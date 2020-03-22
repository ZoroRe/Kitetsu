package me.zoro.kitetsu.company;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author luguanquan
 * @date 2020-03-20 07:39
 */
@DisplayName("BD go go go")
@Slf4j
public class ByteDanceTest {

	@DisplayName("countOfSn 测试")
	@Test
	public void countOfSnTest() {
		assertEquals(0, ByteDance.countOfSn(-1));
		assertEquals(1, ByteDance.countOfSn(0));
		assertEquals(3, ByteDance.countOfSn(1));
		assertEquals(7, ByteDance.countOfSn(2));
		assertEquals(15, ByteDance.countOfSn(3));
	}

	@DisplayName("valueKOfSn 测试")
	@Test
	public void valueKOfSnTest() {

		// S0={1}
		assertEquals(1, ByteDance.valueKOfSn(0, 0));
		assertNull(ByteDance.valueKOfSn(0, 2));
		// S1={1, 2, 1}
		assertNull(ByteDance.valueKOfSn(1, 4));
		assertEquals(1, ByteDance.valueKOfSn(1, 0));
		assertEquals(2, ByteDance.valueKOfSn(1, 1));
		assertEquals(1, ByteDance.valueKOfSn(1, 2));
		// S2={1,2,1,3,1,2,1}
		assertEquals(1,ByteDance.valueKOfSn(2, 0));
		assertEquals(2,ByteDance.valueKOfSn(2, 1));
		assertEquals(1,ByteDance.valueKOfSn(2, 2));
		assertEquals(3,ByteDance.valueKOfSn(2, 3));
		assertEquals(1,ByteDance.valueKOfSn(2, 4));
		assertEquals(2,ByteDance.valueKOfSn(2, 5));
		assertEquals(1,ByteDance.valueKOfSn(2, 6));
	}

	@DisplayName("树深度 测试")
	@Test
	public void btNodeDepthTest() {
		ByteDance.BinaryTreeNode root = null;
		assertEquals(0, ByteDance.btNodeDepth(root));

		root = new ByteDance.BinaryTreeNode(5);
		assertEquals(1, ByteDance.btNodeDepth(root));

		root.leftChild = new ByteDance.BinaryTreeNode(3);
		assertEquals(2, ByteDance.btNodeDepth(root));

		root.rightChild = new ByteDance.BinaryTreeNode(6);
		assertEquals(2, ByteDance.btNodeDepth(root));

		root.rightChild.rightChild = new ByteDance.BinaryTreeNode(8);
		assertEquals(3, ByteDance.btNodeDepth(root));

		root.rightChild.rightChild.leftChild = new ByteDance.BinaryTreeNode(7);
		assertEquals(4, ByteDance.btNodeDepth(root));
	}

	@DisplayName("是否平衡树-单次遍历,遍历同时获取高度 测试")
	@Test
	public void isBalanceTest() {
		ByteDance.BinaryTreeNode root = null;
		assertTrue(ByteDance.isBalance(root, new AtomicInteger(0)));

		root = new ByteDance.BinaryTreeNode(10);
		assertTrue(ByteDance.isBalance(root, new AtomicInteger(0)));

		root.leftChild = new ByteDance.BinaryTreeNode(8);
		assertTrue(ByteDance.isBalance(root, new AtomicInteger(0)));

		root.leftChild.rightChild = new ByteDance.BinaryTreeNode(9);
		assertFalse(ByteDance.isBalance(root, new AtomicInteger(0)));

		root.rightChild = new ByteDance.BinaryTreeNode(14);
		assertTrue(ByteDance.isBalance(root, new AtomicInteger(0)));

		root.rightChild.leftChild = new ByteDance.BinaryTreeNode(12);
		assertTrue(ByteDance.isBalance(root, new AtomicInteger(0)));

		root.rightChild.rightChild = new ByteDance.BinaryTreeNode(15);
		assertTrue(ByteDance.isBalance(root, new AtomicInteger(0)));

	}

	@DisplayName("是否平衡树-两次遍历，判断平衡和获取高度分开 测试")
	@Test
	public void isBalance2Test() {
		ByteDance.BinaryTreeNode root = null;
		assertTrue(ByteDance.isBalance2(root));

		root = new ByteDance.BinaryTreeNode(10);
		assertTrue(ByteDance.isBalance2(root));

		root.leftChild = new ByteDance.BinaryTreeNode(8);
		assertTrue(ByteDance.isBalance2(root));

		root.leftChild.rightChild = new ByteDance.BinaryTreeNode(9);
		assertFalse(ByteDance.isBalance2(root));

		root.rightChild = new ByteDance.BinaryTreeNode(14);
		assertTrue(ByteDance.isBalance2(root));

		root.rightChild.leftChild = new ByteDance.BinaryTreeNode(12);
		assertTrue(ByteDance.isBalance2(root));

		root.rightChild.rightChild = new ByteDance.BinaryTreeNode(15);
		assertTrue(ByteDance.isBalance2(root));

	}
}
