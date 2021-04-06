package org.moda.scala实用指南

import scala.annotation.tailrec

object DiGui{
  def factorial(number: Int): BigInt = {
    if (number == 0){
      1
    } else {
      number * factorial(number - 1)
    }
  }

  @scala.annotation.tailrec
  def factorial1(fact: BigInt, number: Int): BigInt = {
    if (number == 0){
      1
    } else {
      factorial1(fact * number, number - 1)
    }
  }
}

object DiGuiOps {
  def main(args: Array[String]): Unit = {
    println(DiGui.factorial1(1, 1000000))
  }
}
