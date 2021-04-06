package cn.ctgu.junit;

/**
 * 输入3个整数a、b和c分别作为三角形的三条边，要求a、b和c必须满足以下条件：
 * 1、整数
 * 2、3个数
 * 3、边长大于等于1，小于等于100
 * 4、任意两边之和大于第三边
 *
 * 输出为5种情况之一：
 * 如果不满足条件1、2、3，则程序输出为“输入错误”。
 * 如果不满足条件4，则程序输出为“非三角形”。
 * 如果三条边相等，则程序输出为“等边三角形”。
 * 如果恰好有两条边相等，则程序输出为“等腰三角形”。
 * 如果三条边都不相等，则程序输出为“一般三角形”。
 */
public class Triangle {
	/**
	 * 判断a, b, c为三边能否构成三角形
	 * @param a
	 * @param b
	 * @param c
	 * @return true 可以构成三角形 false 不能构成三角形
	 */
	public boolean isTriangle(int a, int b, int c) {
		return (a + b > c) && (a + c > b) && (b + c > a);
	}


	public String classify(int a, int b, int c) {
		if (a <1 || a>100 || b<1 || b>100 || c<1 || c> 100) {
			if (!isTriangle(a, b, c)) {
				return "非三角形";
			}
			return "输入错误";
		}

		if (!isTriangle(a, b, c)) {
			return "非三角形";
		} else if (a == b && a == c && b == c) {
			return "等边三角形";
		} else if (a != b && a != c && b != c) {
			return "不等边三角形";
		} else {
			return "等腰三角形";
		}
	}
}