package me.zoro.kitetsu.distributed.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author luguanquan
 * 基于 redis 实现的分布式锁
 */
@EnableAspectJAutoProxy
@Aspect
@Component
public class RedisSyncAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisSyncAspect.class);
	
	@Autowired
	private RedisLockHelper redisLockHelper;

	@Around("@annotation(me.zoro.kitetsu.distributed.lock.Synchronized) execution(* *.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
		if(this.redisLockHelper == null) {
            LOGGER.error("redisHelper is null, RedisSyncAspect will passthrough all method call");
            RedisSyncContext.set(false, true);
            return pjp.proceed();
        }

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method targetMethod = signature.getMethod();
        Synchronized sync = targetMethod.getAnnotation(Synchronized.class);

		String namespace = sync.namespace();
		int expireSecond = sync.expireSecond();
		int waitLockMillisecond = sync.waitLockMillisecond();
		String threadName = Thread.currentThread().getName();
		String key = "-";



		// 构造兔子数列，最大1000
		int a = 0, b = 1;

		long start = System.currentTimeMillis();
		while(true) {
			boolean requireLock = redisLockHelper.requireLock(namespace, key, expireSecond);
			if(requireLock) {
				try {
					RedisSyncContext.set(true, true);
					return pjp.proceed();
				} finally {
                    LOGGER.debug("释放分布式锁，namespace=[{}], expireSecond=[{}], waitLockInMills=[{}], threadName=[{}], targetMethod=[{}]", namespace, expireSecond, waitLockMillisecond, threadName, targetMethod.getName());
					redisLockHelper.releaseLock(namespace, "-");
				}
			}
			
			if(waitLockMillisecond == 0) {
				RedisSyncContext.set(true, false);
                LOGGER.error("threadName=[{}], targetMethod=[{}],waitLockInMills=0, 该方法不再尝试进行", threadName, targetMethod.getName());
				return null;
			}
			long totalWait = System.currentTimeMillis() - start;
			if(totalWait >= waitLockMillisecond) {
				RedisSyncContext.set(true, false);
                LOGGER.error(" threadName=[{}], targetMethod=[{}],waitLockInMills=[{}], 开始时间为[{}],已等待[{}],不再尝试进行当前方法", threadName, targetMethod.getName(),waitLockMillisecond, start, totalWait);
				return null;
			}
			if(waitLockMillisecond - totalWait < b) {
				Thread.sleep(waitLockMillisecond - totalWait);
			} else {
				Thread.sleep(b);
				int c = a + b;
				a = b;
				b = c;
				if(b > 1000) {b = 1000;}
			}
		}
    }

}
