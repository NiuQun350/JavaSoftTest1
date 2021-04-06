package cn.ctgu.junitTest;


import cn.ctgu.junit.NextDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class NextDateTest {
    private final NextDate date = new NextDate();

    @ParameterizedTest
    @CsvFileSource(resources = "/nextDate/所有类型参数测试.csv", numLinesToSkip = 1, encoding = "UTF-8")
    public void paramNextDate(int number, int month, int day, int year, String expected) {
        System.out.println("测试用例"+number+":"+month+","+day+","+year+":"+expected);
        String result = date.nextDate(month, day, year);
        assertEquals(expected, result);
    }

    /*
    @ParameterizedTest
    @CsvFileSource(resources = "/nextDate/一般边界测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
    public void testWithCsvFileSource(int number, int month, int day, int year, String expected) {
        System.out.println("测试用例"+number+":"+month+","+day+","+year+":"+expected);
        String result = date.nextDate(month, day, year);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/nextDate/健壮测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
    public void testRobustCase(int number, int month, int day, int year, String expected) {
        System.out.println("测试用例"+number+":"+month+","+day+","+year+":"+expected);
        String result = date.nextDate(month, day, year);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/nextDate/健壮性最坏情况测试用例", numLinesToSkip = 1, encoding = "UTF-8")
    public void testBadRobustCase(int number, int month, int day, int year, String expected) {
        System.out.println("测试用例"+number+":"+month+","+day+","+year+":"+expected);
        String result = date.nextDate(month, day, year);
        assertEquals(expected, result);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/nextDate/弱一般等价类测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
    public void testEquivalenceCase(int number, int month, int day, int year, String expected) {
        System.out.println("测试用例"+number+":"+month+","+day+","+year+":"+expected);
        String result = date.nextDate(month, day, year);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/nextDate/最坏情况测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
    public void testWorstCase(int number, int month, int day, int year, String expected) {
        System.out.println("测试用例"+number+":"+month+","+day+","+year+":"+expected);
        String result = date.nextDate(month, day, year);
        assertEquals(expected, result);
    }

    */

}