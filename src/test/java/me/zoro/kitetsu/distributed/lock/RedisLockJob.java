package me.zoro.kitetsu.distributed.lock;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author luguanquan
 * @date 2020-03-16 16:55
 */
@Service
public class RedisLockJob {

	/**
	 *
	 * 在等待时间内完成任务
	 * 任务执行时间5s, 锁超时时间20s, 其他线程等待时间20s
	 * 所以并发两个任务，两个都能完成
	 * @param index
	 * @return
	 * @throws InterruptedException
	 */
	@Synchronized(namespace = "RedisLockJob-finishInExpireTime", expireSecond = 20, waitLockMillisecond = 20000)
	public Integer finishInExpireTime(int index) throws InterruptedException {
		Thread.sleep(5 * 1000);
		return index;
	}

	/**
	 *
	 * 执行任务时间10s, 锁超时时间20s, 等待时间5s
	 * 并发两个任务，第二个将会超时
	 * @param index
	 * @return
	 * @throws InterruptedException
	 */
	@Synchronized(namespace = "RedisLockJob-finishAfterExpireTime", expireSecond = 20, waitLockMillisecond = 5000)
	public Integer finishAfterExpireTime(int index) throws InterruptedException{
		Thread.sleep(10 * 1000);
		return index;
	}

	/**
	 *
	 * 执行任务时间5s, 锁超时时间20s, 等待时间0s
	 * 并发两个任务，第二个任务直接不执行
	 * @param index
	 * @return
	 * @throws InterruptedException
	 */
	@Synchronized(namespace = "RedisLockJob-finishAfterExpireTime", expireSecond = 20, waitLockMillisecond = 0)
	public Integer notWait(int index) throws InterruptedException{
		Thread.sleep(5 * 1000);
		return index;
	}
}
