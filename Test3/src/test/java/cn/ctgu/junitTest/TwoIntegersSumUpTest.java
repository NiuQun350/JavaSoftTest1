package cn.ctgu.junitTest;

import cn.ctgu.junit.TwoIntegersSumUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 1.上述实验示例加法器添加约束:sum<100，实现代码，并按照边界值分析方法设计测试用例，并使用Junit框架测试之；
 * 下方的无效输入测试用例 + 有效输入测试用例 = 整数求和最坏情况测试用例
 * @2021/4/2
 * @NiuQun
 */
public class TwoIntegersSumUpTest {
  private final TwoIntegersSumUp adder = new TwoIntegersSumUp();

    // 无效输入测试用例
    @ParameterizedTest(name = "num1={0}, num2={1}")
    @CsvSource({
            "0,50",
            "98,50",
            "99,50",
            "100,50",
            "50,0",
            "50,98",
            "50,99",
            "50,100",
            "50,50",
    })
    void sumInvalidCases(int num1, int num2) {
        Assertions.assertThrows(
                AssertionError.class,
                () -> adder.sum(num1, num2));
    }

    //有效输入测试用例
    @ParameterizedTest(name = "num1={0}, num2={1}, expected={2}")
    @CsvSource({
            "1,50,51",
            "2,50,52",
            "50,1,51",
            "50,2,52"
    })
    void sumValidCases(int num1, int num2, int expected) {
        Assertions.assertEquals(expected, adder.sum(num1, num2));
    }


    //采用参数生成方法实现有效输入测试用例
    @ParameterizedTest(name = "num1={0}, num2={1}, expected={2}")
    @MethodSource("generator")
    void sumValidCasesWithMethodParam(int num1, int num2, int expected) {
        Assertions.assertEquals(expected, adder.sum(num1, num2));
    }

    // 参数生成方法必须为static
    private static Stream<Arguments> generator(){
        return Stream.of(
                Arguments.of(1,50,51),
                Arguments.of(2,50,52),
                Arguments.of(50,1,51),
                Arguments.of(50,2,52)
                );
    }
}
