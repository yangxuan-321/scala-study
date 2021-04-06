package org.moda.base

object SS {
  def main(args: Array[String]): Unit = {
    val sint = Array(2, 7, 11, 15)
    val ints = sint.zipWithIndex
    val ints1: Option[Array[Int]] = ints.flatMap(i => ints.map(ii => (i, ii))).filter(c => c._1._2 != c._2._2 && c._1._1 + c._2._1 == 9).map(ccc => Array(ccc._1._2, ccc._2._2).sorted).distinct.headOption
    ints1.getOrElse(Array.empty[Int])
  }
}
