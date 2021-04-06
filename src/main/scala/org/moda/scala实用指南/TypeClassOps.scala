package org.moda.scala实用指南

import scala.beans.BeanProperty


/**
  * @author MODA-Master
  * @Title: TypeClassOps
  * @ProjectName scala-study
  * @Description: TODO 类型别名
  * @date 20-6-13 下午10:36
  */
class ModaMaster20200613{
  @BeanProperty
  var name: String = "moda"
}

object TypeClassOps {
  def main(args: Array[String]): Unit = {
    type Moda = ModaMaster20200613
    val moda: Moda = new Moda
    println(moda.getName)
    println(moda.getClass)
  }
}
