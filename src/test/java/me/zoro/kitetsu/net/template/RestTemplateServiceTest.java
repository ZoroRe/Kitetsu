package me.zoro.kitetsu.net.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

/**
 * @author luguanquan
 * @date 2020-03-13 20:04
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("RestTemplateService 测试")
@Slf4j
public class RestTemplateServiceTest {

	@Autowired
	private RestTemplateService restTemplateService;

	@Test
	@DisplayName("restGetForObject 测试")
	public void restGetForObjectTest() {
		String resp = restTemplateService.restGetForObject();
		assertEquals("GET: hello world!", resp);
	}

	@Test
	@DisplayName("restGetForEntity 测试")
	public void restGetForEntityTest() {
		ResponseEntity<String> resp = restTemplateService.restGetForEntity();
		assertEquals(200, resp.getStatusCode().value());
		assertEquals("GET: hello world!", resp.getBody());
	}

	@Test
	@DisplayName("restPostForObject 测试")
	public void restPostForObjectTest() {
		String resp = restTemplateService.restPostForObject();
		assertEquals("POST: hello world!", resp);
	}

	@Test
	@DisplayName("restPostForEntity 测试")
	public void restPostForEntityTest() {
		ResponseEntity<String> resp = restTemplateService.restPostForEntity();
		assertEquals(200, resp.getStatusCode().value());
		assertEquals("POST: hello world!", resp.getBody());
	}


}
