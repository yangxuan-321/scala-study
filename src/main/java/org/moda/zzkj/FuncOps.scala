package org.moda.zzkj

class Cat {
  def eat(food: String): Unit ={
    println(s"the cat eat => ${food}")
  }
}

object FuncOps {
  def main(args: Array[String]): Unit = {
    println("1".equals("1"))
    println("1" equals "2")

    val cookCat = new Cat()
    // 等效于 cookCat.eat("mouse")
    cookCat eat "mouse"

    // 变长参数值
    def max(values: Int*) = values.foldLeft(values(0)){Math.max}

    println(max(3, 5, 1, 100, 5, 8))

    val ints = Array(1, 2, 3, 4)
    println(max(ints: _*))

    def mail(dest: String = "yangxuan_321@163.com", content: String = "hello world!") = {println(s"${content} -> ${dest}")}

    mail("123", "hello yangxuan")
    mail(content = "hello yangxuan")
  }
}
