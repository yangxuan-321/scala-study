package org.moda.scala基础

import java.io.File

import scala.io.Source

/**
  * @author MODA-Master
  * @Title: FileOps
  * @ProjectName scala-study
  * @Description: TODO 文件操作学习
  * @date 20-6-9 下午9:40
  */
object FileOps {
  def main(args: Array[String]): Unit = {
    // 文件读取
//    val file = Source.fromFile(FileOps.getClass.getResource("/file1.txt").getPath)
//    val netFile = Source.fromURL("http://www.shicimingju.com/chaxun/list/2973.html")
//
//
//    // file.getLines 就是 一个 迭代器
//    for (line <- file.getLines){
//      println(line)
//    }
//
//    println
//
//    for (line <- netFile.getLines()){
//      println(line)
//    }
    val c = Source.fromFile(new File("/home/yangxuan/Desktop/code_bak/bad.txt").getPath).getLines().mkString(" ")
    println(c)
  }
}
