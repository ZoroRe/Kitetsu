package me.zoro.kitetsu.java;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author luguanquan
 * @date 2020-04-10 21:42
 * <p>
 * 1.经典问题 synchronized 和 lock 的区别
 */
@Slf4j
public class LockReadme {

	/**
	 * 阅读 ReentrantLock 相关源码
	 * 这个是比较像 synchronized 的锁，因此可以聊聊两者比较
	 */
	public void readReentrantLock() {
		ReentrantLock reentrantLock = new ReentrantLock();
		// 内部调用 Sync.lock(),Sync的实现包括 FairSync 和 NonfairSync,
		// 在 NonfairSync.lock()中，首先通过 unsave.compareAndSetState() 获取锁,获取失败调用acquire进入 AQS  同步等待队列竞争锁
		// 在 添加到同步队列中 acquireQueued, 内部通过for(;;){...}进行自旋，然后如果shouldParkAfterFailedAcquire 为true后直接
		// 调parkAndCheckInterrupt()让线程进入阻塞状态,内部是调用LockSupport.park(this),而LockSupport内部也是调的 UNSAFE ，
		// Unsafe.class 定义了很多 native 方法实现线程的挂起等操作
		reentrantLock.lock();
		try {
		} catch (Exception e) {
			log.info("{}", e);
		} finally {
			reentrantLock.unlock();
		}
		// 内部直接调用sync.nonfairTryAcquire(1)获取锁，尝试获取，不等待，通过 CAS  获取，
		// 如果失败再判断当前持有锁的是自身，是就重入计数增加,否则直接返回false
		reentrantLock.tryLock();

	}

	/**
	 * 阅读 ReadWriteLock相关源码
	 */
	public void readReadWriteLock() {
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		readWriteLock.readLock().tryLock();
		readWriteLock.writeLock().tryLock();
	}

	/**
	 * 阅读 CountDownLatch 相关源码
	 */
	public void readCountDownLatch() {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			log.info("{}", e);
		}
		countDownLatch.countDown();
	}

	/**
	 * 阅读 semaphore 相关源码
	 */
	public void readSemaphore() {
		Semaphore semaphore = new Semaphore(8);
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			log.info("{}", e);
		}
		semaphore.release();
	}

	public void readStampedLock(){
		StampedLock stampedLock = new StampedLock();
		stampedLock.tryReadLock();
	}
}
