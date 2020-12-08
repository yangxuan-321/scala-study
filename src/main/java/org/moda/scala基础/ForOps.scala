package org.moda.scala基础

import util.control.Breaks._

/**
  * @author MODA-Master
  * @Title: ForFuncOps
  * @ProjectName scala-study
  * @Description: TODO for循环操作学习
  * @date 20-6-10 上午9:26
  */
object ForOps {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 2; j <- 1 to 2){
      println("i -> " + i + " , " + "j -> " + j)
    }

    println("-------------------")

    for (i <- 1 to 5; j <- 1 to 5 if i == j){
      println("i -> " + i + " , " + "j -> " + j)
    }

    val arrs = Array(1, 2, 3, 4 ,5, 6)
    val targetInt = 3

    println("-------------------")

    // break
    breakable{
      for (i <- (0 until arrs.length)){
        if (targetInt == i){
          break
        }

        println(i)
      }
    }

    println("-------------------")

    // continue
    for (i <- (0 until arrs.length)){
      breakable {
        if (targetInt == i) {
          break
        }
        println(i)
      }
    }
  }
}
