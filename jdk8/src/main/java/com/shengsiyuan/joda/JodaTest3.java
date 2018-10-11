package com.shengsiyuan.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class JodaTest3 {

    public static void main(String[] args) {
        // 标准UTC
        // 2014-11-04T09:22:54.876Z // 直接用不带时区的, 客户端自己去处理时区
        // 2014-11-04T09:22:54.876+08:00

        System.out.println(convertUTC2Date("2014-11-04T09:22:54.876Z"));

        System.out.println(convertDate2UTC(new Date()));

        System.out.println(convertDate2LocalByDateFormat(new Date(),
                                                         "yyyy-MM-dd HH:mm:ss"));
    }

    private static Date convertUTC2Date(String utc) {
        DateTime dateTime = DateTime.parse(utc,
                                           DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

        return dateTime.toDate();
    }

    private static String convertDate2UTC(Date javaDate) {
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }

    private static String convertDate2LocalByDateFormat(Date javaDate, String dateFormat) {
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);
    }
}
