package org.moda.work.tool

import java.io.File

import scala.io.Source

object Json1 {
  def main(args: Array[String]): Unit = {
    val c = Source.fromFile(new File("/opt/MODA/MODA-CODE/my-code/scala-study/src/main/java/org/moda/工作工具/1.json").getPath)
      .getLines().toList.filter(_.contains("signature")).filterNot(_.contains(""""signature":"","""))
     // c.map(cx => println(cx))
    // println(c.size)
    val cc = c.map(_.trim).map(_.replace(""""signature":"""", "")).map(_.replace("""",""", ""))
    // cc.map(cx => println(cx))

    val d = Source.fromFile(new File("/opt/MODA/MODA-CODE/my-code/scala-study/src/main/java/org/moda/工作工具/2.txt").getPath)
      .getLines().toList
      .filter(_.contains("OceanengineMaterialService"))
      .filter(_.contains("result: Right("))

    val xx = cc.filterNot(ccx => d.exists(_.contains(ccx)))
    xx.map(println)
  }
}
