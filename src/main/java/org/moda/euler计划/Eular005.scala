package org.moda.euler计划

import scala.annotation.tailrec
import NumberUtils._

object Eular005 {
    def main(args: Array[String]): Unit = {
        val start = 1L
        val stop = 20L
        val x = (start to stop).foldLeft(1L)((m1 ,m2) => (m1, m2).minCommonMultiple)
        println(s"result => $x")
    }
}