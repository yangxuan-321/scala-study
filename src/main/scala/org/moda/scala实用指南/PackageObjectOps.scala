package org.moda.scala实用指南

package object Constant{
  val ON: Int= 1
  val OFF: Int = 2

  def execON() = {
    println("打开")
  }

  def execOFF() = {
    println("关闭")
  }
}

object PackageObjectOps {
  def main(args: Array[String]): Unit = {
  }
}
