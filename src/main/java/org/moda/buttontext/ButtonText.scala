package org.moda.buttontext

import org.moda.scalalolo.FileOps

import scala.io.Source

object ButtonText {
  def main(args: Array[String]): Unit = {
    val file = Source.fromFile("/home/yangxuan/Desktop/default_button_text.txt")
    file.getLines
      .map(_.replace("\"", ""))
      .filter(!_.isBlank).toList.distinct
      .map(println)
//    for (line <- file.getLines){
//      println(line)
//    }
  }
}
