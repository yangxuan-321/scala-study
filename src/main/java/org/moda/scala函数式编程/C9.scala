package org.moda.scala函数式编程

/**
 * 语法分析器组合子
 */
class C9 {
}

trait Parsers[ParserError, Parser[+_]] { self =>
  def char(c: Char): Parser[Char]
  def run[A](p: Parser[A])(input: String): Either[ParserError, A]
  // 此处将 string 申明为隐式转换
  implicit def string(s: String): Parser[String]
  implicit def operators[A](p: Parser[A]) = ParserOps[A](p)
  implicit def asStringParser[A](a: A)(implicit f: A => Parser[String]): ParserOps[String] = ParserOps(f(a))
  // 是否 满足
  def or[A](s1: Parser[A], s2: Parser[A]): Parser[A]
  // 识别 一个东西(p)是不是 重复了 n 次
  def listOfN[A](n: Int, p: Parser[A]): Parser[List[A]]
  // 识别 一个东西(p)重复 并返回重复结果
  def many[A](p: Parser[A]): Parser[List[A]]
  def map[A, B](a: Parser[A])(f: A => B): Parser[B]
  case class ParserOps[A](p: Parser[A]) {
    def |[B>:A](p2: Parser[B]): Parser[B] = self.or(p, p2)
    def or[B>:A](p2: Parser[B]): Parser[B] = self.or(p, p2)
  }
//  object Laws {
//    def equal[A](p1: Parser[A], p2: Parser[A])(in: Gen[String]): Prop = forAll(in)(s => run(p1)(s) == run(p2)(s))
//  }
}

