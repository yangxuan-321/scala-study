package org.moda.scala函数式编程.monoid

import scala.annotation.tailrec


/**
 * Monoid
 * 结合律和同一律是 monoid法则
 */
class C10 {

}

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

trait Foldable[F[_]] {
  def foldRight[A, B](as: F[A])(z: B)(f: (A, B) => B): B
  def foldLeft[A, B](as: F[A])(z: B)(f: (B, A) => B): B
  def foldMap[A, B](as: F[A])(f: A => B)(m: Monoid[B]): B
  def concatenate[A](as: F[A])(m: Monoid[A]): A = foldLeft(as)(m.zero)(m.op)
}

object C10 {
  def main(args: Array[String]): Unit = {
    val stringMonoid = new Monoid[String] {
      override def op(a1: String, a2: String): String = a1 + a2
      override def zero: String = ""
    }

    def listMonoid[A] =
      new Monoid[List[A]] {
        override def op(a1: List[A], a2: List[A]): List[A] = a1 ++ a2
        override def zero: List[A] = Nil
      }

    val intAddition = new Monoid[Int] {
      override def op(a1: Int, a2: Int): Int = a1 + a2
      override def zero: Int = 0
    }

    val intMultiolication = new Monoid[Int] {
      override def op(a1: Int, a2: Int): Int = a1 * a2
      override def zero: Int = 1
    }

    val booleanOr = new Monoid[Boolean] {
      override def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2
      override def zero: Boolean = false
    }

    val booleanAnd = new Monoid[Boolean] {
      override def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2
      override def zero: Boolean = true
    }

    def optionMonoid[A] =
      new Monoid[Option[A]] {
        override def op(a1: Option[A], a2: Option[A]): Option[A] = a1 orElse a2
        override def zero: Option[A] = None
      }

    def endoMonoid[A] =
      new Monoid[A => A] {
        override def op(a1: A => A, a2: A => A): A => A
          // = (a: A) => a2(a1(a)) 等价的
          = a1 compose a2
        override def zero: A => A = (a: A) => a
      }

    /**
     * 那么monoid是什么？简单来说是一个类型A和一个实现法则Monoid[A]。简短说，一个monoid是一个类型，一个此类型的二元操作(满足结合律)和
     * 一个单位元元素(zero)。
     */

    // 测试stringMonoid
    val words = List("Hic", "Est", "Index")
    words.foldLeft(stringMonoid.zero)(stringMonoid.op)
    words.foldRight(stringMonoid.zero)(stringMonoid.op)

    /**
     * 在使用monoid折叠时foldLeft和foldRight的效果是一样的。这正是因为结合律和同一律法则。左折叠就像左关联操作，右折叠和右关联操作类似，
     * 而identity元素可以在任意一边。
     */
    // 可以编写一个通用的concatenate函数，使用monoid去折叠列表：
    def concatenate[A](as: List[A], m: Monoid[A]): A = as.foldLeft(m.zero)(m.op)

    // 加入列表中的元素不是Monoid实例，该怎么办？不过总是可以将列表map成另外的类型
    def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B = concatenate(as.map(f), m)

    // f.curried 可以将函数柯西化 (A, B) => B ~> (A)(B) => B
    def func[A, B, C](a: A, b: B, f: (A, B) => C): C = f.curried(a)(b)

    // @tailrec
    // 注意此处不是尾递归
    def foldMapV[A, B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B = {
      if (v.size == 0) m.zero
      if (v.size == 1) f(v(0))
      else {
        val vv = v.splitAt(v.size / 2)
        m.op(foldMapV(vv._1, m)(f), foldMapV(vv._2, m)(f))
      }
    }

    val aaa = foldMapV(IndexedSeq(1, 2, 3), intAddition)((a: Int) => a)
    val aaa1 = foldMapV(IndexedSeq(1, 2, 3), stringMonoid)((a: Int) => a.toString)
    println(aaa)
    println(aaa1)

    // 判断列表是否有序的 Monoid
    val isSortMonoid = new Monoid[Option[(Int, Boolean)]] {
      override def op(a1: Option[(Int, Boolean)], a2: Option[(Int, Boolean)]): Option[(Int, Boolean)] = {
        (a1, a2) match {
          case (Some((x1, z1)), Some((x2, z2))) => Some(x2, z1 && x1 <= x2)
          case (Some(v11), _) => a1
          case (_, Some(v22)) => a2
          case _ => None
        }
      }

      override def zero: Option[(Int, Boolean)] = Option.empty
    }
    // 经过测试 同时 支持 foldLeft 和 foldRight 同样都可以，难道这就是 同一律和结合律的魅力？
    val sort1 = foldMap(List(1, 2, 3, 5), isSortMonoid)((a: Int) => Some(a, true)).fold(true)(_._2)
    println(sort1)

    // 实现Foldable[List]
    new Foldable[List] {
      override def foldRight[A, B](as: List[A])(z: B)(f: (A, B) => B): B
    }
  }
}
