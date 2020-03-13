package me.zoro.kitetsu.net;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luguanquan
 * @date 2020-03-13 18:51
 */
@RestController
public class SimpleRestService {


	@GetMapping("kitetsu/hello/get")
	public String helloWorld(){
		return "GET: hello world!";
	}

	@PostMapping("kitetsu/hello/post")
	public String postHelloWorld(){
		return "POST: hello world!";
	}
}
