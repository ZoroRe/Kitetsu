package me.zoro.kitetsu.company;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("借坑 校验在某司写的一些功能测试")
@Slf4j
public class CompanyOneTest {

	@Test
	@DisplayName("合规报告标题测试")
	public void testFormatQuarterComplianceTitle() {
		CompanyOne companyOne = null;
		boolean hasException = false;
		Class<CompanyOne> clazz = CompanyOne.class;
		try {
			companyOne = CompanyOne.class.newInstance();
		} catch (IllegalAccessException e) {
			hasException = true;
			log.error("反射获取对象失败,e={}", e);
		} catch (InstantiationException e) {
			hasException = true;
			log.error("反射获取对象失败,e={}", e);
		}
		assertEquals(false, hasException);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);

		//设置可调用 private 方法
		Method method = null;
		try {
			method = clazz.getDeclaredMethod("formatQuarterComplianceTitle", Integer.class, Integer.class);
			method.setAccessible(true);
			String title = null;
			//1月1号
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2020);
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			title = (String) method.invoke(companyOne, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
			assertEquals("2020-01-01", simpleDateFormat.format(calendar.getTime()));
			assertEquals("2019年第4季度合规报告", title);

			calendar.set(Calendar.MONTH, 3);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			title = (String) method.invoke(companyOne, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
			assertEquals("2020-04-01", simpleDateFormat.format(calendar.getTime()));
			assertEquals("2020年第1季度合规报告", title);

			calendar.set(Calendar.MONTH, 6);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			title = (String) method.invoke(companyOne, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
			assertEquals("2020-07-01", simpleDateFormat.format(calendar.getTime()));
			assertEquals("2020年第2季度合规报告", title);

			calendar.set(Calendar.MONTH, 9);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			title = (String) method.invoke(companyOne, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
			assertEquals("2020-10-01", simpleDateFormat.format(calendar.getTime()));
			assertEquals("2020年第3季度合规报告", title);
		} catch (NoSuchMethodException e) {
			hasException = true;
			log.error("方法调用失败,e={}", e);
		} catch (IllegalAccessException e) {
			hasException = true;
			log.error("方法调用失败,e={}", e);
		} catch (InvocationTargetException e) {
			hasException = true;
			log.error("方法调用失败,e={}", e);
		}
		assertEquals(false, hasException);

	}

	@Test
	@DisplayName("合规季报定时任务Cron测试")
	public void testCronQuarterly() {
		// 注意这个首次触发时间点会根据电脑当前事件有所改变，不同时间段测试要修改值
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
		CronTrigger trigger = new CronTrigger("0 0 9 1 1,4,7,10 ?");
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

	@Test
	@DisplayName("合规年报定时任务Cron测试")
	public void testCronYearly() {
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
		String[] triggerTime = new String[]{"2021-01-01 09:00:00", "2022-01-01 09:00:00", "2023-01-01 09:00:00"};
		CronTrigger trigger = new CronTrigger("0 0 9 1 1 ?");
		for (int i = 0; i < 3; i++) {
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
}
