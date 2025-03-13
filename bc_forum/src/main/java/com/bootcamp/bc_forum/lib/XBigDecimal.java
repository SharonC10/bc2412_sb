package com.bootcamp.bc_forum.lib;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class XBigDecimal {
  private BigDecimal bigDecimal;
  private RoundingMode roundingMode;

  private XBigDecimal(Double x) {
    this.bigDecimal = BigDecimal.valueOf(x);
    this.roundingMode = RoundingMode.HALF_DOWN;
  }

  public static XBigDecimal valueOf(Double x) {
    return new XBigDecimal(x);

  }

  public XBigDecimal divide(Double value) {
    this.bigDecimal =
        bigDecimal.divide(BigDecimal.valueOf(value), 2, this.roundingMode);
    return new XBigDecimal(bigDecimal.doubleValue());
  }

  public XBigDecimal setRoundingMode(RoundingMode roundingMode) {
    this.roundingMode = roundingMode;
    return this;
  }

  public double doubleValue() {
    return this.bigDecimal.doubleValue();
  }

  public static void main(String[] args) {
    System.out.println(XBigDecimal.valueOf(10.0).divide(3.0).doubleValue());
    // 3.33
    System.out.println(XBigDecimal.valueOf(10.0)
        .setRoundingMode(RoundingMode.CEILING).divide(3.0).doubleValue()); //3.34

  }
}
