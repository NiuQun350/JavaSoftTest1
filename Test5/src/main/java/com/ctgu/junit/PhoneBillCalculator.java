package com.ctgu.junit;

import java.time.ZonedDateTime;

/**
 * @2021/4/17
 * @NiuQun
 */
public class PhoneBillCalculator {
    private ZonedDateTime startingTime; //开始通话时间
    private ZonedDateTime endingTime;   //结束通话时间



    public PhoneBillCalculator(ZonedDateTime startingTime, ZonedDateTime endingTime) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }


    /**
     * 采用如下计费规则计算通话费。
     * 1.通话时间小于等于20分钟时，每分钟收费0.05美元，通话时间不足1分钟按1分钟计算。
     * 2.通话时间大于20分钟时，收费1.00美元，外加超过20分钟的部分每分钟0.10美元；
     * 3.不到1分钟按1分钟计算
     * @return
     */
    public double billCalculator() {
        HoldingTimeByZoned holdingTimeByZoned = new HoldingTimeByZoned(startingTime, endingTime);
        long minute = holdingTimeByZoned.getHoldingTime();
        if (minute > 0 && minute <= 20) {
            return minute * 0.05;
        } else if (minute > 20 && minute <= 1800) {
            return 1 + (minute - 20) * 0.1;
        }
        throw new RuntimeException("通话时间不正确");

    }


}
