package org.moda.euler

import scala.annotation.tailrec
import NumberUtils._

object Eular003 {
  def main(args: Array[String]): Unit = {
    val num = 600851475143l
    println(s"$num => ${eular003(num)}")
  }

  def eular003(num: Long): List[Long] = {
    @tailrec
    def _eular003(n: Long, m: Long, res: List[Long]): List[Long] ={
      if (n.isPrime) res:+n else if (n%m==0 && m.isPrime) _eular003(n/m, 2, res:+m) else _eular003(n, m+1, res)
    }
    _eular003(num, 2, List())
  }
}