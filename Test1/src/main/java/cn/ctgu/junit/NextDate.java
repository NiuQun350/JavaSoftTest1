package cn.ctgu.junit;

import java.util.Date;

/**
 * NextDate函数问题：NextDate函数输入为month(月份)、day(日期)和year(年)，输出为输入后一天的日期。
 * 例如，如果输入为：1964年8月16日，则输出为1964年8月17日。要求输入变量month、day和year都是整数值，
 * 并且满足以下条件：
 *     	Con1.  1≤month≤12
 *     	Con2.  1≤day≤31
 *     	Con3.  1900≤year≤2050
 * @2021/3/17
 * @NiuQun
 */
public class NextDate {

    /**
     * 在输入的日期合法的条件下，输入后一天的年月日
     * @param month
     * @param day
     * @param year
     * @return
     */
    public String nextDate(int month, int day, int year) {
        // 输入的日期不合法
        if (!dateIsLegal(month, day, year)) {
            return "非法输入";
        }

        // 输入的日期合法
        if (dateIsLegal(month, day+1, year)) {
            return year + "年" + month + "月" + (day+1) + "日";
        } else if (dateIsLegal(month + 1, 1, year)){
            return year + "年" + (month+1) + "月" + 1 + "日";
        }else {
            return (year+1)+ "年" + 1 + "月" + 1 +  "日";
        }
    }

    /**
     * 判断输入的年月日是否合法
     * @param month
     * @param day
     * @param year
     * @return 如果输入的年月日合法，返回true，否则，返回false
     */
    public boolean dateIsLegal(int month, int day, int year) {
        // 对输入的年，月，日进行基础判断
        if (year < 1900 || year > 2050 || month < 1 || day < 1) {
            return false;
        }

        // 详细判断
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            // 闰年
            if (month == 2) {return day <= 29;}
            else {return isLegalDay(month, day);}
        }else {
            // 平年
            if (month == 2) {return day <= 28;}
            else {return isLegalDay(month, day); }
        }
    }

    /**
     * 判断非2月输入的月份和日期是否正确
     * @param month
     * @param day
     * @return
     */
    private boolean isLegalDay(int month, int day) {
        if (month > 12) {
            return false;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return  day <= 30;
        } else {
            return day <= 31;
        }
    }
}