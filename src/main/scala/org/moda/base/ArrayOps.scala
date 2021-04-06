package org.moda.base

/**
  * @author MODA-Master
  * @Title: ArrayOps
  * @ProjectName scala-study
  * @Description: TODO 数组操作学习
  * @date 20-6-9 下午9:23
  */
object ArrayOps {
  def main(args: Array[String]): Unit = {
    val array = Array(1, 2, 3, 4, 5, 6)

    for (e <- array){
      println("e->" + e.toString)
    }

    println

    for (index <- 0 until array.length){
      println(array(index))
    }
  }
}
