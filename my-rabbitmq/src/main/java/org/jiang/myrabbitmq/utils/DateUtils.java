package org.jiang.myrabbitmq.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description
 * @Author li.linhua
 * @Date 2020/6/5
 * @Version 1.0
 */
public class DateUtils {

    public static String getNow() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss").format(LocalDateTime.now());
    }
}
