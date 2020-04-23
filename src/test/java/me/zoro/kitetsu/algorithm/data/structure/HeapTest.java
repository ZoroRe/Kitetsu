package me.zoro.kitetsu.algorithm.data.structure;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author luguanquan
 * @date 2020-04-02 21:46
 */
@DisplayName("堆测试")
@Slf4j
public class HeapTest {

	@DisplayName("测试构建最大堆")
	@Test
	public void buildMaxHeapTest() {
		int[] a = new int[]{10};
		assertArrayEquals(new int[]{10}, Heap.buildMaxHead(a));
		a = new int[]{10, 8, 5, 7, 9};
		assertArrayEquals(new int[]{10, 9, 5, 7, 8}, Heap.buildMaxHead(a));

		log.info("原数组:{},最大堆:{}", a, Heap.buildMaxHead(a));
	}
}
