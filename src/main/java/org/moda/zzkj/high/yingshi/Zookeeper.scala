package org.moda.zzkj.high.yingshi

class Dog[F[_]](val name: String, val sex: String){
  override def toString: String = s"name=>${name}, sex=>${sex}"
}

class Pig[F[_]](val age: Int, val weight: Int){
  override def toString: String = s"age=>${age}, weight=>${weight}"
}

class Zookeeper[F[_]]{
  var f: F[_] = _
  override def toString: String = s"f=>${f}"
}

object Zookeeper{
  def apply[F[_]](): Zookeeper[F] = new Zookeeper[F]
}


