package org.moda.euler

import org.moda.euler.Eular001.greatestCommonDivisor

import scala.annotation.tailrec

object NumberUtils {
  // 判断素数
  implicit class Prime(num: Long) {
    def isPrime(): Boolean = {
      @tailrec
      def isPrime_(n: Long, m: Long): Boolean = {
        if (n/2 < m) true else if (n%m == 0) false else isPrime_(n, m+1)
      }
      if (1 == num) false else isPrime_(num, 2)
    }
  }

  // 求两个数的最小公倍数和最小公约数
  implicit class CommonMultiple(two: (Long, Long)){
    // 最小公倍数
    def minCommonMultiple(): Long = two._1 * two._2 / maxCommonDivisor()

    // 最大公约数
    def maxCommonDivisor(): Long = {
      @tailrec
      def _maxCommonDivisor(m1: Long, m2: Long): Long = if (m1%m2 == 0) m2 else _maxCommonDivisor(m2, m1%m2)
      if (two._1 > two._2) _maxCommonDivisor(two._1, two._2) else _maxCommonDivisor(two._2, two._1)
    }
  }
}