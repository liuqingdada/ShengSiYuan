package com.shengsiyuan.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class JodaTest2 {
    private static final String TIME_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss:SSS";

    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        System.out.println(today.toString(TIME_FORMAT_ALL));
        System.out.println(tomorrow.toString(TIME_FORMAT_ALL));

        System.out.println("===========");

        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1.toString(TIME_FORMAT));

        System.out.println("===========");

        LocalDate localDate = new LocalDate();
        System.out.println(localDate);
        LocalDateTime localDateTime = new LocalDateTime();
        System.out.println(localDateTime);

        localDate = localDate.plusMonths(4)
                             .dayOfMonth()
                             .withMaximumValue();
        System.out.println(localDate);

        System.out.println("============");

        // 计算两年前第三个月最后一天日期
        DateTime d2 = today.minusYears(2)    // 操作年份
                           .monthOfYear()    // 操作月份
                           .setCopy(3)
                           .dayOfMonth()     // 操作天数
                           .withMaximumValue();
        System.out.println(d2.toString(TIME_FORMAT));
    }
}
