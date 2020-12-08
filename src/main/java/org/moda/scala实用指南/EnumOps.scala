package org.moda.scala实用指南

object FlumeSourceEnum extends Enumeration{
  type FlumeSourceEnum = Value

  val Avro, Thrift, Exec, JMS, Spooling_Directory = Value

  // 此处函数 返回值 还需要 考证
  def exec(sourceType: FlumeSourceEnum)= {
    sourceType match {
      case Avro => def fromAvaro(): Unit = println("from Avaro")
      case Thrift => def fromThrift(): Unit = println("from Thrift")
      case Exec => def fromExec(): Unit = println("from Exec")
      case JMS => def fromJMS(): Unit = println("from JMS")
      case Spooling_Directory => def fromSpooling_Directory(): Unit = println("from Spooling_Directory")
      case _ => def fromNone(): Unit = println("from None")
    }
  }
}

object EnumOps {
  def main(args: Array[String]): Unit = {
    val func = FlumeSourceEnum.exec(FlumeSourceEnum.Avro)
    func
  }
}
