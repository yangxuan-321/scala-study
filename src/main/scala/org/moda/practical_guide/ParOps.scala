package org.moda.practical_guide

/**
 * 插入par做并行计算
 */
object ParOps {
  def main(args: Array[String]): Unit = {
    val symblos = Array(1, 2, 3)

    symblos.foreach(println(_))

    (1 to 9).map("*" * _).foreach(println)
  }
}
