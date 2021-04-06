package cn.ctgu.junitTest;


import cn.ctgu.junit.Commission;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class CommissionTest {
    private final Commission commission = new Commission();

    @ParameterizedTest
    @CsvFileSource(resources = "/commission/所有类型参数测试_salesStatistics.csv", numLinesToSkip = 1, encoding = "UTF-8")
    public void salesStatisticsTest(int number, int lock, int stock, int barrel, String expected) {
        System.out.println("测试用例" + number + ":" + stock + ":" + barrel + ":" + expected);
        String result = commission.salesStatistics(lock, stock, barrel);
        assertEquals(expected, result);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/commission/所有类型参数测试_commission.csv", numLinesToSkip = 1, encoding = "UTF-8")
    public void calculateCommissionTest(int number, int lock, int stock, int barrel, String expected) {
        System.out.println("测试用例" + number + ":" + stock + ":" + barrel + ":" + expected);
        String result = commission.calculateCommission(lock, stock, barrel);
        assertEquals(result, expected);
    }
}