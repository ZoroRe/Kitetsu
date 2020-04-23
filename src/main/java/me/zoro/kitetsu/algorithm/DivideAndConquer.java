package me.zoro.kitetsu.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luguanquan
 * @date 2020-04-09 22:28
 * <p>
 * 分治算法经典问题汇集
 */
@Slf4j
public class DivideAndConquer {

	/**
	 * 归并排序
	 * 1.取中间值，然后左右两边分别用归并排序，假设为A,B
	 * 2.合并结果，假设左右两边都排序好，注意不保证A一定比B小，只是他们排序好了。然后同时将AB遍历，把两者小的放在前面，放入后的序列向前移动
	 * 其中一个序列移动完后，剩下子序列可以直接按照顺序放入
	 * 3.代码核心在于合并部分,但是递归的子序列调用也要注意(只是复杂逻辑不在这里)
	 */

	public static void mergeSort(int[] source, int start, int end, int[] temp) {
		if (start >= end) {
			return;
		}
		int mid = (end - start) / 2 + start;
		mergeSort(source, start, mid, temp);
		mergeSort(source, mid + 1, end, temp);
		merge(source, start, mid, end, temp);
	}

	private static void merge(int[] source, int start, int mid, int end, int[] temp) {
		// 合并子序列，这里就说明start - mid已经排好序,mid+1 -  end 也排好序，将他们合并起来
		int firstCursor = start;
		int secondCursor = mid + 1;
		int resultIndex = 0;
		while (firstCursor <= mid && secondCursor <= end) {
			if (source[firstCursor] < source[secondCursor]) {
				temp[resultIndex++] = source[firstCursor++];
			} else {
				temp[resultIndex++] = source[secondCursor++];
			}
		}
		while (firstCursor <= mid) {
			temp[resultIndex++] = source[firstCursor++];
		}
		while (secondCursor <= end) {
			temp[resultIndex++] = source[secondCursor++];
		}
		// 注意这里，要用临时拍好的覆盖原始数组
		for (int i = 0, j = start; i < resultIndex; ) {
			source[j++] = temp[i++];
		}
	}

	/**
	 * 快速排序
	 *
	 * @param arr
	 */
	public static void quickSort(int[] arr, int low, int high) {
		if (low >= high) {
			return;
		}
		// FIXME: 2020-04-14 未通过
		// 选出一个 key, 然后大于key在右侧，小于key在左侧，返回 key 所在 index，根据 index 再排序两边,key也可以直接取arr[low]
		// 只要保证选出后，key 左边小于等于它，右边大于等于它，找到key的索引
		int index = (high - low) / 2 + low;
		int key = arr[index];
		int firstCursor = low;
		int secondCursor = high;
		while (firstCursor < secondCursor) {
			// 注意这里等于也减少，这样最好找到的 firstCursor 和 secondCursor 碰头的地方就和 index 交换，index 的值为最后碰头的值
			// 重点要知道 index 会被更新
			while (arr[firstCursor] <= key && firstCursor < secondCursor) {
				firstCursor++;
			}
			while (arr[secondCursor] <= key && firstCursor < secondCursor) {
				secondCursor--;
			}
			if (firstCursor < secondCursor) {
				//swap
				swap(arr, firstCursor, secondCursor);
				log.info("finished swap on: firstCursor={},secondCursor={},key={}, arr={}", firstCursor, secondCursor,
						key, arr);
			}
			firstCursor++;
			secondCursor--;
		}
		// swap
		swap(arr, firstCursor, index);
		index = firstCursor;
		// 原本可以直接用 index - 1 和 index + 1排序两边，这里再比较一次如果有多个相同的值，可以减少相同值再次进入比较，提高效率
		int min = index - 1;
		while (arr[min] == key) {
			min--;
		}
		int max = index + 1;
		while (arr[max] == key) {
			max--;
		}
		quickSort(arr, low, min + 1);
		quickSort(arr, max - 1, high);
	}

	private static void swap(int[] arr, int first, int second) {
		arr[first] += arr[second];
		arr[second] = arr[first] - arr[second];
		arr[first] = arr[first] - arr[second];
	}


	public static void main(String[] args) {
		int[] source = new int[]{1, 5, 9, 2, 3, 7, 4, 8, 6};
		DivideAndConquer.quickSort(source, 0, source.length - 1);
	}
}
