package com.nowcoder.community.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {
    public void getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println("当前时间：" + sdf.format(now));
    }

    public Date getAfterTime(Date date, int minutes) {
        long time = minutes * 60 * 1000;//30分钟
        return new Date(date.getTime() + time);//30分钟后的时间
    }
}
