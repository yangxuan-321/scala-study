package org.moda.program_skill

object ForYield {
  def main(args: Array[String]): Unit = {
    val a = List(1, 2, 3)
    val b = List(4, 5, 6)
    val r = for {
      a1 <-aa
      _ <- bb
    } yield a1
    println(r)
  }

  def aa = {
    Option(1)
  }
  def bb = {
    Option(2)
  }
}

object CC {

}
