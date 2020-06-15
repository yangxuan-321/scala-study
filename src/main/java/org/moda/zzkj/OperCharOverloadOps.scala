package org.moda

class Dog(val name: String, var sex: String){
class Dog(val name: String, val sex: String){
  @BeanProperty
  var test: String = _

  def +(dog: Dog): Dog ={
   new Dog(s"${name} <-> ${dog.name}", s"${sex} <-> ${dog.sex}")
  }

  override def toString: String = {s"name: ${name}, sex: ${sex}"}
}

object Dog{
  def apply(name: String, sex: String): Dog = new Dog(name, sex)
}

/**
 * 操作符操作 操作
 */
object OperCharOverloadOps {
  def main(args: Array[String]): Unit = {
    val baby = new Dog("jams", "boy") + new Dog("kiki", "girl")
    println(baby)

    // 不写 new 是因为 其默认 调用了 object Dog 的apply方法
    val baby1 = Dog("hello", "boy") + Dog("world", "girl") + Dog("!", "NONE")
    println(baby1)
    println(baby.name)
    println(baby.test)
    println(baby.getTest)
  }
}
