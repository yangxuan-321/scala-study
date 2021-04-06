package org.moda.practical_guide.high.yingshi



// F 代表的就是泛型集合
class HttpService[F[_] :Dog :Pig :Zookeeper](){
  val zookeeper: Zookeeper[F] = implicitly[Zookeeper[F]]
  val dog: Dog[F] = implicitly[Dog[F]]
  val pig: Pig[F] = implicitly[Pig[F]]
}

object HttpService{
  def apply[F[_] :Dog :Pig :Zookeeper](): HttpService[F] = new HttpService[F]()
}

object ImplicitStudy {
//  implicit val zookeeper: Zookeeper[Int] = new Zookeeper[Int]
//  implicit val dog: Dog[Int] = new Dog[Int]("a", "b")
//  implicit val pig: Pig[Int] = new Pig[Int](1, 2)
//
  def main(args: Array[String]): Unit = {
//    val value = HttpService[Int[_]]()
//    println(value.dog)
//    println(value.pig)
//    println(value.zookeeper)
  }
}
