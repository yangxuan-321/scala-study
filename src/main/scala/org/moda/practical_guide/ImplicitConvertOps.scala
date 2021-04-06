package org.moda.practical_guide

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
