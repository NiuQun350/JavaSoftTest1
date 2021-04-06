package cn.ctgu.junitTest;

import cn.ctgu.junit.TwoIntegers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * 该程序接收两个整数输入，并返回两个整数之后。两个整数在1到99之间[1,99],并且包括1和99
 */
class TwoIntegersTest {

  private final TwoIntegers adder = new TwoIntegers();

  // 无效输入测试用例
  @ParameterizedTest(name = "num1={0}, num2={1}")
  @CsvSource({
          "0, 50",    
          "100, 50",  
          "50, 0",    
          "50, 100", 
  })
  void sumInvalidCases(int num1, int num2) {
    Assertions.assertThrows(
        AssertionError.class,
        () -> adder.sum(num1, num2));
  }

  // 一般边界值测试用例
  //有效输入测试用例
  @ParameterizedTest(name = "num1={0}, num2={1}, result={2}")
  @CsvSource({
          "50, 50, 100",  
          "1, 50, 51", 
           "2,50,52",
          "99, 50, 149",  
          "98,50,148",
          "50, 50, 100", 
          "50, 1, 51", 
          "50, 2, 52",
          "50,99,149",
          "50,98,148",
  })
  void sumValidCases(int num1, int num2, int result) {
    Assertions.assertEquals(result, adder.sum(num1, num2));
  }

  //采用参数生成方法实现有效输入测试用例
  @ParameterizedTest(name = "num1={0}, num2={1}, result={2}")
  @MethodSource("generator")
  void sumValidCasesWithMethodParam(int num1, int num2, int result) {
    Assertions.assertEquals(result, adder.sum(num1, num2));
  }
  
// 参数生成方法必须为static
  private static Stream<Arguments> generator(){
      return Stream.of(
          Arguments.of(50,50,100),
          Arguments.of(1,50,51),
          Arguments.of(2,50,52),
          Arguments.of(99,50,149),
          Arguments.of(98,50,148),
          Arguments.of(50,50,100),
          Arguments.of(50,1,51),
          Arguments.of(50,2,52),
          Arguments.of(50,99,149),
          Arguments.of(50,98,148)
      );
  }
}