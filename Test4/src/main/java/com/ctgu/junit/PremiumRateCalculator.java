package com.ctgu.junit;

/**
 * 需求：保险公司人寿保险保费计算程序
 * 1.某保险公司人寿保险的保费计算方式为： 保费＝投保额×保险费率
 * 2.其中，保险费率根据年龄、性别、婚姻状况和抚养人数的不同而有所不同，体现在不同年龄、性别、婚姻状况和抚养人数
 * 3.点数设定不同，10点及10点以上保险费率为0.6%，10点以下保险费率为0.1%；
 * 4. 而点数又是由投保人的年龄、性别、婚姻状况和抚养人数来决定。
 *
 * @2021/4/10
 * @NiuQun
 */
public class PremiumRateCalculator {
    private int age;
    private char sex;
    private String marriage;
    private int raise;
    private double insurance;

    /**
     * 年龄要求取值范围为[1, 100]中的整数
     * @return
     */
    public double getAgePoint(int age) {
        if (age >= 1 && age <= 99) {
            if (age >= 20 && age <= 39) {
                return 6;
            } else if (age >= 40 && age <= 59) {
                return 4;
            } else {
                return 2;
            }
        }
        throw new ValueParseException("输入不合法");
    }

    /**
     * 性别取值要求：'M'为男性，'F'为女性
     * @return
     */
    public double getSexPoint(char sex) {
        String regex = "[MF]";
        if (("" +sex).matches(regex)) {
            if (sex == 'M') {
                return 4;
            } else if (sex == 'F') {
                return 3;
            }
        }
        throw new ValueParseException("输入不合法");
    }

    /**
     * 婚姻状况要求："已婚" 和 "未婚两种状态"
     * @return
     */
    public double getMarriagePoint(String marriage) {
        if ("已婚".equals(marriage)) {
            return 3;
        } else if ("未婚".equals(marriage)) {
            return 5;
        }
        throw new ValueParseException("输入不合法");
    }

    public double getRaisePoint(int raise) {
        String regex = "[0-9]";
        if( ("" + raise).matches(regex) ) {
            if (raise >= 1 && raise <= 9) {
                if (raise >= 0 && raise <= 6) {
                    return raise * 0.5;
                } else {
                    return 6;
                }
            }
        }
        throw new ValueParseException("输入不合法");
    }

    /**
     * 根据点数，获取保险率
     * @param age
     * @param sex
     * @param marriage
     * @param raise
     * @return
     */
    public double rate(int age, char sex, String marriage, int raise) {
        double point = getAgePoint(age) + getSexPoint(sex) + getMarriagePoint(marriage) + getRaisePoint(raise);
        if (point >= 8 && point <= 19) {
            if (point >= 10) {
                return 0.0006;
            }else {
                return 0.0001;
            }
        }

        throw new ValueParseException("输入不合法");
    }
}
