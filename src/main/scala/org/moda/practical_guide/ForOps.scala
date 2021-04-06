package org.moda.practical_guide

/**
  * @author MODA-Master
  * @Title: ForOps
  * @ProjectName scala-study
  * @Description: TODO
  * @date 20-6-13 下午9:08
  */
object ForOps {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 3){
      println(i)
    }

    (1 to 3).foreach(i => println(i))

    (1 to 3).foreach(println(_))
  }
}
