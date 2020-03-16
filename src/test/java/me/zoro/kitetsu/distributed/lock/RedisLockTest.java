package me.zoro.kitetsu.distributed.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author luguanquan
 * @date 2020-03-16 17:13
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("Redis Lock 分布式锁测试")
@Slf4j
public class RedisLockTest {

	@Autowired
	private RedisLockJob redisLockJob;
	private static Integer jobResult1 = null;
	private static Long finishTime1 = null;
	private static Integer jobResult2 = null;
	private static Long finishTime2 = null;
	private static Integer jobResult3 = null;
	private static Long finishTime3 = null;
	private static Integer jobResult4 = null;
	private static Long finishTime4 = null;
	private static Integer jobResult5 = null;
	private static Long finishTime5 = null;
	private static Integer jobResult6 = null;
	private static Long finishTime6 = null;


	@DisplayName("分布式锁-等得到锁 测试")
	@Test
	public void finishInExpireTimeTest() {
		// 任务完成5s，等待时间20s
		boolean hasException = false;
		Thread thread1 = new Thread(() -> {
			try {
				jobResult1 = redisLockJob.finishInExpireTime(1);
				finishTime1 = System.currentTimeMillis();
			} catch (InterruptedException e) {

			}
		});
		thread1.start();

		//停止半秒，争取让 thread1 先执行，然后再开启 thread2
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			hasException = true;
		}
		Thread thread2 = new Thread(() -> {
			try {
				jobResult2 = redisLockJob.finishInExpireTime(2);
				finishTime2 = System.currentTimeMillis();
			} catch (InterruptedException e) {
			}
		});
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		}catch (InterruptedException e){

		}

		assertFalse(hasException);
		assertEquals(1, jobResult1.intValue());
		assertEquals(2, jobResult2.intValue());
		// 2等1完成后继续后才拿到锁，任务需要5s，预计两者完成时间 5s - 7 s 之间
		assertTrue(finishTime2 - finishTime1 > 5000);
		assertTrue(finishTime2 - finishTime1 < 7000);

	}

	@DisplayName("分布式锁-等不到锁 测试")
	@Test
	public void finishAfterExpireTimeTest() {
		// 任务完成需要10s, 等待超时 5s
		boolean hasException = false;
		Thread thread1 = new Thread(() -> {
			try {
				jobResult3 = redisLockJob.finishAfterExpireTime(1);
				finishTime3 = System.currentTimeMillis();
			} catch (InterruptedException e) {

			}
		});
		thread1.start();

		//停止半秒，争取让 thread1 先执行，然后再开启 thread2
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			hasException = true;
		}

		Thread thread2 = new Thread(() -> {
			try {
				jobResult4 = redisLockJob.finishAfterExpireTime(2);
				finishTime4 = System.currentTimeMillis();
			} catch (InterruptedException e) {
			}
		});
		Long thread2StartTime = System.currentTimeMillis();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		}catch (InterruptedException e){

		}

		// 线程2在 5s后超时，因此完成时间应该比线程开始时间设置差距4-7s,预估结果，会和机器有一定差异
		assertTrue(finishTime4 - thread2StartTime > 4000);
		assertTrue(finishTime4 - thread2StartTime < 7000);
		assertNull(jobResult4);
		// 线程1正常完成
		assertFalse(hasException);
		assertEquals(1, jobResult3.intValue());

	}

	@DisplayName("分布式锁-不用等待 测试")
	@Test
	public void notWaitTest() {
		boolean hasException = false;
		Thread thread1 = new Thread(() -> {
			try {
				jobResult5 = redisLockJob.notWait(1);
				finishTime5 = System.currentTimeMillis();
			} catch (InterruptedException e) {

			}
		});
		thread1.start();

		//停止半秒，争取让 thread1 先执行，然后再开启 thread2
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			hasException = true;
		}

		Thread thread2 = new Thread(() -> {
			try {
				jobResult6 = redisLockJob.notWait(1);
				finishTime6 = System.currentTimeMillis();
			} catch (InterruptedException e) {
			}
		});
		thread2.start();

		// 这里等待两个线程完成
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			hasException = true;
		}
		assertEquals(1, jobResult5.intValue());
		assertNull(jobResult6);
		// 不用等待，任务完成需要5s, 任务2开始要晚至少半秒，但是还有其他任务，预估6比5提前4s结束吧
		assertTrue(finishTime5 - finishTime6 > 4000);

	}
}
