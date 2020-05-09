package me.zoro.kitetsu.net;

import lombok.extern.slf4j.Slf4j;
import me.zoro.kitetsu.exception.GlobalException;
import me.zoro.kitetsu.model.ApiResponseDTO;
import me.zoro.kitetsu.model.IDEntity;
import me.zoro.kitetsu.model.IDSEntity;
import me.zoro.kitetsu.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author luguanquan
 * @date 2020-05-09 13:03
 * <p>
 * 这里展示一个请求合并的方式，假设这是服务A，并发量非常高，同时它会调用服务B(B可能是一个微服务，也可能是真正去数据库的服务)去真正查询，
 * 如果A都每次直接调B,必然导致AB并发量一致，负荷达到double,加入A在调用B时能经过一个请求合并，比如几百个请求合在一起一次请求B，就能在B上
 * 大大减少并发量，从而提高性能
 * <p>
 * <p>
 * 提高性能的方式，包括缓存(redis)、缓冲(kafka、mq)、请求合并
 * <p>
 * 这里也类似以下博客展示的一种手写合并请求的方式
 * <p>
 * 手写方式参见：https://blog.csdn.net/u014395955/article/details/103628633
 * 使用 Hystrix 方式: https://www.cnblogs.com/hellxz/p/9071163.html
 */
@RestController
@Slf4j
public class MergeRequestRestService {

	@Autowired
	private UserRestService userRestService;


	private LinkedBlockingQueue<RealRequest> requestQueue = new LinkedBlockingQueue<>(2000);
	private ExecutorService executorService;

	@PostConstruct
	private void init() {
		executorService = Executors.newCachedThreadPool();
	}

	@Scheduled(initialDelay = 10, fixedRate = 20)
	public void mergeRun() {
		synchronized (requestQueue) {
			if (requestQueue.size() == 0) {
				return;
			}
			List<RealRequest> currentRequestList = new ArrayList<>();
			IDSEntity idsEntity = new IDSEntity();
			List<Long> ids = new ArrayList<>();
			idsEntity.setIds(ids);
			while (requestQueue.size() > 0) {
				RealRequest item = requestQueue.poll();
				ids.add(item.id);
				currentRequestList.add(item);
			}
			// 这里提交到线程池避免一次合并就得等他请求结束，提交的话释放锁，队列重新接受数据
			executorService.execute(() -> {

				log.info("合并了{}个请求,现在开始批处理", currentRequestList.size());
				ResponseEntity<List<ApiResponseDTO<UserDO>>> all = userRestService.queryUsersBatch(idsEntity);
				HashMap<Long, ApiResponseDTO<UserDO>> response = new HashMap<>();
				if (all.getStatusCode().is2xxSuccessful()) {
					for (ApiResponseDTO<UserDO> item : all.getBody()) {
						response.put(item.getData().getId(), item);
					}
					for (RealRequest item : currentRequestList) {
						item.future.complete(ResponseEntity.ok(response.get(item.id)));
					}
				} else {
					//全部请求都返回出错,这里简单粗暴实现了,真实研发需要更好处理
					ApiResponseDTO error = new ApiResponseDTO();
					error.setCode(-1);
					error.setMessage("Not Found.");
					for (RealRequest item : currentRequestList) {
						item.future.complete(ResponseEntity.ok(error));
					}
				}
			});
		}
	}

	@GetMapping("kitetsu/user/merge/get")
	public ResponseEntity<ApiResponseDTO<UserDO>> queryUser(IDEntity idEntity) {
		RealRequest realRequest = new RealRequest();
		realRequest.id = idEntity.getId();
		realRequest.future = new CompletableFuture<>();
		requestQueue.add(realRequest);
		try {
			return realRequest.future.get();
		} catch (Exception e) {
			log.error("请求异常了");
			throw new GlobalException();
		}
	}

	/**
	 * 封装请求的类
	 */
	static class RealRequest {
		public long id;
		public CompletableFuture<ResponseEntity<ApiResponseDTO<UserDO>>> future;
	}
}
