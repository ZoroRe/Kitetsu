package me.zoro.kitetsu.mysql.ds2;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author luguanquan
 * @date 2020-03-19 23:09
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("MySql第二数据源 测试")
public class UserMapper2Test {

	@Autowired
	private UserMapper2 userMapper2;

	@DisplayName("count 测试")
	@Test
	public void countTest() {
		Long count = userMapper2.count();
		assertNotNull(count);
		assertTrue(count > 0);
	}
}
