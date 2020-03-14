package me.zoro.kitetsu.mysql.mybatis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import lombok.extern.slf4j.Slf4j;
import me.zoro.kitetsu.model.UserDO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author luguanquan
 * @date 2020-03-14 10:26
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("UserMapper 测试")
// 实现测试方法先后顺序
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	/**
	 * 注意，非 static 变量在某个 @Test 方法赋值后，不会共享给其他 @Test 方法，所以这里直接用 static,
	 * 实际如果要共享变量要确认 static 是否有影响
	 */
	private static Long id = null;
	private static String name = "zoro";
	private static String birthday = "19920408";
	private static Integer gender = 0;

	@DisplayName("insert 测试")
	@Test
	@Order(1)
	public void insertTest() {
		UserDO userDO = new UserDO();
		userDO.setName(name);
		userDO.setBirthday(birthday);
		userDO.setGender(gender);
		userMapper.insert(userDO);
		id = userDO.getId();
		assertTrue(userDO.getId() != null && userDO.getId() > 0);
	}


	@DisplayName("findById 测试")
	@Test
	@Order(2)
	public void findByIdTest() {
		UserDO userDO = userMapper.findById(id);
		assertNotNull(userDO);
		assertEquals(id, userDO.getId().longValue());
		assertEquals(name, userDO.getName());
		assertEquals(birthday, userDO.getBirthday());
		assertEquals(gender, userDO.getGender());
	}

	@DisplayName("deleteById ")
	@Test
	@Order(3)
	public void deleteById() {
		Integer result = userMapper.deleteById(id);
		assertNotNull(result);
		assertTrue(result > 0);
	}
}
