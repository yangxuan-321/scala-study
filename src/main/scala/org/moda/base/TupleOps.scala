package org.moda.base

/**
  * @author MODA-Master
  * @Title: TupleOps
  * @ProjectName scala-study
  * @Description: TODO 元组操作学习
  * @date 20-6-9 下午9:11
  */
object TupleOps {
  def main(args: Array[String]): Unit={
//    val tuple = (100, "moda", "scala")
//    // scala中的元组 下标 从1开始
//    println(tuple._1.toString)
//    println(tuple._2)
//    println(tuple._3)

//    for (t <- tuple) {
//
//    }
    val xs = Seq(1.0, 2.0, 3.0)
    Option(1)

    val ss = mean(xs).flatMap(m => mean(xs.map(x => Math.pow(x-m, 2))))
    println(ss)
  }

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)
}
