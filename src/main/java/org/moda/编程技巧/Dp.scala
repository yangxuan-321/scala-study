package org.moda.编程技巧

import scala.collection.mutable.ListBuffer

object Dp {
  /**
   * leecode 322: 你有三种硬币，2元、5元、7元，每种硬币足够多，买一本书需要27元，用最少的硬币组合
   */
  def coins(): Unit = {
    val coinx = Array(2, 5, 7)
    val sum = 27
    val total = 5
    val f = ListBuffer()

    def minFunc(): Unit = {

    }

    for (i <- 1 to sum) {
      for (j <- 0 to coinx.length - 1) {
        print(s"{}, {}", i, j)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    coins()
  }
}
