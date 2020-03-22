package me.zoro.kitetsu.company;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luguanquan
 * @date 2020-03-20 07:19
 * <p>
 * 从网上找的偷头条面试算法题
 */
@Slf4j
public class ByteDance {

	/** 题1
	 * 给定一个规则:
	 * S_0 = {1}
	 * S_1 = {1, 2, 1}
	 * S_2 = {1, 2, 1, 3, 1, 2, 1}
	 * ...
	 * S_n = {S_n-1, n + 1, S_n-1}
	 */
	/**
	 * 题1中对于序列 Sn, 包含多少个数字
	 *
	 * @param n
	 * @return
	 */
	public static int countOfSn(int n) {
		if (n < 0) {
			return 0;
		}
		int count = 1;
		while (n >= 0) {
			count = count << 1;
			n--;
		}
		return count - 1;
	}

	/**
	 * 题1中对于给定的 n, k为对应 S_n 中第 k 的数字，求 k 的值
	 *
	 * @param n
	 * @param k
	 * @return
	 */
	public static Integer valueKOfSn(int n, int k) {
		if (n < 0 || k < 0) {
			return null;
		}
		int total = countOfSn(n);
		if (k > total - 1) {
			return null;
		}
		if (k == 0) {
			return 1;
		}
		int center = total / 2;
		if (k == center) {
			return n + 1;
		}
		if (k < center) {
			return valueKOfSn(n - 1, k);
		}
		return valueKOfSn(n - 1, 2 * center - k);
	}

	public static class BinaryTreeNode {

		public BinaryTreeNode(int value) {
			this.value = value;
		}

		public int value;
		public BinaryTreeNode leftChild;
		public BinaryTreeNode rightChild;

	}

	public static int btNodeDepth(BinaryTreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = btNodeDepth(root.leftChild);
		int right = btNodeDepth(root.rightChild);
		return left > right ? left + 1 : right + 1;
	}

	/**
	 * 判断是否是平衡二叉树,递归同时获取高度，单次遍历
	 *
	 * @param root
	 * @param depth
	 * @return
	 */
	public static boolean isBalance(BinaryTreeNode root, AtomicInteger depth) {
		if (root == null) {
			return true;
		}
		AtomicInteger leftDepth = new AtomicInteger(0);
		AtomicInteger rightDepth = new AtomicInteger(0);
		boolean isLeftBalance = isBalance(root.leftChild, leftDepth);
		boolean isRightBalance = isBalance(root.rightChild, rightDepth);
		if (isLeftBalance && isRightBalance) {
			int diff = leftDepth.get() - rightDepth.get();
			depth.addAndGet(leftDepth.get() > rightDepth.get() ? leftDepth.get() + 1 : rightDepth.get() + 1);
			if (diff <= 1 && diff >= -1) {
				/// 日志看节点遍历情况
//				log.info("节点信息: value={}, depth={}, leftDepth={}, rightDepth={}", root.value, depth.get(),
//						leftDepth.get(), rightDepth.get());
				return true;
			}
		}
		return false;
	}

	/**
	 * 子树高度和平衡分别遍历，两次遍历
	 *
	 * @param root
	 * @return
	 */
	public static boolean isBalance2(BinaryTreeNode root) {
		boolean isEmptyOrLeaf = root == null || (root.leftChild == null && root.rightChild == null);
		if (isEmptyOrLeaf) {
			return true;
		}
		int leftDepth = btNodeDepth(root.leftChild);
		int rightDepth = btNodeDepth(root.rightChild);
		int diff = leftDepth - rightDepth;
		if (diff <= 1 && diff >= -1) {
			return isBalance2(root.leftChild) && isBalance2(root.rightChild);
		}
		return false;
	}

}
