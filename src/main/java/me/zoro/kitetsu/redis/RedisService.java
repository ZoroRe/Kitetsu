package me.zoro.kitetsu.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author luguanquan
 * @date 2020-03-13 21:49
 */
@Service
@Slf4j
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 普通缓存获取（不会抛出错误，只会记录错误）
	 *
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key) {
		try {
			return key == null ? null : redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			log.error("get redis key error, key:{}", key, e);
			return null;
		}
	}

	/**
	 * 普通缓存放入并设置时间 （不会抛出错误，只会记录错误）
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒) 大于0表示设置过期时间，小于等于0表示不设置过期时间
	 * @param unit  单位
	 * @return true成功 false 失败
	 */
	public boolean set(String key, Object value, long time, TimeUnit unit) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, unit);
			} else {
				redisTemplate.opsForValue().set(key, value);
			}
			return true;
		} catch (Exception e) {
			log.error("set redis fail,key:{}, value:{}", key, value, e);
			return false;
		}
	}


	/**
	 * 按分数的限制获取中段的数据（不会抛出错误，只会记录错误）
	 *
	 * @param key 键
	 * @param min 分数最小值
	 * @param max 分数最大值
	 * @return
	 */
	public Set<Object> zsRangeByScore(String key, double min, double max) {
		try {
			return redisTemplate.opsForZSet().rangeByScore(key, min, max);
		} catch (Exception e) {
			log.error("zsRangeByScore error, key:{}, min:{}, max:{}", key, min, max, e);
			return null;
		}
	}

	public Set<Object> zsReverseRange(String key, long start, long end) {
		try {
			return redisTemplate.opsForZSet().reverseRange(key, start, end);
		} catch (Exception e) {
			log.error("zsReverseRange error, key:{}, start:{}, end:{}", key, start, end, e);
			return null;
		}
	}

	public Long zsReverseRank(String key, Object value) {
		try {
			return redisTemplate.opsForZSet().reverseRank(key, value);
		} catch (Exception e) {
			log.error("zsReverseRank error, key:{}, value:{}", key, value, e);
			return null;
		}
	}
}
