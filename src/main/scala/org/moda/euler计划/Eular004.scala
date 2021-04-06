package org.moda.euler计划;

object Eular004 {
    def main(args: Array[String]): Unit = {
        val start = 100
        val stop = 999
        val x = (start to stop).flatMap(c => (start to stop).map(d => (stop-c)*(stop-d))).toStream.filter(s => s.toString == s.toString.reverse).max
        println(s"result => $x")
    }
}
