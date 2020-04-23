package me.zoro.kitetsu.algorithm.data.structure;

/**
 * @author luguanquan
 * @date 2020-04-02 20:51
 * 堆，完全二叉树。除了叶子层，其他节点数都是这层的最大节点数。
 * 节点值大于等于全部子树节点的是最大堆
 * 节点值小于等于全部子树节点的是最小堆
 * 可用于海量数据查找最大/最小K个数(构建k个数的堆，然后后续比较根节点)
 */
public class Heap {


	/**
	 * 构建k个数的最大堆
	 * @param array
	 */
	public static int[] buildMaxHead(int[] array) {
		if (array == null) {
			return new int[0];
		}
		int k = array.length;
		int[] data = new int[k];
		int size = 0;
		int curIndex;
		int parentIndex;
		int nextData;
		for (int i = 0; i < array.length; i++) {
			nextData = array[i];
			curIndex = size++;
			parentIndex = (curIndex - 1) / 2;
			data[curIndex] = nextData;
			/// 这里不太准，不是准确的最大堆，比较到了左兄弟
			while (parentIndex >= 0 && data[curIndex] > data[parentIndex]) {
				data[curIndex] = data[curIndex] + data[parentIndex];
				data[parentIndex] = data[curIndex] - data[parentIndex];
				data[curIndex] = data[curIndex] - data[parentIndex];
				curIndex = parentIndex;
				parentIndex = curIndex / 2;
			}
		}
		return data;
	}

	public void buildRealMaxHeap(int[] array){

	}

}
