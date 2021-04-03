package org.moda.scala函数式编程.monad

object C11 {
  /**
   * 我们在每个案例中都编写了 少许的原语，然后使用这些原语来定义组合子。这些组合子的相似性是值得注意的
   * 比如，我们为每个数据类型 都编写了 map 函数，用与提升某个数据类型 到 上下文中的数据类型。对于
   * Gen、 Parser、 Option，类型签名如下：
   * def map[A, B](g: Gen[A])(f: A => B): Gen[B]
   * def map[A, B](p: Parser[A])(f: A => B): Parser[B]
   * def map[A, B](o: Option[A])(f: A=> B): Option[B]
   * 这些类型签名仅仅是数据类型不同(Gen, Option, Parser)
   */
  trait Functor[F[_]] {
    def map[A, B](a: F[A])(f: A => B): F[B]

    def distribute[A, B](ab: F[(A, B)]): (F[A], F[B]) =
      (map(ab)(_._1), map(ab)(_._2))
  }

  trait Mon[F[_]] {
    def map[A, B](a: F[A])(f: A => B): F[B]
    def flatMap[A, B](a: F[A])(f: A => F[B]): F[B]
    def map2[A, B, C](a: F[A], b: F[B])(f: (A, B) => C): F[C] =
      flatMap(a)(x => map(b)(x1 => f(x, x1)))
  }

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    // val xx: Option[Option[C]] = a.map(x => b.map(x1 => f(x, x1)))
    // val xxx: Option[C] = xx.flatten

    // 简化1
    // val xx: Option[C] = a.map(x => b.map(x1 => f(x, x1))).flatten

    // 简化2
    // val xx: Option[C] = a.flatMap { x => b.map(x1 => f(x, x1))}

    // 简化3
    a flatMap (x => b map (x1 => f(x, x1)))
  }

  def init(): Unit = {
    val listFunctor = new Functor[List] {
      override def map[A, B](a: List[A])(f: A => B): List[B] = a map f
      // 对于list的distribute 就是 List[(A, B)] => (List[A], List[B])
      // 其实这就是一个标准的unzip操作
    }


  }

  def main(args: Array[String]): Unit = {

  }
}
