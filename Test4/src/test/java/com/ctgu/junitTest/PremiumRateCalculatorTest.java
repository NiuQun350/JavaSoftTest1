package com.ctgu.junitTest;

import com.ctgu.junit.PremiumRateCalculator;
import com.ctgu.junit.ValueParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @2021/4/11
 * @NiuQun
 */
public class PremiumRateCalculatorTest {
    private final PremiumRateCalculator premiumRate = new PremiumRateCalculator();
    @ParameterizedTest
    @CsvFileSource(resources = "/有效等价类参数测试.csv", numLinesToSkip = 1, encoding = "UTF-8")
    @DisplayName("有效等价类测试")
    public void PremiumRateValidValueTest(int number, int age, char sex, String marriage, int raise, String expected) {
        System.out.println("测试用例" + number + ":" +age + ":" + sex + ":" + marriage + ":" + raise);

        DecimalFormat df=new DecimalFormat("0.0000");
        double rate = premiumRate.rate(age, sex, marriage, raise);
        assertEquals(expected, df.format(rate));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/无效等价类参数测试.csv", numLinesToSkip = 1, encoding = "UTF-8")
    @DisplayName("无效等价类测试")
    public void PremiumRateInvalidValueTest(int number, int age, char sex, String marriage, int raise, String expected) {
        System.out.println("测试用例" + number + ":" + age + ":" + sex + ":" + marriage + ":" + raise);

        Throwable throwable = assertThrows(ValueParseException.class, () ->{
            premiumRate.rate(age, sex, marriage, raise);
        });
        assertAll(
                () -> assertEquals(expected, throwable.getMessage())
        );
    }
}
