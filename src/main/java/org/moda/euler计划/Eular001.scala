package org.moda.euler计划

object Eular001 {
//  val N: Long = 1000
  val a: Long = 3
  val b: Long = 5

  def f = {
    (1 to 999).filter(x => x % 3 == 0 || x % 5 == 0).sum
  }

  // 求最小公倍数
  def leastCommonMultiple(m1: Long, m2 :Long): Long = {
    val s = if (m1 > m2) greatestCommonDivisor(m1, m2) else greatestCommonDivisor(m2, m1)
    m1*m2/s
  }

  // 求最大公约数
  @scala.annotation.tailrec
  def greatestCommonDivisor(m1: Long, m2: Long): Long ={
    if (m1%m2 == 0)
      m2
    else
      greatestCommonDivisor(m2, m1%m2)
  }

  // 等差数列求和
  def arithmeticSequenceSum(a1: Long, d: Long, n: Long): Long ={
    n * a1 + n * (n-1) * d/2
  }

  // 欧拉计划 - 001
  def cal(N: Long): Long ={
    val N1 = N - 1
    val na = N1 / a
    val nb = N1 / b
    val ab = leastCommonMultiple(a, b)
    val nab = N1 / ab

    val Sa = arithmeticSequenceSum(a, a, na)
    val Sb = arithmeticSequenceSum(b, b, nb)
    val Sab = arithmeticSequenceSum(ab, ab, nab)

    Sa + Sb - Sab
  }

  def main(args: Array[String]): Unit = {
    // 如果 求 3 和 5 的 最大公约数 的 时间复杂度 近似为 O(1)的话。
    // 则整个算法的时间复杂度也近似与O(1)
    printf(s"result is ${f}")
  }
}
