package org.moda.practical_guide

/**
  * @author MODA-Master
  * @Title: ExtendsClass
  * @ProjectName scala-study
  * @Description: TODO 学习继承
  * @date 20-6-14 下午2:23
  */
class Duck(val duckType: String, val duckName: String){
  var duckSex: String = _

  override def toString: String = s"duckType=${duckType}, duckName=${duckName}"
}

// 北京鸭
// 如果 父类中 已经存在 相关的属性 则 子类 如果属性 再次出现 该字段 必须使用 override 重载修饰符 修饰
class PekingDuck(override val duckType: String, override val duckName: String) extends Duck(duckType, duckName) {
  var weight: Int = _

  // 辅助构造器
  def this(duckType: String, duckName: String, weightH: Int){
    this(duckType, duckName)
    weight = weightH
  }

  override def toString: String = s"${super.toString}, weight=${weight}"
}

object ExtendsClass {
  def main(args: Array[String]): Unit = {
    val duck = new PekingDuck("PekingDuck", "小红")
    println(duck)

    val duck_ = new PekingDuck("PekingDuck", "小红", 12)
    println(duck_)

    println(new Duck("NONE", "NONE"))
  }
}
