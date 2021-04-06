package org.moda.program_skill

import com.zz.cdp.common.util.NumberUtil._
object FindADouble {
  def main(args: Array[String]): Unit = {
    val vv = (1 to 10000000).foldRight(false)((e, r) => (
      {
        val k = (0.0.toBigDecimal + 0.01 * e).toDouble.acc(2)
        if ((k * 1000000.0).toLong / 1000000.0 != BigDecimal((k.toBigDecimal * 1000000.0).toLong) / 1000000.0) {
          println(s"找打了: ${e} : ${k}")
        }
        r || (300 + 0.01 * e * 1000000.0 / 1000000.0) != (1000000.0.toBigDecimal * 1000000.0) / 1000000.0
      }
    ))
  }
}
