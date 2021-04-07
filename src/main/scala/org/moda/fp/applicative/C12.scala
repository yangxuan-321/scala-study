package org.moda.fp.applicative

import org.moda.fp.monad.C11.Functor

/**
 * 使用 map2 和 unit 作为原语 进行推导
 * @tparam F
 */
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

/**
 * 使用 apply 和 unit 作为原语
 * @tparam F
 */
trait Applicative1[F[_]] extends Functor[F] {
  // 使用 map2 和 unit 实现 apply 函数
  def apply[A, B](fab: F[A => B])(fa: F[A]): F[B]

  def unit[A](a: => A): F[A]
  // 使用 unit 和 apply 实现 map
  def map[A, B](fa: F[A])(f: A => B): F[B] =
    apply(unit(f))(fa)
  // 使用 unit 和 apply 实现 map2
  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C) = {
    // val value: F[B => C] = apply(unit(f.curried))(fa)
    // apply(value)(fb)
    apply(map(fa)(f.curried))(fb)
  }

  def map3[A, B, C, D](fa: F[A], fb: F[B], fc: F[C])(f: (A, B, C) => D): F[D] =
    apply(apply(apply(unit(f.curried))(fa))(fb))(fc)

  def map4[A, B, C, D, E](fa: F[A], fb: F[B],
                          fc: F[C], fd: F[D])(f: (A, B, C, D) => E): F[E] =
    apply(apply(apply(apply(unit(f.curried))(fa))(fb))(fc))(fd)
}

trait Monad1[F[_]] extends Applicative1[F] {
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B] = join(map(fa)(f))
  def join[A](ffa: F[F[A]]): F[A] = flatMap(ffa)(x => x)
  def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C]
  override def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(x => unit(f(x)))
}

object C12 {
  def func1(): Unit = {

  }

  def main(args: Array[String]): Unit = {

  }
}
