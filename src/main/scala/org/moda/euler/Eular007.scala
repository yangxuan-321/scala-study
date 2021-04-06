package org.moda.euler
import NumberUtils._

import scala.annotation.tailrec

object Eular007 {
    def main(args: Array[String]): Unit = {
        val start = 1
        val stop = 10001
        val x = (start to stop).foldLeft(1L)((res, _) => nextPrime(res + 1))
        println(s"result => $x")
    }

    def nextPrime(n: Long): Long ={
        @tailrec
        def _nextPrime(num: Long): Long = if(num.isPrime) num else _nextPrime(num + 1)
        _nextPrime(n)
    }
}