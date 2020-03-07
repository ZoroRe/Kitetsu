package me.zoro.kitetsu.algorithm.array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luguanquan
 * @date 2020-03-07 16:04
 * @email luguanquans@qq.com
 */
@DisplayName("数组相关算法测试")
public class ArrayAlgorithmTest {

	@Test
	@DisplayName("重复值题目1测试")
	public void hasRepeatInArrayTest() {
		Integer[] arr0 = new Integer[]{0};
		Integer[] arr1 = new Integer[]{0, 1};
		Integer[] arr2 = new Integer[]{0, 1, 2};
		Integer[] arr3 = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		assertEquals(false, ArrayAlgorithm.hasRepeatInArray(arr0));
		assertEquals(false, ArrayAlgorithm.hasRepeatInArray(arr1));
		assertEquals(false, ArrayAlgorithm.hasRepeatInArray(arr2));
		assertEquals(false, ArrayAlgorithm.hasRepeatInArray(arr3));
		Integer[] arrT0 = new Integer[]{0, 0};
		Integer[] arrT1 = new Integer[]{0, 1, 0};
		Integer[] arrT2 = new Integer[]{0, 1, 2, 2};
		Integer[] arrT3 = new Integer[]{0, 1, 2, 2, 3, 4, 5, 6, 7};
		assertEquals(true, ArrayAlgorithm.hasRepeatInArray(arrT0));
		assertEquals(true, ArrayAlgorithm.hasRepeatInArray(arrT1));
		assertEquals(true, ArrayAlgorithm.hasRepeatInArray(arrT2));
		assertEquals(true, ArrayAlgorithm.hasRepeatInArray(arrT3));
	}
}
