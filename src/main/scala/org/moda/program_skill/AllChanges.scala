package org.moda.program_skill

import scala.collection.immutable.Queue
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random

trait CellLine[+A]

// 排列 组合
object AllChanges {
  // C(3, box) 从 box 里面 不放回 的 选 3 个球 所有组合
  def cx(a: Int, box: Array[Int]): List[Array[Int]] = {
    val q = mutable.Queue((0 to box.length - 1).map(c => (1, c, Array.empty[Int])): _*)
    val res = ListBuffer.empty[Array[Int]]
    if (a > box.length) res.toList
    else {
      while (q.nonEmpty) {
        val e = q.dequeue
        if (e._1 == a) {
          res += (e._3 :+ e._2)
        } else {
          val c = (e._2 + 1 to box.length - 1).map(cc => (e._1 + 1, cc, e._3 :+ e._2))
          q.enqueueAll(c)
        }
      }
      res.toList
    }
  }

//  def cx1(a: Int, box: Array[Int]): List[Array[Int]] = {
//    val q = Queue((0 to box.length - 1).map(c => (1, c, Array.empty[Int])): _*)
//    val res = List.empty[Array[Int]]
//    Stream.continually(1).foldLeft((false, q, res))((r, e) => {
//      if (q.isEmpty) (true, r._2, r._3)
//      else {
//        val (e1, q1) = q.dequeue
//        if (e1._1 == a) {
//          val resNew = (e1._3 :+ e1._2) +: res
//          (false, r._2, resNew)
//        } else {
//          val c = (e1._2 + 1 to box.length - 1).map(cc => (e1._1 + 1, cc, e1._3 :+ e1._2))
//          val qNew  = q1.enqueue(c)
//          (false, qNew, r._3)
//        }
//      }
//    })
//  }

  def main(args: Array[String]): Unit = {
    val box = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12) //, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38)
     cx(3, box).map(c => println(c.mkString(",")))
    // println(cx(5, box).last.mkString(","))
//    println(cx(5, box).size)
//    val qq = Queue(1, 2, 3)
//    val qq1 = qq.enqueue(List(4, 5))
//    val qq2 = qq1.enqueue(6, 7)
//    val qq3 = qq2.dequeue
//    println(qq3)
  }
}
