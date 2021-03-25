package org.moda.scala函数式编程

/**
 * 语法分析器组合子
 */
class C9 {
}

trait Parsers[ParserError, Parser[+_]] { self =>
  def char(c: Char): Parser[Char]
  def run[A](p: Parser[A])(input: String): Either[ParseError, A]
  // 此处将 string 申明为隐式转换
  implicit def string(s: String): Parser[String]
  implicit def operators[A](p: Parser[A]) = ParserOps[A](p)
  def or[A](s1: Parser[A], s2: Parser[A]): Parser[A]
  case class ParserOps[A](p: Parser[A]) {
    def |[B>:A](p2: Parser[B]): Parser[B] = self.or(p, p2)
    def or[B>:A](p2: Parser[B]): Parser[B] = self.or(p, p2)
  }
}
