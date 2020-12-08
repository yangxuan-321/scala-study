package org.moda.helloworld

/**
  * @author MODA-Master
  * @Title: HelloWorld
  * @ProjectName scala-study
  * @Description: TODO
  * @date 20-5-20 下午10:26
  */
object HelloWorld {
  def main(args: Array[String]): Unit = {
    printf("hello world!")
    (1 to 10).map(c => (c, c)).groupBy(_._1).map {
      case (key, v) =>
    }
  }
}
