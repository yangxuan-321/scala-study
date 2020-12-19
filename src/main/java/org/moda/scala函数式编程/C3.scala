package org.moda.scala函数式编程

import scala.annotation.tailrec

// sealed 代表被修饰过的 trait 在做模式匹配时 必须写全 所有情况，否则 编译不过
sealed trait List[+A]
// 空的List的单例
case object Nil extends List[Nothing]
// tail 可能是 Nil 也可能是 Nodes 本身
case class Nodes[+A](head: A, tail: List[A]) extends List[A]

object List {
  // def sum[A](ints: List[A]): Int = ints match {
  //   case Nil => 0
  //   case Nodes(node, nodes) => node + sum(nodes)
  // }

  def delHead[A](lists: List[A]): List[A] = lists match {
    case Nil => Nil
    case Nodes(head, nodes) => nodes
  }

  def setHead[A](head: A, lists: List[A]): List[A] = lists match {
    case Nil => Nodes(head, lists)
    case Nodes(h, nodes) => Nodes(head, nodes)
  }

  def delHeads[A](lists: List[A], cnt: Int): List[A] =
    (1 to cnt).foldLeft(lists)((z, x) => delHead(z))

  def dropWhile[A](lists: List[A], f: A => Boolean): List[A] =
//    lists match {
//      case Nodes(head, Nil) => if(f(head)) Nil else Nodes(head, Nil)
//      case Nodes(head, nodes) => if (f(head)) dropWhile(nodes, f) else Nodes(head, dropWhile(nodes, f))
//    }

    lists match {
      case Nil => Nil
      case Nodes(head, nodes) => if (f(head)) dropWhile(nodes, f) else Nodes(head, dropWhile(nodes, f))
    }

//    lists match {
//      case Nodes(head, nodes) if(f(head)) =>  dropWhile(nodes, f)
//      case _ => lists
//    }

  // 柯理化
  def  dropWhile1[A](lists: List[A])(f: A => Boolean): List[A] =
    lists match {
      case Nil => Nil
      case Nodes(head, nodes) => if (f(head)) dropWhile(nodes, f) else Nodes(head, dropWhile(nodes, f))
    }

  // 实现 foldRight函数
  def foldRight[A, B](lists: List[A], z: B)(f: (A, B) => B): B =
    lists match {
      case Nil => z
      case Nodes(x, xs) => f(x, foldRight(xs, z)(f))
    }

  // 实现 foldLeft函数
  @tailrec
  def foldLeft[A, B](lists: List[A], z: B)(f: (A, B) => B): B =
    lists match {
      case Nil => z
      case Nodes(x, xs) => foldLeft(xs, f(x, z))(f)
    }

  def apply[A](ds: A*): List[A] =
    if (ds.isEmpty) Nil
    else Nodes(ds.head, apply(ds.tail: _*))
}


object C3 {

//  def test1(is: Int*) = {
//    println(is)
//  }

  def main(args: Array[String]): Unit = {
    val value = List(1, 2, 3, 4, 5, 6, 7, 8, 2)

//    test1(Seq(1, 2): _*)

//    println(s"$vector")
//    val res = List(1, 2, 3, 4, 5) match {
//      case Nodes(x, Nodes(2, Nodes(4, _))) => x
//      case Nil => 42
//      case Nodes(x, Nodes(y, Nodes(3, Nodes(4, _)))) => x + y
//      case Nodes(h, t) => h + List.sum(t)
//    }

//    println(List.delHead(value))
//    println(List.setHead(9, value))
//      println(List.delHeads(value, 5))
    println(List.dropWhile[Int](value, (i: Int) => i % 2 == 0))

    // 可以看到的是柯理化 有助于 函数参数类型推导
    // 例如 此处 的 高阶f函数，并不需要 指定为int 类型，它的类型是int，来源于前一个参数
    println(List.dropWhile1[Int](value)(i => i % 2 == 0))

    println(List.foldLeft(value, 3)((l, r) => if (l > r) l else r))

    println(List.foldRight(value, 3)((l, r) => if (l > r) l else r))
  }
}
