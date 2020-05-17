package me.zoro.kitetsu.proxy.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * @author luguanquan
 * @date 2020/5/17 10:47 上午
 */
@DisplayName("JdkProxy示例 测试用例")
@Slf4j
public class MoveProxyInvocationTest {

	@DisplayName("代理接口")
	@Test
	public void jdkProxyInterfaceTest() {
		MoveProxyInvocation moveProxyInvocation = new MoveProxyInvocation();
		// create proxy
		MoveInterface service = (MoveInterface) Proxy.newProxyInstance(MoveInterface.class.getClassLoader(),
				new Class<?>[]{MoveInterface.class}, moveProxyInvocation);
		service.fly();
	}

	@DisplayName("代理接口错误示例")
	@Test
	public void jdkProxyInterfaceErrorTest() {

		MoveProxyInvocation moveProxyInvocation = new MoveProxyInvocation();
		try {
			MoveInterface service = (MoveInterface) Proxy.newProxyInstance(MoveInterface.class.getClassLoader(),
					MoveInterface.class.getInterfaces(), moveProxyInvocation);
			service.fly();
		} catch (Exception e) {
			log.error("newProxyInstance 第二个参数需要是一个继承接口的类，也不能直接传对应接口的.getInterfaces()");
		}
	}

	@DisplayName("代理类-不调用类原始实现")
	@Test
	public void jdkProxyClassTest() {
		MoveProxyInvocation moveProxyInvocation = new MoveProxyInvocation();
		MoveInterface service = (MoveInterface) Proxy.newProxyInstance(MoveClass.class.getClassLoader(),
				MoveClass.class.getInterfaces(), moveProxyInvocation);
		service.fly();
	}

	@DisplayName("代理类-调用类原始实现")
	@Test
	public void jdkProxyClassWithImplTest() {
		MoveClass moveClass = new MoveClass();
		MoveProxyInvocation moveProxyInvocation = new MoveProxyInvocation(moveClass);
		// 注意这里代理的肯定是接口，强转成接口，而不是类，否则出错
		MoveInterface service = (MoveInterface) Proxy.newProxyInstance(MoveClass.class.getClassLoader(),
				MoveClass.class.getInterfaces(), moveProxyInvocation);
		service.fly();
	}

	@DisplayName("示范一个泛型式接口[类似Retrofit]")
	@Test
	public void Any(){

	}

}
