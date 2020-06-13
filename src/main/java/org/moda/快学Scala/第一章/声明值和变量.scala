package org.moda.快学Scala.第一章

/**
  * @author MODA-Master
  * @Title: 声明值和变量
  * @ProjectName scala-study
  * @Description: TODO
  * @date 20-5-20 下午10:31
  */
object 声明值和变量 {
  def main(args: Array[String]): Unit = {
    变量声明学习()
    常用类型学习()
    算数和操作符重写()
  }

  def 变量声明学习(): Unit = {
    // var 声明的 是 变量
    // val 声明的 常量
    // 变量可以带类型 也可以不带
    var a = 0
    a = 1
    var b: Int = 0
    b = 1

    val c = 1
    // c = 2 此处会报错 因为常量不能重新赋值
    val d: Int = 1
    // d = 2 此处会报错 因为常量不能重新赋值

    println(c)

    //变量 可以多个 一起声明
    var a1, a2 = 3
    val b1, b2 :Int = 4
  }


  def 常用类型学习(): Unit = {
    // Scala并不可以区分 引用类型 和 基本类型
    // 例如 整形
    println(1.toString())
    println(1.to(10).toString())

    // Scala的类型有 :
    // Byte, Char, Short, Int, Long, Float, Double, Boolean
    println(99.44.toInt.toString())
  }

  def 算数和操作符重写(): Unit = {
    // 1.+(2) ==> 1+2
    println(1.+(2))

    // 8.*(5).+(2) ==> 42
    println(8.*(5).+(2))
  }
}
