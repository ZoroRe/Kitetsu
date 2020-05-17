package me.zoro.kitetsu.proxy.cglib;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luguanquan
 * @date 2020/5/17 3:44 下午
 */
@Slf4j
public class CglibTarget {

	public void invokeDefault() {
		log.info("这是原方法: this is invokeDefault by CglibTarget");
	}
}
