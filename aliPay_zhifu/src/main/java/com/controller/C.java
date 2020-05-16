package com.controller;


import com.yibu.A;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Configuration
public class C <T> {







    @Bean
    public Object aa(){
        return geta();
    }

    @Bean
    public Object geta() {
        System.out.println("");
        return new Object();
    }

    public static void ss() {
        Clock clock = Clock.systemUTC();
        System.out.println(clock.getZone());
        System.out.println(clock.millis());
        LocalDateTime localDateTime=LocalDateTime.now();
        localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        Clock clock1 = Clock.systemDefaultZone();
        System.out.println(clock1.getZone());
        System.out.println(clock1.millis());
        System.out.println(new Date().getTime());
        ReentrantLock lock = new ReentrantLock();
        System.out.println(new Date().toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime().toEpochSecond(ZoneOffset.ofHours(8)));
        System.out.println(new Date().toInstant());
        System.out.println(LocalDateTime.ofInstant(new Date().toInstant(), ZoneOffset.of("+8")).toEpochSecond(ZoneOffset.of("+8")));

        // localDateTime 转成 只能转成 秒值 转不了毫秒值 本来就只有 日期和时间
        System.out.println(Instant.now().toEpochMilli());
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toEpochSecond(ZoneOffset.ofHours(8))*1000);
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        long l = System.currentTimeMillis();
        System.out.println(format.format(new Date(l)));

        cal.setTimeInMillis(l);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(format.format(new Date(cal.getTimeInMillis())));
        LocalDateTime of = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(1, 0, 22));
        System.out.println(of.toString());

    }
    public static class Qw<T>{
        public Qw(List<A> a){
            System.out.println(a);
         //   A a1 = a.get(0);
        }

        public T sss(T a){
            return a;
        }
    }
}
