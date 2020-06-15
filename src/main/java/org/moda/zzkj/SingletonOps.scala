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

  private val defaultConf = new Config("application-dev.yml")

  private val confs = mutable.Map(
    "dev" -> new Config("application-dev.yml"),
    "test" -> new Config("application-test.yml"),
    "prod" -> new Config("application-prod.yml")
  )

  def getInstance(profile :String): Config = confs.getOrElse(profile, defaultConf)

  def apply(profile: String): Config = getInstance(profile)
}

object SingletonOps {
  def main(args: Array[String]): Unit = {
    val conf = Config.getInstance("dev")
    println(conf)

    val conf1 = Config.getInstance("test")
    println(conf1)

    val conf2 = Config("prod")
    println(conf2)

    val conf3 = Config("123")
    println(conf3)
  }
}
