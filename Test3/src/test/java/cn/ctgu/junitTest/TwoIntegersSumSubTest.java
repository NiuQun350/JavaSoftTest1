package cn.ctgu.junitTest;


import cn.ctgu.junit.TwoIntegersSumSub;
import cn.ctgu.junit.TwoIntegersSumUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 2.加法器修改为支持加法和减法，两个整数在1到99之间[1,99],并且包括1和99，且要求结果result[0,99],
 * 实现代码，并按照边界值分析方法设计测试用例，并使用Junit框架测试之
 * 下方的无效输入测试用例 + 有效输入测试用例 = 整数加法最坏情况测试用例
 *
 * @2021/4/2
 * @NiuQun
 */
public class TwoIntegersSumSubTest {
  private final TwoIntegersSumSub adder = new TwoIntegersSumSub();

    // 加法器无效输入测试用例
    @ParameterizedTest(name = "num1={0}, num2={1}, result={3}")
    @CsvSource({
            "0,50",
            "98,50",
            "99,50",
            "100,50",
            "50,0",
            "50,98",
            "50,99",
            "50,100",
            "50,50"
    })
    void sumInvalidCases(int num1, int num2) {
        Assertions.assertThrows(
                AssertionError.class,
                () -> adder.sum(num1, num2));
    }

    //加法器有效输入测试用例
    @ParameterizedTest(name = "num1={0}, num2={1}, expected={2}")
    @CsvSource({
            "1,50,51",
            "2,50,52",
            "50,1,51",
            "50,2,52",
    })
    void sumValidCases(int num1, int num2, int expected) {
        Assertions.assertEquals(expected, adder.sum(num1, num2));
    }

    //加法器采用参数生成方法实现有效输入测试用例
    @ParameterizedTest(name = "num1={0}, num2={1}, expected={2}")
    @MethodSource("generatorSum")
    void sumValidCasesWithMethodParam(int num1, int num2, int expected) {
        Assertions.assertEquals(expected, adder.sum(num1, num2));
    }

    // 加法器参数生成方法必须为static
    private static Stream<Arguments> generatorSum(){
        return Stream.of(
                Arguments.of(1,50,51),
                Arguments.of(2,50,52),
                Arguments.of(50,1,51),
                Arguments.of(50,2,52)
        );
    }



  // 减法器无效输入测试用例
  @ParameterizedTest(name = "num1={0}, num2={1}, result={3}")
  @CsvSource({
          "0,50",
          "1,50",
          "2,50",
          "100,50",
          "50,0",
          "50,98",
          "50,99",
          "50,100"
  })
  void subInvalidCases(int num1, int num2) {
    Assertions.assertThrows(
            AssertionError.class,
            () -> adder.sub(num1, num2));
  }

  //减法器有效输入测试用例
  @ParameterizedTest(name = "num1={0}, num2={1}, expected={2}")
  @CsvSource({
          "98,50,48",
          "99,50,49",
          "50,1,49",
          "50,2,48",
          "50,50,0"
  })
  void subValidCases(int num1, int num2, int expected) {
    Assertions.assertEquals(expected, adder.sub(num1, num2));
  }

  //加法器采用参数生成方法实现有效输入测试用例
  @ParameterizedTest(name = "num1={0}, num2={1}, expected={2}")
  @MethodSource("generatorSub")
  void subValidCasesWithMethodParam(int num1, int num2, int expected) {
    Assertions.assertEquals(expected, adder.sub(num1, num2));
  }

  // 加法器参数生成方法必须为static
  private static Stream<Arguments> generatorSub(){
    return Stream.of(
            Arguments.of(98,50,48),
            Arguments.of(99,50,49),
            Arguments.of(50,1,49),
            Arguments.of(50,2,48),
            Arguments.of(50,50,0)
    );
  }
}
