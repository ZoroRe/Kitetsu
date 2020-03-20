package me.zoro.kitetsu.boxed;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luguanquan
 * @date 2020-03-20 23:09
 */
public class BoxedUtils {

	public static void addOne(Integer value) {
		value = value + 1;
	}

	public static void addOne(AtomicInteger value) {
		value.addAndGet(1);
	}
}
