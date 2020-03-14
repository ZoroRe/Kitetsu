package me.zoro.kitetsu.redis;

import me.zoro.kitetsu.model.IDEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luguanquan
 * @date 2020-03-12 23:40
 */
@DisplayName("Redis 测试")
// 这个注解允许去读取配置信息
// 说明，如果一次性运行全部的测试用例，这个 SpringBootTest 要一致，不然可能因为上下文不一致导致部分测试用例执行失败
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RedisTest {


	@Autowired
	private RedisService redisService;

	@Test
	@DisplayName("Redis存取测试")
	public void redisTest() {
		String key1 = UUID.randomUUID().toString();
		redisService.set(key1, "hello world", 1, TimeUnit.MINUTES);
		assertEquals("hello world", redisService.get(key1));

		String key2 = UUID.randomUUID().toString();
		IDEntity idEntity  = new IDEntity();
		idEntity.setId(1L);
		redisService.set(key2, idEntity, 1, TimeUnit.MINUTES);
		assertEquals(1L, ((IDEntity)redisService.get(key2)).getId());
	}
}
