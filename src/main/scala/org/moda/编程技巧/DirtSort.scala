package org.moda.编程技巧

// 字典序排序
object DirtSort {
//  def dirt(str: String, res: List[String]) = {
//
//  }
  def dirt(str: String, res: List[String]): List[String] = {
    // str.foldRight((false, str))(()
//    val re = str.toCharArray.foldRight((false, str.length - 1: Int)){ (e, r) =>
//      if (r._2 == str.length - 1) (false, e)
//      else (r._1 || str.charAt(e - 1) < str.charAt(e) , r._2 - 1)
//    }

    var strcs = str.toCharArray
    val rs = (1 to strcs.length -1).foldRight((false, strcs.length-1))((e, r) => (r._1 || strcs(e) > strcs(e-1), if(r._1) r._2 else e))
    if (rs._1) {
      val ls = (rs._2 to strcs.length - 1).foldRight((false, 0))((e, r) => (r._1 || strcs(e) > strcs(rs._2-1), if(r._1) r._2 else e))
      if (ls._1) {
        // 交换
        val tmp = strcs(rs._2 - 1)
        strcs(rs._2 - 1) = strcs(ls._2)
        strcs(ls._2) = tmp
        // println(String.valueOf(strcs))
        val ress = res :+ String.valueOf(strcs)
        dirt(String.valueOf(strcs), ress)
      } else res
    } else res
  }

  def main(args: Array[String]): Unit = {
    println(dirt("0123456789", List("0123456789")))
  }
}
