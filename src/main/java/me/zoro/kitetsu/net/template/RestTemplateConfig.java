package me.zoro.kitetsu.net.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author luguanquan
 * @date 2020-03-13 20:00
 *
 * 这个 @Configuration 放佛是类似以前一些 xml 的配置,设置之后里面方法的 @Bean 才能有效
 */
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
