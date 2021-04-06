package cn.ctgu.junit;

public class TwoIntegersSumSub {

  public int sum(int n, int m) {
    // 前置断言
    assert n >= 1 && n <= 99;
    assert m >= 1 && m <= 99;
    assert (n + m >= 0) && (n + m <= 99);
    return n + m;
  }

  public int sub(int n, int m) {
    // 前置断言
    assert n >= 1 && n <= 99;
    assert m >= 1 && m <= 99;
    assert (n - m >= 0) && (n - m <= 99);
    return n - m;
  }
}

