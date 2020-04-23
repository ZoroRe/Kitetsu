package me.zoro.kitetsu.company;

/**
 * @author luguanquan
 * @date 2020-03-07 16:48
 * @email luguanquans@qq.com
 * 这里没啥，主要是以前测试下，但可以看看相关的单元测试，用反射的方式测了这个方法。
 */
public class CompanyOne {
	private static final int MONTH_AFTER_FIRST_QUARTER = 3;
	private static final int MONTH_AFTER_SECOND_QUARTER = 6;
	private static final int MONTH_AFTER_THIRD_QUARTER = 9;
	/**
	 * 获取季度报告的标题
	 *
	 * @param currentYear
	 * @param currentMonth
	 * @return
	 */
	private String formatQuarterComplianceTitle(Integer currentYear, Integer currentMonth) {
		if (currentMonth < MONTH_AFTER_FIRST_QUARTER) {
			return (currentYear - 1) + "年第4季度合规报告";
		}
		if (currentMonth < MONTH_AFTER_SECOND_QUARTER) {
			return currentYear + "年第1季度合规报告";
		}
		if (currentMonth < MONTH_AFTER_THIRD_QUARTER) {
			return currentYear + "年第2季度合规报告";
		}
		return currentYear + "年第3季度合规报告";
	}
}
