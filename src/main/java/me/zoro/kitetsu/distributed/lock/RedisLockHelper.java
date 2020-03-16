package me.zoro.kitetsu.distributed.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

/**
 * @author  luguanquan
 * 获取和释放锁的类
 */
@Service
@Slf4j
public class RedisLockHelper {


    private static String getKey(String namespace, String key) {
        return "lock:" + namespace + ":" + key;
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获得一个名称为key的锁，redis保证同一时刻只有一个client可以获得锁。
     *
     * @param namespace             命名空间，每个应用独立的空间
     * @param key                   业务key，redis将保证同一个namespace同一个key只有一个client可以拿到锁
     * @param maxTransactionSeconds 单位秒，必须大于0,拿到锁之后,预计多久可以完成这个事务，如果超过这个时间还没有归还锁，那么事务将自动归还锁
     * @return
     */
    public boolean requireLock(String namespace, String key, int maxTransactionSeconds) {
        if (namespace == null || key == null || key.isEmpty() || maxTransactionSeconds <= 0) {
            log.warn("requireLock with error params: namespace:{},key:{},maxTransactionSeconds:{}", namespace, key,
                    maxTransactionSeconds, new Exception());
            return false;
        }
        try {
            key = getKey(namespace, key);
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            Boolean success = connection.set(key.getBytes(), "1".getBytes(), Expiration.seconds(maxTransactionSeconds),
                    RedisStringCommands.SetOption.SET_IF_ABSENT);
            return success != null && success;
        } catch (Exception e) {
            log.warn("requireLock error, namespace:{}, key:{}", namespace, key, e);
            return false;
        }
    }

    /**
     * 如果事务已经完成，则归还锁。
     *
     * @param namespace
     * @param key
     */
    public boolean releaseLock(String namespace, String key) {
        if (namespace == null || key == null || key.isEmpty()) {
            log.warn("requireLock with error params: namespace:{},key:{}", namespace, key, new Exception());
            return false;
        }
        try {
            key = getKey(namespace, key);
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            log.warn("requireLock error, namespace:{}, key:{}", namespace, key, e);
            return false;
        }
    }

}
