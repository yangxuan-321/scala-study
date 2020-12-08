package org.moda.euler计划

import org.moda.euler计划.NumberUtils._

object Eular006 {
    def main(args: Array[String]): Unit = {
        val start = 1L
        val stop = 10L
        val x = (start to stop).foldLeft(0L, 0L)((res ,num) => (res._1+num*num, res._2+num))
        val res = x._2 * x._2 - x._1
        println(s"result => $res")
    }
}