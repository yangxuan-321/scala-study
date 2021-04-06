package com.zz.cdp.common.util

class NumberUtil {

}

object NumberUtil{

  implicit class DoubleAcc(d: Double) {
    def acc(p: Int): Double = {
      BigDecimal(d).setScale(p, BigDecimal.RoundingMode.HALF_UP).toDouble
    }
  }

  implicit class DoubleDiv(n: Double) {
    def div(d: Double): Double = if (d == 0.0) 0.0 else n / d
  }

  // 高精度除法 a / b
  implicit class DoubleAccurateDiv(a: Double) {
    def divided (b: Double): Double = if (b == 0.0) 0.0 else (a.toBigDecimal / b).toDouble
  }

  // 高精度乘法 a * b
  implicit class DoubleAccurateMulti(a: Double) {
    def multiply(b: Double): Double = (a.toBigDecimal * b).toDouble
  }

  // 高精度加法 a + b
  implicit class DoubleAccuratePlus(a: Double) {
    def plus(b: Double): Double = (a.toBigDecimal + b).toDouble
  }

  // 高精度减法 a - b
  implicit class DoubleAccurateMinus(a: Double) {
    def minus(b: Double): Double = (a.toBigDecimal - b).toDouble
  }

  implicit class DoubleConvertBigDecimal(n: Double) {
    def toBigDecimal(): BigDecimal = BigDecimal(n)
  }

  def round(number: Double, dotLength: Int): Double = number.formatted(s"%.${dotLength}f").toDouble

  def main(args: Array[String]): Unit = {
    val lubanRoiGoal = Some(4.56).getOrElse(0.0).multiply(10000L).toInt
    val c = (lubanRoiGoal*1.0).divided(10000L)
    println(c)
  }
}