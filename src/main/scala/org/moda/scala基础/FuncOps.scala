package org.moda.scala基础

/**
  * @author MODA-Master
  * @Title: FuncOps
  * @ProjectName scala-study
  * @Description: TODO 函数操作学习
  * @date 20-6-10 下午1:19
  */
object FuncOps {
  def main(args: Array[String]): Unit = {
    // 定义了一个函数
    def addFunc (x: Int, y: Int): Int = x + y
    println(addFunc(10, 20))

    // 匿名函数
    val add = (x: Int, y: Int) => x + y
    println(add(10, 20))

    // 函数柯里化
    val add_ = (x: Int) => (y: Int) => x + y
    println(add_(1)(2))

    // 闭包
    def inc(): Int = {
      var x = 0
      return {
        x = x + 1
        x
      }

    }

    println(inc())
    println(inc())
    println(inc())
    println(inc())
    println(inc())
    println(inc())
  }
}
