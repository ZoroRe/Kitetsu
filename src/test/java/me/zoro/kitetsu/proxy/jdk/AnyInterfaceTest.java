package me.zoro.kitetsu.proxy.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author luguanquan
 * @date 2020/5/17 1:31 下午
 */
@DisplayName("示例一些任意接口实现某统一代理")
@Slf4j
public class AnyInterfaceTest {

	@DisplayName("测试")
	@Test
	public void test() {
		AnyInterfaceTestInterface service = AnyInterfaceProxy.create(AnyInterfaceTestInterface.class);

		log.info("hello={}", service.hello());

		log.info("helloName={}", service.helloName("zoro"));

		log.info("helloTwo={}", service.helloTwo("zoro", "yan"));
	}
}
