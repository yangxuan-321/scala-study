package org.moda.zzkj

import scala.beans.BeanProperty

class Dog(val name: String, val sex: String){
  @BeanProperty
  var test: String = _

  def +(dog: Dog): Dog ={
   new Dog(s"${name} <-> ${dog.name}", s"${sex} <-> ${dog.sex}")
  }

  override def toString: String = {s"name: ${name}, sex: ${sex}"}
}

/**
 * 操作符操作 操作
 */
object OperCharOverloadOps {
  def main(args: Array[String]): Unit = {
    val baby = new Dog("jams", "boy") + new Dog("kiki", "girl")
    println(baby.name)
    println(baby.test)
    println(baby.getTest)
  }
}
,