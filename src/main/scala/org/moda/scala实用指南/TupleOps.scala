package org.moda.scala实用指南

/**
  * @author MODA-Master
  * @Title: TupleOps
  * @ProjectName scala-study
  * @Description: TODO
  * @date 20-6-13 下午9:08
  */
object TupleOps {

  def swap(a: Int, b: Int): (Int, Int) = {
    if (a > b) {
      return (b, a)
    }

    return (a, b)
  }

  def main(args: Array[String]): Unit = {
    val t3 = Tuple3(1, 2, 3)

    val a = 2
    val b = 3
    val (min, max) = swap(a, b)
    println(s"${min} , ${max}")

    val v = swap(a, b)
    println(s"${v._1} , ${v._2}")
  }
}
