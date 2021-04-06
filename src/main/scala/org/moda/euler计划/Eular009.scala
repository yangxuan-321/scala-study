package org.moda.euler计划

import org.moda.euler计划.NumberUtils._

import scala.annotation.tailrec

object Eular009 {
    def main(args: Array[String]): Unit = {
        def square(num: Long): Long = math.pow(num, 1.0/2).toLong
        val cstop = 500L

//        val x = (1L to cstop).flatMap(c => (1L to c).flatMap(b => (1L to b).map(a => a).filter(a => a+b+c==1000L && a*a+b*b==c*c).map(xx => (xx, b, c))))
val x = (1L to cstop).flatMap(c => (1L to c).flatMap(b => (1L to b).map(a => (a, b, c)).toStream)).filter(t => t._1+t._2+t._3==1000L && t._1*t._1+t._2*t._2==t._3*t._3)
        println(x)
        //        for (c <- 1L to cstop){
//            for (b <- 1L to bstop){
//                for (a <- 1L to astop){
//                    if (a + b + c == 1000L && a*a + b*b == c*c){
//                        println("找到")
//                    }
//                }
//            }
//        }
    }
}