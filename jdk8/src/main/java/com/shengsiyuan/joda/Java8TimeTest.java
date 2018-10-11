package com.shengsiyuan.joda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;

public class Java8TimeTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());


        LocalDate ld = LocalDate.of(2017, 3, 3);
        System.out.println(ld);


        LocalDate ld2 = LocalDate.of(2010, 3, 25);
        MonthDay monthDay = MonthDay.of(ld2.getMonth(), ld2.getDayOfMonth());
        MonthDay md = MonthDay.from(LocalDate.of(2011, 3, 25));

        if (monthDay.equals(md)) {
            System.out.println("同月同日");
        } else {
            System.out.println("不是同月同日");
        }

        System.out.println("=============");

        LocalTime time = LocalTime.now();
        System.out.println(time);
        System.out.println(time.plusHours(3)
                               .plusMinutes(30));
    }
}
