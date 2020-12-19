package org.moda.scala函数式编程

// 随机数生成器接口
trait RNG {
  // 返回一个 生成的 整数结果 和 一个新的生成器
  // 使用老的生成器 一定是 得到的一个值，使用 返回的 新的生成器 才会得到 一个 新的随机值
  def nextInt: (Int, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}

object C6_Rand {
  //　返回一个 double
  def double(rng: RNG): (Double, RNG) = {
    val value = rng.nextInt
    (value._1*1.0/(1L+Integer.MAX_VALUE), value._2)
  }

  // 返回一个　生成的　正整数结果 和 一个新的生成器
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val v = rng.nextInt
    (v._1 >>> 1, v._2)
  }

  type Rand[+A] = RNG => (A, RNG)

//  def nonNegativeEven: Rand[Int] =
//    map(nonNegativeInt)(x => x & 0xFFFFFFFE)

  // 组合
  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }

  def unit[A](a: A): Rand[A] =
    rng => (a, rng)

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    rng => {
      val (a, rng1) = ra(rng)
      val (b, rng2) = rb(rng1)
      (f(a, b), rng2)
    }
  }

  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] =
    rng => g(f(rng)._1)(rng)


  def a: Long => Int = {
    x => (x + 1).toInt
  }
}

object C6_Common {
  def map[S, A, B](a: S => (A, S))(f: A => B): S => (B, S) =
    s => {
      val r = a(s)
      (f(r._1), r._2)
    }

  type State[S, +A] = S => (A, S)

  // type Rand[A] = State[RNG, A]

  def unit[S, A](a: A): State[S, A] =
    s => (a, s)

  def map[S, A, B](as: State[S, A])(f: A => B): State[S, B] =
    bs => {
      val c: (A, S) = as(bs)
      (f(c._1), c._2)
    }

  def map2[S, A, B, C](as: State[S, A], bs: State[S, B])(f: (A, B) => C): State[S, C] =
    (cs: S) => {
      val a: (A, S) = as(cs)
      val b: (B, S) = bs(a._2)
      (f(a._1, b._1), b._2)
    }


}

object C6 {
  def main(args: Array[String]): Unit = {
    val rng = new SimpleRNG(41)
    // 只要是同一个 rng 不管运行多少次 都是一样的值
    println(rng.nextInt._1)
    println(rng.nextInt._1)

    val cni1 = C6_Rand.nonNegativeInt(rng)
    println(cni1._1)
    val cni2 = C6_Rand.nonNegativeInt(cni1._2)
    println(cni2._1)
//    println(rng.nextInt._2.nextInt._1)
//    println(rng.nextInt._2.nextInt._1)

    // 测试　正整数
//    val allGt0 = (1 to 1000000).foldLeft((rng: RNG, true))((e, r) => {
//      val c = C6_Rand.nonNegativeInt(e._1)
//      (c._2, e._2 && c._1 > 0)
//    })._2
//    println(allGt0)

    // 测试　0 - 1 的 double 数
//    val allDouble0_1 = (1 to 1000000).foldLeft((rng: RNG, true))((e, r) => {
//      val c = C6_Rand.double(e._1)
//      (c._2, e._2 && c._1 > 0.0 && c._1 < 1)
//    })._2
//    println(allDouble0_1)

    // 匿名函数
    // val xd = (x: Int) => {x+1; x+2}
    val dbR = C6_Rand.map((rng: RNG) => C6_Rand.nonNegativeInt(rng))(_.toDouble)
    val b1: (Double, RNG) = dbR(rng)
    println(b1._1)
    val b2 = dbR(b1._2)
    println(b2._1)
  }
}
