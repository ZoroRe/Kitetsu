package me.zoro.kitetsu.cron;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author luguanquan
 * @date 2020-03-11 21:02
 *
 * cron 表达式: 秒 分 时 日 月 日(星期) 年
 * 日，月都是 1 开始, 除了星期外，其他值如果是任意值用 * ，星期的任意值用 ?
 * 一般如果没有指定年，最后一位不需要用
 * 指定多个值可以用 , 隔开，如  0 0 9 1 1,4,7,10 ? 即为每年的1、4、7、10月1日9点0分0秒
 * 注意： 要在 Application 使用注解 @EnableScheduling
 */
public class CronStudy {

	/**
	 * 每个季度执行,1、4、7、10月1日9点
	 */
	@Scheduled(cron = "0 0 9 1 1,4,7,10 ?")
	public void runQuarterly(){

	}

	/**
	 * 每天凌晨3点执行
	 */
	@Scheduled(cron = "0 0 3 * * ?")
	public void runDaily(){

	}

	/**
	 * 每隔五分钟执行
	 */
	@Scheduled(fixedRate = 5 * 60)
	public void runInterval(){

	}
}
