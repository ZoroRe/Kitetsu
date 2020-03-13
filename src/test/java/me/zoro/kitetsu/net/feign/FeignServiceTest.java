package me.zoro.kitetsu.net.feign;

import me.zoro.kitetsu.entity.IDEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author luguanquan
 * @date 2020-03-13 20:04
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("FeignService 测试")
class FeignServiceTest {

	@Autowired
	private FeignService feignService;

	@Test
	@DisplayName("getWithFullUrl 测试")
	public void getWithFullUrlTest() {
		String url = "http://localhost:10001/kitetsu/hello/get";
		boolean hasException = false;
		String resp = null;
		try {
			resp = feignService.getWithFullUrl(new URI(url), null);
		} catch (URISyntaxException e) {
			hasException = true;
		}
		assertFalse(hasException);
		assertEquals("GET: hello world!", resp);
	}

	@Test
	@DisplayName("getWithBaseUrl 测试")
	public void getWithBaseUrlTest(){
		String resp = feignService.getWithBaseUrl();
		assertEquals("GET: hello world!", resp);
	}

	@Test
	@DisplayName("getWithBaseUrlAndParams 测试")
	public void getWithBaseUrlAndParamsTest(){
		IDEntity idEntity = new IDEntity();
		idEntity.setId(1L);
		String resp = feignService.getWithBaseUrlAndParams(idEntity);
		assertEquals("GET: hello world!", resp);
	}

	@Test
	@DisplayName("postWithBaseUrl 测试")
	public void postWithBaseUrlTest(){
		IDEntity idEntity = new IDEntity();
		idEntity.setId(1L);
		String resp = feignService.postWithBaseUrl(idEntity);
		assertEquals("POST: hello world!", resp);
	}
}
