package org.moda.scala函数式编程

import scala.annotation.tailrec

// sealed 代表被修饰过的 trait 在做模式匹配时 必须写全 所有情况，否则 编译不过
sealed trait Stream[+A] {
  def toList: List[A] =
    this match {
      case Empty => Nil
      case SNodes(head, tail) => Nodes(head(), tail().toList)
    }

  def foldRight[B](b: B)(f: (A, =>B) => B): B =
    this match {
      case Empty => b
      case SNodes(head, tail) => f(head(), tail().foldRight(b)(f))
    }

  def exists(s: A => Boolean): Boolean =
    this.foldRight(false)((e, r) => s(e) || r)

  def take(cnt: Int): Stream[A] =
    this match {
      case Empty => Empty
      case SNodes(head, tail) if cnt != 0 => SNodes(head, () => tail().take(cnt - 1))
      case _ =>
        Empty
    }

  def map[B](f: A => B): Stream[B] =
    this.foldRight(Empty: Stream[B])((e, r) => Stream.nodes(f(e), r))

  def filter(f: A => Boolean): Stream[A] =
    this.foldRight(Empty: Stream[A])((e, r) => if (f(e)) Stream.nodes(e, r) else r)
}
// 空的List的单例
case object Empty extends Stream[Nothing]
// tail 可能是 Nil 也可能是 Nodes 本身
case class SNodes[+A](head: () => A, tail: () => Stream[A]) extends Stream[A]

object Stream {

  def nodes[A](head: => A, tail: => Stream[A]): Stream[A] = {
    lazy val head_ = head;        // 对惰性求值的head和tail做缓存，避免重复求值
    lazy val tail_ = tail;
    SNodes(() => head_, () => tail_)
  }

  def empty[A]: Stream[A] = Empty

  def constant[A](e: A): Stream[A] = {
    lazy val es: Stream[A] = Stream.nodes(e, es)
    es
  }

  def constantPlus(e: Int): Stream[Int] = {
    lazy val es: Stream[Int] = Stream.nodes(e, constantPlus(e + 1))
    es
  }

  def fibs(a: Int, b: Int): Stream[Int] = {
    lazy val es: Stream[Int] = Stream.nodes(b, fibs(b, a + b))
    es
  }

  def unfold[A, S](r: S)(f: S => Option[(A, S)]): Stream[A] = {
    val ss = f(r).get
    lazy val es: Stream[A] = Stream.nodes(ss._1, unfold(ss._2)(f))
    es
  }

  def apply[A](ds: A*): Stream[A] =
    if (ds.isEmpty) empty
    else nodes(ds.head, apply(ds.tail: _*))
}


object C5 {

  def maybeTwice(getIntFunc: => Int): Int = {
    getIntFunc + getIntFunc
  }

  def maybeTwice2(getIntFunc: => Int): Int = {
    lazy val i = getIntFunc
    i + i
  }

  def main(args: Array[String]): Unit = {
    // 你会惊奇的 发现 打印了 两次hi 。 是因为 传名参数(区别于传值参数) 原因
    // println(maybeTwice({println("hi"); 1 + 1}))
    // 你会发现 这个方法 竟然 只做了 一次求值(因为只打印了一次)
    // 对一个val申明的变量 添加lazy修饰符，将导致scala延迟对这个变量求值，直到它第一次被引用的时候。他也会缓存结果，在后续引用的地方不会触发重复求职
    // println(maybeTwice2({println("hi"); 1 + 1}))

    // val value = Stream(1, 2, 3, 4, 5, 6, 7, 8, 2)
    val value = Stream({println("hi-1"); 1}, {println("hi-2-1"); 2}, {println("hi-3"); 3}, {println("hi-4"); 4},
      {println("hi-5-1"); 5}, {println("hi-6"); 6}, {println("hi-5-2"); 5}, {println("hi-8"); 8}, {println("hi-2-2"); 2})
    // println(value.take(2))
    // value.exists(eq(_))
    // println(value.map(_ + 1).toList)
    // println(value.filter(_ % 2 == 0).toList)

    // 做一个无限流
    // println(Stream.constantPlus(1).take(6).toList)

    // println(Stream.fibs(0, 1).take(6).toList)

    // 使用 无限流 + 共递归 实现 无限的 斐波拉契 无限流
    println(Stream.unfold((0, 1))((e: (Int, Int)) => Some(e._1, (e._2, e._1 + e._2))).take(6).toList)
  }

  def eq(n: Int) = {
    println("---exists---")
    n == 3
  }
}
