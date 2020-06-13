package org.moda.scalalolo

/**
  * @author MODA-Master
  * @Title: TupleOps
  * @ProjectName scala-study
  * @Description: TODO 元组操作学习
  * @date 20-6-9 下午9:11
  */
object TupleOps {
  def main(args: Array[String]): Unit={
    val tuple = (100, "moda", "scala")
    // scala中的元组 下标 从1开始
    println(tuple._1.toString)
    println(tuple._2)
    println(tuple._3)

//    for (t <- tuple) {
//
//    }
  }
}
