package com.ctgu.junit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;

/**
 * 需求：电话账单计费
 *
 * 对于电话账单来说，当春季和秋季标准时间与夏令时时间进行转换时会产生一个很有意思的问题:
 * 1.春季，这种转换发生在(3月某个)星期日凌晨2:00点，这时要将时钟设置为凌晨3:00点；
 * 2.秋季，转换通常在11月的第一个星期日，时钟要从2:59:59调回2:00:00。
 * 3.请为长途电话服务函数开发计费类，使用决策表构建测试用例并使用Junit5测试。
 *
 * 采用如下计费规则计算通话费。
 * 1.通话时间小于等于20分钟时，每分钟收费0.05美元，通话时间不足1分钟按1分钟计算。
 * 2.通话时间大于20分钟时，收费1.00美元，外加超过20分钟的部分每分钟0.10美元；
 * 3.不到1分钟按1分钟计算
 *
 * 假设：
 * 1.通话计费时间从被叫方应答开始计算，到呼叫方挂机时结束；
 * 2.通话时间的秒数向上进位到分钟；
 * 3.没有超过30个小时的通话。
 */
public class HoldingTimeByZoned {
    private static final Logger log = LoggerFactory.getLogger(HoldingTimeByZoned.class);

    private ZonedDateTime startingTime; //开始通话时间
    private ZonedDateTime endingTime;   //结束通话时间

    public HoldingTimeByZoned(ZonedDateTime startingTime, ZonedDateTime endingTime) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    /**
     * 获取通话时间
     * 
     * @return
     */
    public long getHoldingTime() {
        long minute = 0; // minute是计费通话时长，单位：分钟
        long between; // between是真实通话时长，单位：秒
        
        ZoneId zoneId = startingTime.getZone();
        ZoneRules zoneRules = zoneId.getRules();

        Boolean isDstOfStart = zoneRules.isDaylightSavings( startingTime.toInstant() );
        Boolean isDstOfEnd = zoneRules.isDaylightSavings( endingTime.toInstant() );
        
        log.info("start:{},夏令时:{},end:{},夏令时:{}",
                startingTime.toLocalDateTime(),
                isDstOfStart,
                endingTime.toLocalDateTime(),
                isDstOfEnd
                );
        
        between = endingTime.toEpochSecond() - startingTime.toEpochSecond();
        
        if (between > 60)
            minute = (long) Math.ceil((double)between/60);
        else
            minute = (between + 59) / 60;//不到一分钟，算一分钟


        log.info("between:{},minutes:{}", between, minute);
        
        if (minute > 1800 || minute <= 0) {
            throw new RuntimeException("通话时间不正确");
        } else {
            return minute;
        }
    }
}