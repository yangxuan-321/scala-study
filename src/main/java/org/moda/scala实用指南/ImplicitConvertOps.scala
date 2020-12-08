package org.moda.scala实用指南

object MyStringUtils{
  implicit class Interpolator(val context: StringContext) extends AnyVal {
    def mask(args: AnyVal): StringBuilder = {
      new StringBuilder("sss")
    }
  }
}

object ImplicitConvertOps {
  def main(args: Array[String]): Unit = {

  }
}
