package org.moda.fp


import java.util.concurrent.{Callable, ExecutorService, Future}

import scala.concurrent.duration.TimeUnit


// 支持并行计算的集合
object Par {
  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: A): Par[A] = es => UnitFuture(a)

  private case class UnitFuture[A](get: A) extends Future[A] {
    def isDone = true

    def get(timeout: Long, units: TimeUnit) = get

    def isCancelled = false

    def cancel(evenIfRunning: Boolean): Boolean = false
  }

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] =
    es => UnitFuture(f(a(es).get, b(es).get))

  def fork[A] (a: => Par[A]): Par[A] =
    es => es.submit(new Callable[A] {
      override def call(): A = a(es).get
    })

  def lazyUnit[A](a: => A): Par[A] =
    e => {
      lazy val a_ = a
      UnitFuture(a_)
    }

  def asyncF[A, B] (f: A => B): A => Par[B] =
    a => lazyUnit({f(a)})

  // def run[A](a: Par[A]): A
}

object C7 {
  def main(args: Array[String]): Unit = {
    val dc = 10000
    val dx = BigDecimal(dc)
    println((dx * 4.56).toLong / 10000.0)

  }
}
