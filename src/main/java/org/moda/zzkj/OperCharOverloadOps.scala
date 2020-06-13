package org.moda.zzkj

class Dog(val name: String, val sex: String){
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
    println(baby)
  }
}
