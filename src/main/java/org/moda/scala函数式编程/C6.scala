package org.moda.scala函数式编程

// 随机数生成器接口
trait RNG {
  // 返回一个 生成的 整数结果 和 一个新的生成器
  // 使用老的生成器 一定是 得到的一个值，使用 返回的 新的生成器 才会得到 一个 新的随机值
  def nextInt: (Int, RNG)

  // 返回一个　生成的　正整数结果 和 一个新的生成器
  def nonNegativeInt: (Int, RNG)

  //　返回一个 double
  def double(): (Double, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }

  override def nonNegativeInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 17).toInt
    (n, nextRNG)
  }

  override def double(): (Double, RNG) = {
    val value = this.nextInt
    (value._1*1.0/(1L+Integer.MAX_VALUE), value._2)
  }

  type Rand[+A] = RNG => (A, RNG)

  def unit[A](a: A): Rand[A] =
    rng => (a, rng)

  // 组合
  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }

  def nonNegativeEven: Rand[Int] =
    map(nonNegativeEven)(x => x & 0xFFFFFFFE)

}

object C6 {
  def main(args: Array[String]): Unit = {
    val _, rng = new SimpleRNG(41)
    // 只要是同一个 rng 不管运行多少次 都是一样的值
//    println(rng.nextInt._2.nextInt._1)
//    println(rng.nextInt._2.nextInt._1)
//    println(rng.nextInt._2.nextInt._1)
//    println(rng.nextInt._2.nextInt._1)

    // 测试　正整数
    val allGt0 = (1 to 1000000).foldLeft((rng: RNG, true))((e, r) => {
      val c = e._1.nonNegativeInt
      (c._2, e._2 && c._1 > 0)
    })._2
    println(allGt0)

    // 测试　0 - 1 的 double 数
    val allDouble0_1 = (1 to 1000000).foldLeft((rng: RNG, true))((e, r) => {
      val c = e._1.double
      (c._2, e._2 && c._1 > 0.0 && c._1 < 1)
    })._2
    println(allDouble0_1)
  }
}
