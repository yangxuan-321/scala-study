package org.moda.scala实用指南

import java.time.LocalDate

/**
 * TODO 隐式函数操作
 */

class DateHelper(offset: Int){
  def days(when: String): LocalDate = {
    val today = LocalDate.now
    when match {
      case "ago" => today.minusDays(offset)
      case "from_now" => today.plusDays(offset)
      case _ => today
    }
  }
}

object DateHelper {
  val ago = "ago"
  val from_now = "from_now"

  implicit def convertInt2DateHelper(offset: Int): DateHelper = new DateHelper(offset)
}

object ImplicitFuncOps {
  import DateHelper._
  def main(args: Array[String]): Unit = {
    val past = 2 days ago
    println(past)

    // 上面两个是等价的
    // 1 days ago
    val date = DateHelper.convertInt2DateHelper(1).days(ago)
    println(date)
  }
}
