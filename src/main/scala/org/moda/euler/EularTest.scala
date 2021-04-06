package org.moda.euler

object EularTest {

  def printS():Int = {
    var i1 = 0
    var i2 = 1
    var t = 0
    while (true){
      if (i2 > 400 * 10000 || i2 < 0){
        return 0
      }
      if (i2%2 == 0){
        printf(s"${i2}+")
      }
      t = i1 + i2
      i1 = i2
      i2 = t
    }
    return 0
  }

  def main(args: Array[String]): Unit = {
    printS()
  }
}
