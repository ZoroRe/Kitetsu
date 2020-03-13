package me.zoro.kitetsu.net.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author luguanquan
 * @date 2020-03-13 18:50
 *
 * 这里示例示范怎么通过 RestTemplate 进行网络请求
 */
@Service
public class RestTemplateService {

	/**
	 * 可以使用这个进行网络请求，内部微服务的 http 调用等
	 */
	@Autowired
	private RestTemplate restTemplate;

	private String getUrl = "http://localhost:10001/kitetsu/hello/get";
	private String postUrl = "http://localhost:10001/kitetsu/hello/post";

	public String restGetForObject() {
		String resp = restTemplate.getForObject(getUrl, String.class);
		return resp;
	}

	public ResponseEntity restGetForEntity() {
		ResponseEntity<String> resp = restTemplate.getForEntity(getUrl, String.class);
		return resp;
	}

	public String restPostForObject() {
		String resp = restTemplate.postForObject(postUrl, null, String.class);
		return resp;
	}

	public ResponseEntity restPostForEntity() {
		ResponseEntity<String> resp = restTemplate.postForEntity(postUrl, null, String.class);
		return resp;
	}
}
