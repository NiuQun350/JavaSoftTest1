package cn.ctgu.junit;

public class TwoIntegers {

  public int sum(int n, int m) {
    // 前置断言
    assert n >= 1 && n <= 99;
    assert m >= 1 && m <= 99;

    return n + m;
  }
}

