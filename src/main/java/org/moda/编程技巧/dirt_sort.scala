//package org.moda.编程技巧
//
//// 字典序排序
//object dirt_sort {
////  def dirt(str: String, res: List[String]) = {
////
////  }
//  def dirt(str: String, res: List[String]) = {
//    // str.foldRight((false, str))(()
//    val re = str.toCharArray.foldRight((false, str.length - 1: Int)){ (e, r) =>
//      if (r._2 == str.length - 1) (false, e)
//      else (r._1 || str.charAt(e - 1) < str.charAt(e) , r._2 - 1)
//    }
//
//    if (re._1 == false) res else dirt(re
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    vvvvaazZxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx)
//  }
//
//  def main(args: Array[String]): Unit = {
//
//  }
//}
