package cn.ctgu.junitTest;


import cn.ctgu.junit.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {
	private final Triangle triangle = new Triangle();

	@ParameterizedTest
	@CsvFileSource(resources = "/triangle/三角形一般边界测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
	void testWithCsvFileSource(int num, int a, int b, int c,String expected) {
		System.out.println("测试用例"+num+":"+a+","+b+","+c+":"+expected);
		String type = triangle.classify(a,b,c);
		assertEquals(expected,type);

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/triangle/三角形健壮测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
	void testRobustCase(int num, int a, int b, int c,String expected) {
		System.out.println("测试用例"+num+":"+a+","+b+","+c+":"+expected);
		String type = triangle.classify(a,b,c);

		assertEquals(expected,type);

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/triangle/三角形健壮性最坏情况测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
	void testBadRobustCase(int num, int a, int b, int c,String expected) {
		System.out.println("测试用例"+num+":"+a+","+b+","+c+":"+expected);
		String type = triangle.classify(a,b,c);

		assertEquals(expected,type);

	}
	@ParameterizedTest
	@CsvFileSource(resources = "/triangle/三角形弱一般等价类测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
	void testEquivalenceCase(int num, int a, int b, int c,String expected) {
		System.out.println("测试用例"+num+":"+a+","+b+","+c+":"+expected);
		String type = triangle.classify(a,b,c);

		assertEquals(expected,type);

	}
	@ParameterizedTest
	@CsvFileSource(resources = "/triangle/三角形最坏情况测试用例.csv", numLinesToSkip = 1, encoding = "UTF-8")
	void testWorstCase(int num, int a, int b, int c,String expected) {
		System.out.println("测试用例"+num+":"+a+","+b+","+c+":"+expected);
		String type = triangle.classify(a,b,c);

		assertEquals(expected,type);

	}
}