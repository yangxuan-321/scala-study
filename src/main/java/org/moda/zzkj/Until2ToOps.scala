package org.moda.zzkj

/**
  * @author MODA-Master
  * @Title: Until2ToOps
  * @ProjectName scala-study
  * @Description: TODO
  * @date 20-6-13 下午9:08
  */
object Until2ToOps {
  def main(args: Array[String]): Unit = {
    // 1,2
    for (i <- 1 to 3){
      println(i)
    }

    println("----------")

    // 1,2,3
    for (i <- 1 until 3){
      println(i)
    }
  }
}
