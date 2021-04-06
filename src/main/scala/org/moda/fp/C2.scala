package org.moda.fp

object C2 {
  def main(args: Array[String]): Unit = {
    val bool = isSorted(Array(1, 3, 5, 6, 8, 9), (a: Int, b: Int) => a <= b)
    println(bool)
  }

  def isSorted[A](as: Array[A], sorter: (A, A) => Boolean): Boolean = {
//    as.foldLeft((true, as(0)))((r, a) => (r._1 && sorter(r._2, a), a))._1
    as.slice(0, as.length - 1).zip(as.slice(1, as.length)).forall(c => sorter(c._1, c._2))
  }

  def curry[A, B, C] (f:(A, B) => C): A => (B => C) = {
    a: A => ((b: B) => f(a, b))
  }

  def uncurry[A, B, C] (f: A => B => C): (A, B) => C = {
    (a: A, b: B) => f(a)(b)
  }

  def compose[A, B, C] (f: B => C, g: A => B): A => C = {
    a: A => f(g(a))
  }
}
