package me.zoro.kitetsu.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author luguanquan
 * @date 2020/5/17 3:50 下午
 */
@DisplayName("CGLib 示例")
@Slf4j
public class CglibProxyInvokeDefaultTest {

	@DisplayName("调用原方法")
	@Test
	public void invokeDefaultTest() {
		CglibTarget target = new CglibTarget();
		// 主要是创建 Enhancer, 然后设置 callback 为要去代理的方法 MethodInterceptor 的实现类，intercept即使代理方法
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		CglibProxyInvokeDefault proxyCallback = new CglibProxyInvokeDefault();
		enhancer.setCallback(proxyCallback);
		CglibTarget proxy = (CglibTarget) enhancer.create();
		proxy.invokeDefault();
		log.info("\n原始类:{}\n代理类:{}\n由此可见代理类为被代理类的子类", CglibTarget.class, proxy.getClass());
	}

	@DisplayName("不调用原方法")
	@Test
	public void notInvokeDefaultTest() {
		CglibTarget target = new CglibTarget();
		// 主要是创建 Enhancer, 然后设置 callback 为要去代理的方法 MethodInterceptor 的实现类，intercept即使代理方法
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		CglibProxyNotInvokeDefault proxyCallback = new CglibProxyNotInvokeDefault();
		enhancer.setCallback(proxyCallback);
		CglibTarget proxy = (CglibTarget) enhancer.create();
		proxy.invokeDefault();
		log.info("\n原始类:{}\n代理类:{}\n由此可见代理类为被代理类的子类", CglibTarget.class, proxy.getClass());
	}
}
