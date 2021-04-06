package org.moda.scala基础

/**
  * @author MODA-Master
  * @Title: MapOps
  * @ProjectName scala-study
  * @Description: TODO Map容器操作学习
  * @date 20-6-9 下午9:31
  */
object MapOps {
  def main(args: Array[String]): Unit = {
    val map = Map("a" -> 97, "b" -> 98, "c" -> 99);
    // k v 本身 也是 一个 元组感觉
    for ((k, v) <- map){
      println(k + " -> " + v)
    }

    println

    // k v 本身 也是 一个 元组感觉
    // 感受一下元组
    for (e <- map){
      println(e._1 + " -> " + e._2)
    }

    println

    // 占位符
    for ((k, _) <- map){
      println(k)
    }
  }
}
