package org.moda.scala函数式编程.monoid


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

    
  }
}
