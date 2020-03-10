package me.zoro.kitetsu.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author luguanquan
 * @date 2020-03-09 04:50
 * 反射是什么？反射，其实就是java中动态对一个类的对象的获取，以及对该对象全部属性和全部方法的访问（对于private属性或方法，需要先调用setAccessible(true)）
 * 反射相关学习，反射相关其实主要四个模块，Class,Filed,Method,Constructor
 */
@Slf4j
public class ReflectStudy {
	private String privateInfo;
	public String publicInfo;

	/**
	 * 关于Class获取，主要有三种方法。
	 * 1. 通过类获取: ReflectStudy.class
	 * 2. 通过对象获取: reflectStudy.getClass()
	 * 3. 通过类全名获取: class.forName("me.zoro.kitetsu.ReflectStudy")
	 */
	public void aboutClass() {
		// 1.通过类获取
		Class<ReflectStudy> clazz1 = ReflectStudy.class;
		// 2.通过对象获取
		ReflectStudy reflectStudy = new ReflectStudy();
		Class clazz2 = reflectStudy.getClass();
		// 3.通过全名获取
		try {
			Class class3 = Class.forName("me.zoro.kitetsu.reflect.ReflectStudy");
		} catch (ClassNotFoundException e) {
			log.error("class not found {}", e);
		}
	}

	/**
	 * 获取单个属性有两种方式
	 * 1.getField() 只能获取到 public 属性
	 * 2.getDeclaredField 能获取到 private 属性，但要修改访问权限
	 * 以上两个方法都有对应的 +s 获取复数形式
	 *
	 * @return
	 */
	public boolean aboutField() {
		boolean success = true;
		Class clazz = ReflectStudy.class;
		try {
			// getField 只能获取到 public 的属性
			Field field0 = clazz.getField("publicInfo");
			// getDeclaredField 能获取到 private 属性，但要修改访问权限
			Field field1 = clazz.getDeclaredField("privateInfo");
			field1.setAccessible(true);
		} catch (NoSuchFieldException e) {
			log.error("no suche field {}", e);
			success = false;
		}
		return success;
	}

	/**
	 * 获取单个方法有两种方式
	 * 1.getMethod() 只能获取到 public 方法
	 * 2.getDeclaredMethod 能获取到 private 方法，但要修改访问权限
	 * 以上两个方法都有对应的 +s 获取复数形式
	 *
	 * @return
	 */
	public boolean aboutMethod() {
		boolean isSuccess = true;
		Class clazz = ReflectStudy.class;
		try {
			Method method1 = clazz.getMethod("testPlus", Integer.class, Integer.class);
			Method method2 = clazz.getDeclaredMethod("testSubtraction", Integer.class, Integer.class);
		} catch (NoSuchMethodException e) {
			log.error("no such method {}", e);
			isSuccess = false;
		}
		return isSuccess;
	}

	private int testPlus(int a, int b) {
		return a + b;
	}

	public int testSubtraction(int a, int b) {
		return a - b;
	}


}
