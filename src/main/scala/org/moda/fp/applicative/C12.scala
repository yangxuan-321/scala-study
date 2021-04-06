package org.moda.fp.applicative

import org.moda.fp.monad.C11.Functor

trait Applicative[F[_]] extends Functor[F] {
  // 以下 是两个原语
  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]
  def unit[A](a: => A): F[A]

  // 以下是衍生
  def map[A, B](fa: F[A])(f: A => B): F[B] = {
    // (b: B) => map2()
    map2(fa, unit(Unit))((a, _) => f(a))
  }

  def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] =
    la.foldLeft(unit(List[B]()))((r, l) => map2(f(l), r)((fa, fb) => fa :: fb))

  def replicateM[A](n: Int, a: F[A]): F[List[A]] =
    (0 to n).foldLeft(unit(List[A]()))((r, _) => map2(a, r)((fa, fb) => fa :: fb))

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] =
    map2(fa, fb)((a, b) => (a, b))
}

object C12 {
  def func1(): Unit = {

  }

  def main(args: Array[String]): Unit = {

  }
}
