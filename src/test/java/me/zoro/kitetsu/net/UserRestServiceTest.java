package me.zoro.kitetsu.net;

import lombok.extern.slf4j.Slf4j;
import me.zoro.kitetsu.json.JsonHelper;
import me.zoro.kitetsu.model.ApiResponseDTO;
import me.zoro.kitetsu.model.IDEntity;
import me.zoro.kitetsu.model.UserDO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author luguanquan
 * @date 2020-03-14 22:28
 */
@DisplayName("UserRestService 接口测试")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class UserRestServiceTest {


	@Autowired
	private TestRestTemplate testRestTemplate;
	private String baseUrl = "http://localhost:10001/kitetsu";
	private static Long id = null;
	private static String name = "roro";
	private static String birthday = "19920306";
	private static Integer gender = 1;

	@DisplayName("创建用户 测试")
	@Test
	@Order(1)
	public void createUserTest() {
		UserDO userDO = new UserDO();
		userDO.setName(name);
		userDO.setGender(gender);
		userDO.setBirthday(birthday);
		String resp = testRestTemplate.postForObject(baseUrl + "/user/create", userDO, String.class);
		ApiResponseDTO<UserDO> data = JsonHelper.fromJson(resp, ApiResponseDTO.class, UserDO.class);
		assertNotNull(data);
		assertEquals(0, data.getCode());
		UserDO respUserDO = data.getData();
		assertNotNull(respUserDO);
		assertEquals(name, respUserDO.getName());
		assertEquals(gender, respUserDO.getGender());
		assertEquals(birthday, respUserDO.getBirthday());
		id = respUserDO.getId();
	}

	@DisplayName("查询用户 测试")
	@Test
	@Order(2)
	public void queryUserTest() {
		// 验证返回有正确数据时， TODO 为何用着两种注释掉的方式在接口处理时取不出 id
//		HashMap<String,Ob> params = new HashMap<>();
//		params.put("id", 14L);
//		IDEntity idEntity = new IDEntity();
//		idEntity.setId(14L);
		String resp = testRestTemplate.getForObject(baseUrl + "/user/get?id=" + id, String.class);

		ApiResponseDTO<UserDO> data = JsonHelper.fromJson(resp, ApiResponseDTO.class, UserDO.class);
		assertNotNull(data);
		assertEquals(0, data.getCode());
		UserDO respUserDO = data.getData();
		assertNotNull(respUserDO);
		assertEquals(name, respUserDO.getName());
		assertEquals(gender, respUserDO.getGender());
		assertEquals(birthday, respUserDO.getBirthday());

		// 验证查找的 id 没有这个用户时
		String notFoundRespStr = testRestTemplate.getForObject(baseUrl + "/user/get?id=" + -1, String.class);
		ApiResponseDTO notFoundResp = JsonHelper.fromJson(notFoundRespStr, ApiResponseDTO.class);
		assertEquals(1, notFoundResp.getCode().intValue());
	}

	@DisplayName("删除用户 测试")
	@Test
	@Order(3)
	public void deleteUserTest() {
		// 删除存在的数据
		IDEntity idEntity = new IDEntity();
		idEntity.setId(id);
		String resp = testRestTemplate.postForObject(baseUrl + "/user/delete", idEntity, String.class);
		ApiResponseDTO data = JsonHelper.fromJson(resp, ApiResponseDTO.class);
		assertNotNull(data);
		assertEquals(0, data.getCode());

		// 删除后再次查询确认返回无此用户
		String notFoundRespStr = testRestTemplate.getForObject(baseUrl + "/user/get", String.class, idEntity);
		ApiResponseDTO notFoundResp = JsonHelper.fromJson(notFoundRespStr, ApiResponseDTO.class);
		assertEquals(1, notFoundResp.getCode().intValue());

		// 删除不存在的数据
		idEntity.setId(-1L);
		String notFound = testRestTemplate.postForObject(baseUrl + "/user/delete", idEntity, String.class);
		ApiResponseDTO notFundData = JsonHelper.fromJson(notFound, ApiResponseDTO.class);
		assertNotNull(notFundData);
		assertEquals(2, notFundData.getCode());
	}

}
