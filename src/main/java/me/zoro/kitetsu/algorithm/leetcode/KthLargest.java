package me.zoro.kitetsu.algorithm.leetcode;

import java.util.PriorityQueue;

/**
 * @author luguanquan
 * @date 2020-04-15 21:23
 * <p>
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 * 保持数据流中第k大的元素,Leetcode 703
 */
public class KthLargest {

	/**
	 * 解决方案1，使用系统自带的 PriorityQueue
	 */
	public static class SolutionOne {

		// 默认从小到大，可自定义比较器
		private PriorityQueue<Integer> queue;
		private int k;

		public SolutionOne(int k, int[] nums) {
			this.k = k;
			queue = new PriorityQueue<>(k);
			for (int i = 0; i < nums.length; i++) {
				if (i < k) {
					queue.add(nums[i]);
				} else {
					add(nums[i]);
				}
			}
		}

		public int add(int val) {
			if (queue.size() < k) {
				queue.add(val);
			} else if (val > queue.peek()) {
				queue.poll();
				queue.add(val);
			}
			return queue.peek();
		}
	}

	/**
	 * 解决方案
	 */
	public static class SolutionTwo {
		private int size;
		private int k;
		private int[] minHeap;
		private int parent;
		private int current;

		public SolutionTwo(int k, int[] arr) {
			this.k = k;
			minHeap = new int[k + 1];
			for (int i = 0; i < arr.length; i++) {
				add(arr[i]);
			}
		}

		private void swap(int[] array, int first, int second) {
			array[first] += array[second];
			array[second] = array[first] - array[second];
			array[first] = array[first] - array[second];
		}

		public int add(int val) {
			if (size < k) {
				minHeap[++size] = val;
				current = size;
				parent = current / 2;
				while (parent > 0) {
					if (minHeap[current] < minHeap[parent]) {
						swap(minHeap, current, parent);
						current = parent;
						parent = current / 2;
					}
				}
			} else if (minHeap[1] < val) {
				// 堆的删除和添加,添加容易，主要是删除要处理下
				// 删除顶点,把最末尾的添加到顶点，然后选出左右儿子中的小值，将其和小值比较，如此递归调整
				minHeap[1] = val;
				current = 1;
				//添加新节点,因为删除后size<k，可以直接走上面的条件，直接添加
				add(val);
			}
			return minHeap[1];
		}

	}
}
