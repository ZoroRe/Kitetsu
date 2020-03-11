package me.zoro.kitetsu.cron;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luguanquan
 * @date 2020-03-11 21:09
 */
@DisplayName("Cron表达式测试")
public class CronStudyTest {

	@DisplayName("每季度执行定时任务测试")
	@Test
	public void runQuarterlyTest() {
		SimpleTriggerContext triggerContext = new SimpleTriggerContext();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean hasException = false;
		try {
			Date lastScheduleTime = simpleDateFormat.parse("2020-01-01 09:00:00");
			Date lastExecuteTime = simpleDateFormat.parse("2020-01-01 09:00:00");
			Date lastCompleteTime = simpleDateFormat.parse("2020-01-01 09:01:00");
			triggerContext.update(lastScheduleTime, lastExecuteTime, lastCompleteTime);
		} catch (ParseException e) {
			hasException = true;
		}
		assertEquals(false, hasException);
		String[] triggerTime = new String[]{"2020-04-01 09:00:00", "2020-07-01 09:00:00", "2020-10-01 09:00:00",
				"2021-01-01 09:00:00", "2021-04-01 09:00:00", "2021-07-01 09:00:00", "2021-10-01 09:00:00"};
		String cron = null;
		Method[] methods = CronStudy.class.getMethods();
		for (Method method : methods) {
			if ("runQuarterly".equals(method.getName())) {
				cron = method.getAnnotation(Scheduled.class).cron();
			}
		}
		CronTrigger trigger = new CronTrigger(cron);
		for (int i = 0; i < 6; i++) {
			String nextExecuteTime = simpleDateFormat.format(trigger.nextExecutionTime(triggerContext));
			assertEquals(triggerTime[i], nextExecuteTime);
			// 更新上一次完成时间为当前获取到的时间
			Date finishDate = null;
			try {
				finishDate = simpleDateFormat.parse(nextExecuteTime);
			} catch (ParseException e) {
				hasException = true;
			}
			assertEquals(false, hasException);
			triggerContext.update(finishDate, finishDate, finishDate);
		}
	}

	@DisplayName("每天执行定时任务测试")
	@Test
	public void runDailyTest() {
		SimpleTriggerContext triggerContext = new SimpleTriggerContext();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean hasException = false;
		try {
			Date lastScheduleTime = simpleDateFormat.parse("2020-01-01 03:00:00");
			Date lastExecuteTime = simpleDateFormat.parse("2020-01-01 03:00:00");
			Date lastCompleteTime = simpleDateFormat.parse("2020-01-01 03:01:00");
			triggerContext.update(lastScheduleTime, lastExecuteTime, lastCompleteTime);
		} catch (ParseException e) {
			hasException = true;
		}
		assertEquals(false, hasException);
		String[] triggerTime = new String[]{"2020-01-02 03:00:00", "2020-01-03 03:00:00", "2020-01-04 03:00:00",
				"2020-01-05 03:00:00", "2020-01-06 03:00:00", "2020-01-07 03:00:00",};
		String cron = null;
		Method[] methods = CronStudy.class.getMethods();
		for (Method method : methods) {
			if ("runDaily".equals(method.getName())) {
				cron = method.getAnnotation(Scheduled.class).cron();
			}
		}
		CronTrigger trigger = new CronTrigger(cron);
		for (int i = 0; i < 6; i++) {
			String nextExecuteTime = simpleDateFormat.format(trigger.nextExecutionTime(triggerContext));
			assertEquals(triggerTime[i], nextExecuteTime);
			// 更新上一次完成时间为当前获取到的时间
			Date finishDate = null;
			try {
				finishDate = simpleDateFormat.parse(nextExecuteTime);
			} catch (ParseException e) {
				hasException = true;
			}
			assertEquals(false, hasException);
			triggerContext.update(finishDate, finishDate, finishDate);
		}
	}

	@DisplayName("每五分钟执行测试")
	@Test
	public void runIntervalTest() {
		Method[] methods = CronStudy.class.getMethods();
		for (Method method : methods) {
			if ("runInterval".equals(method.getName())) {
				assertEquals(300, method.getAnnotation(Scheduled.class).fixedRate());
				break;
			}
		}
	}
}
