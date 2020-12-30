package org.moda.编程技巧

import scala.util.Random

// 全排列
object AllChanges {
  val random = new Random()
  // chose
  // 返回 被选中的对象 和 剩下的盒子
  def choseOne[T](box: Array[T]): (T, Array[T]) = {
    // val one = random.nextInt(box.length)
    (box(0), box.drop(1))
  }

  def uint[T](a: Int, box: Array[T]): Array[Object] = {
    val choseA = (1 to a).foldLeft((Array.empty[Object], box))((r, e) => {
      val (one1, box1) = choseOne(r._2)
      (r._1 :+ one1, box1)
    })
    choseA._1
  }

  // C(3, box) 从 box 里面 不放回 的 选 3 个球 所有组合
  def C[T](a: Int, box: Array[T]): List[Array[T]] = {
    if (a > box.length) List.empty[Array[T]]
    else {
      while (true) {
        uint(a, box)
      }
    }
    List.empty[Array[T]]
  }

  def main(args: Array[String]): Unit = {
    C(3, Array(1, 2, 3, 4, 5, 6))
  }
}
