package org.moda.euler
import NumberUtils._

object Eular010 {
  def main(args: Array[String]): Unit = {
    val start = System.currentTimeMillis
    val c = 2000000L
    val x = (1L to c).filter(_.isPrime).sum
    println((System.currentTimeMillis - start)/1000)
  }
}