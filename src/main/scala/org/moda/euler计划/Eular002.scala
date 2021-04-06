package org.moda.euler计划

object Eular002 {

  def main(args: Array[String]): Unit = {
    printf(s"${fblq(400 * 10000)}")
  }

//  def fblq(s: Int): BigDecimal = (2l to s).foldLeft((BigDecimal(0), BigDecimal(1), BigDecimal(0)))((n, r) => (n._2, n._1 + n._2, if (n._2%2==0) n._3+n._2 else n._3) )._3

   def fblq(s: Int): BigDecimal = (2l to s).foldLeft((BigDecimal(0), BigDecimal(1), BigDecimal(0)))((n, r) => if (n._2 < s) (n._2, n._1 + n._2, if (n._2%2==0) n._3+n._2 else n._3) else n)._3
}