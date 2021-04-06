package org.moda.practical_guide

/**
  * @author MODA-Master
  * @Title: FanXingOps
  * @ProjectName scala-study
  * @Description: TODO 参数化类型 -> 泛型
  * @date 20-6-14 下午6:53
  */
object FanXingOps {
  def main(args: Array[String]): Unit = {
    // 参数可以不一致
    echo("1", 1)

    // 方法名后加一个参数类型限定, 这样的话 两个就必须 参数类型一致
    echo[Int](1, 1)
  }

  def echo[T](input1: T, input2: T):Unit = {
    println(s"the first-param type is -> ${input1.getClass}, second-param type is ${input2.getClass}")
  }
}
