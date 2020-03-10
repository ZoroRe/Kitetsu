package me.zoro.kitetsu.algorithm.array;


/**
 * @author luguanquan
 * @date 2020-03-07 15:58
 * @email luguanquans@qq.com
 */
public class ArrayAlgorithm {

	/**
	 * 给定一个数组，长度为n的数组，取值范围为[0,n-1],这里程序不验证数据是否合法，请确保调用方自己确保
	 *
	 * @param array 数组长度为n,则元素取值范围[0,n-1]
	 * @return
	 */
	public static Boolean hasRepeatInArray(Integer[] array) {
		if (array == null) {
			return null;
		}
		Integer temp = null;
		for (int i = 0; i < array.length; i++) {
			// 这个判断可以不用，但用了之后如果有重复值能提前知道
			if (i != array[i] && array[i].equals(array[array[i]])) {
				return true;
			}
			// 交换位置
			temp = array[i];
			array[i] = array[temp];
			array[temp] = temp;
		}
//		在上面 for 中判断了就不需要这个遍历，提高效率，只遍历一遍
//		for (int i = 0; i < array.length; i++) {
//			if (array[i].intValue() != i) {
//				return true;
//			}
//		}
		return false;
	}
}
