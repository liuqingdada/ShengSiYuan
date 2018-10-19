package com.shengsiyuan.joda;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Java8TimeTest {

    public static void main(String[] args) {
        Instant instant = Instant.now();
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

        System.out.println("--------------");

        LocalDate ld3 = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println(ld3);

        System.out.println("------");

        LocalDate ld4 = localDate.minus(2, ChronoUnit.MONTHS);
        System.out.println(ld4);
        System.out.println("----");

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println(clock.instant());
        System.out.println("-----");

        LocalDate ld5 = LocalDate.now();
        LocalDate ld6 = LocalDate.of(2017, 3, 19);
        System.out.println(ld5.isAfter(ld6));
        System.out.println(ld5.isBefore(ld6));
        System.out.println(ld5.equals(ld6));
        System.out.println("=======");

//        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
//        zoneIds.stream()
//               .collect(Collectors.toCollection(TreeSet::new))
//               .forEach(System.out::println);
        System.out.println("----------");

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);
        System.out.println("=====");

        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.isLeapYear());
        System.out.println("--------");

        YearMonth ym = YearMonth.of(2016,2);
        System.out.println(ym);
        System.out.println(ym.lengthOfMonth());
        System.out.println(ym.lengthOfYear());
        System.out.println(ym.isLeapYear());
        System.out.println("-------");

        LocalDate ld7 = LocalDate.of(2017,3,25);
        LocalDate ld8 = LocalDate.now();
        Period period = Period.between(ld7, ld8);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println("==========");

        Instant instant2 = Instant.now();
        System.out.println(instant2);

        System.out.println(Duration.between(instant, instant2).toMillis());
    }
}
