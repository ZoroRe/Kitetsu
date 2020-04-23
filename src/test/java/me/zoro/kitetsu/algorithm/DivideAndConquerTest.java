package me.zoro.kitetsu.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author luguanquan
 * @date 2020-04-09 23:00
 */
@DisplayName("分治算法")
@Slf4j
public class DivideAndConquerTest {

	@DisplayName("归并排序测试")
	@Test
	public void mergeSortTest() {
		int[] source = new int[]{1, 5, 9, 2, 3, 7, 4, 8, 6};
		int[] result = new int[9];
		DivideAndConquer.mergeSort(source, 0, source.length - 1, result);
		assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, result);
	}

	@DisplayName("快速排序算法")
	@Test
	public void quickSortTest() {
		int[] source = new int[]{1, 5, 9, 2, 3, 7, 4, 8, 6};
		DivideAndConquer.quickSort(source, 0, source.length - 1);
		assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, source);
	}
}
