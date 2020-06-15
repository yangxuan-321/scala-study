package org.moda.zzkj

/**
  * @author MODA-Master
  * @Title: SingletonOps
  * @ProjectName scala-study
  * @Description: TODO 单例
  * @date 20-6-14 下午9:25
  */

import scala.collection._

class Config private(val profile: String){
  override def toString: String = s"port = ${profile}"
}

object Config{
  private val confs = mutable.Map(
    "dev" -> new Config("application-dev.yml"),
    "test" -> new Config("application-test.yml"),
    "prod" -> new Config("application-prod.yml")
  )

  def getInstance(profile :String): Option[Config] = confs.get(profile)
}

object SingletonOps {
  def main(args: Array[String]): Unit = {
    val conf = Config.getInstance("dev").get
    println(conf)

    val conf1 = Config.getInstance("test").get
    println(conf1)
  }
}
