package org.moda

object ForOps {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 3){
      println(i)
    }

    (1 to 3).foreach(i => println(i))

    (1 to 3).foreach(println(_))
  }
}
