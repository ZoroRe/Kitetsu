package me.zoro.kitetsu.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luguanquan
 * @date 2020-05-09 14:37
 */
@DisplayName("手写请求合并测试")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class MergeRequestRestServiceTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	private String baseUrl = "http://localhost:10001/kitetsu";

	@Test
	@DisplayName("批量查用户测试")
	public void queryUsersBatch() {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		int totalCount = 2000;
		CountDownLatch countDownLatch = new CountDownLatch(totalCount);
		CountDownLatch countDownFinish = new CountDownLatch(totalCount);
		for (int i = 0; i < totalCount; i++) {
			final int id = i;
			executorService.execute(() -> {
				try {
					countDownLatch.await();
				} catch (Exception e) {
					log.error("CountDownLatch");
				}
				log.info("发起请求:id={}", id);
				String resp = testRestTemplate.getForObject(baseUrl + "/user/merge/get?id=" + id, String.class);
				log.info("返回结果:id={},resp={}", id, resp);
				countDownFinish.countDown();
			});
			countDownLatch.countDown();
		}
		// 因为上面请求是异步，如果这个方法执行完，就会关闭接口环境，无法请求，这里增加一个等待避免结束
		try {
			countDownFinish.await();
		} catch (Exception e) {

		}
		log.info("全部请求完成");
	}
}
