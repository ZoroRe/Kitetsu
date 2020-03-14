package me.zoro.kitetsu.net.feign;

import me.zoro.kitetsu.model.IDEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

/**
 * @author luguanquan
 * @date 2020-03-13 20:24
 *
 * 需要再 Application 加上 @EnableFeignClients
 */
@FeignClient(name = "simple-feign-client", url = "http://localhost:10001", path = "kitetsu/")
@Service
public interface FeignService {


	/**
	 * 这个示例主要是如果需要替换 @FeignClient 中设置的 url 应该如何替换，传入完整的 url
	 * @param uri
	 * @param param
	 * @return
	 */
	@RequestMapping(
			method = RequestMethod.GET,
			consumes = {"application/json"},
			produces = {"application/json"})
	String getWithFullUrl(URI uri, @RequestParam String param);

	/**
	 * feign 进行一个 get 请求的示例
	 * @return
	 */
	@RequestMapping(
			value = "/hello/get",
			method = RequestMethod.GET,
			consumes = {"application/json"},
			produces = {"application/json"})
	String getWithBaseUrl();

	/**
	 * 包含参数的 get 示例
	 * @param idEntity
	 * @return
	 */
	@RequestMapping(
			value = "/hello/get",
			method = RequestMethod.GET,
			consumes = {"application/json"},
			produces = {"application/json"})
	String getWithBaseUrlAndParams(@RequestParam IDEntity idEntity);

	/**
	 * feign 进行一个 post 请求的示例
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(
			value = "/hello/post",
			method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"})
	 String postWithBaseUrl(@RequestBody IDEntity id);

}
