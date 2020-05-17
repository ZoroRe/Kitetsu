package me.zoro.kitetsu.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author luguanquan
 * @date 2020/5/17 10:37 上午
 */
@Slf4j
public class MoveProxyInvocation implements InvocationHandler {

	/**
	 * 如果被代理的是一个类，对应接口有一个实现，需要代理时同时调用原接口，就需要传入对应的被代理类，然后通过反射调用他的方法
	 */
	private Object target;

	public MoveProxyInvocation(Object target) {
		this.target = target;
	}

	public MoveProxyInvocation() {
	}

	/**
	 * @param proxy
	 * @param method
	 * @param args
	 * @return object 返回的 object 就是对应原始调用接口的返回值
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("Notice: now you can move by proxy. the method you invoke is {}", method.getName());
		Boolean result = true;
		if (target != null) {
			result = (Boolean) method.invoke(target, args);
		}
		return result;
	}
}
