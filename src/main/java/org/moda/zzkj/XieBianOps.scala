package org.moda.zzkj

object XieBianOps {
  def main(args: Array[String]): Unit = {
    val ducks = Array(new PekingDuck("haha", "haha", 18), new PekingDuck("heihei", "heihei", 16))
    showDuck(ducks)
  }

  // 协变
  def showDuck[T <: Duck](ducks: Array[T]): Unit ={
    ducks.foreach(println(_))
  }
}
