package org.moda.fp.monad

case class Id[A] (value: A) {
  def map[B](f: A => B): Id[B] =
    Id(f(value))
  def flatMap[B](f: A => Id[B]): Id[B] =
    f(value)
}

case class State[S, A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] = {
    State((s: S) => {
      val (a, s1) = run(s)
      (f(a), s1)
    })
  }

  def flatMap[B](f: A => State[S, B]): State[S, B] =
    State((s: S) => {
      val (a, s1) = run(s)
      f(a).run(s1)
    })
}

object C11 {
  /**
   * 我们在每个案例中都编写了 少许的原语，然后使用这些原语来定义组合子。这些组合子的相似性是值得注意的
   * 比如，我们为每个数据类型 都编写了 map 函数，用与提升某个数据类型 到 上下文中的数据类型。对于
   * Gen、 Parser、 Option，类型签名如下：
   * def map[A, B](g: Gen[A])(f: A => B): Gen[B]
   * def map[A, B](p: Parser[A])(f: A => B): Parser[B]
   * def map[A, B](o: Option[A])(f: A=> B): Option[B]
   * 这些类型签名仅仅是数据类型不同(Gen, Option, Parser)
   */
  trait Functor[F[_]] {
    def map[A, B](a: F[A])(f: A => B): F[B]

    def distribute[A, B](ab: F[(A, B)]): (F[A], F[B]) =
      (map(ab)(_._1), map(ab)(_._2))
  }

  trait Mon[F[_]] {
    def map[A, B](a: F[A])(f: A => B): F[B]
    def flatMap[A, B](a: F[A])(f: A => F[B]): F[B]
    def map2[A, B, C](a: F[A], b: F[B])(f: (A, B) => C): F[C] =
      flatMap(a)(x => map(b)(x1 => f(x, x1)))
  }

  // 在上面我们展示了map2 是可以通过 map 和 flatMap 来组合实现。那么map和flatMap
  // 就是最小的原始操作集合了吗？ 其实 map 可以用 flatMap + unit 实现
  // 例如Option 我们 可以 从 根本上来看 使用 flatMap 和 unit 可以组合出 map
  // 通过 map 和 flatMap 可以组合出 map2 所以。 unit 和 flatMap 是最先的原始集合
  def unit[A](a: => A): Option[A] = Option(a)
  def flatMap[A, B](a: Option[A])(f: A => Option[B]): Option[B] =
    a match {
      case Some(v) => f(v)
      case _ => None
    }
  def map[A, B](a: Option[A])(f: A => B): Option[B] =
    flatMap(a)((x: A) => unit(f(x)))


  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    // val xx: Option[Option[C]] = a.map(x => b.map(x1 => f(x, x1)))
    // val xxx: Option[C] = xx.flatten

    // 简化1
    // val xx: Option[C] = a.map(x => b.map(x1 => f(x, x1))).flatten

    // 简化2
    // val xx: Option[C] = a.flatMap { x => b.map(x1 => f(x, x1))}

    // 简化3
    a flatMap (x => b map (x1 => f(x, x1)))
  }

  /** 那么我们选择 unit 和 flatMap 做个最小的操作集合。将这些函数定义的所有数据类型都统一在
   * 一个概念下。这个trait称为Monad，它包括flatMap和unit的抽象，并且提供默认的map和map2的实现
   */
  trait Monad[F[_]] extends Functor[F] {
    def unit[A](a: A): F[A]
    def flatMap[A, B](a: F[A])(f: A => F[B]): F[B]
    def map[A, B](a: F[A])(f: A => B): F[B] =
      flatMap(a)((a: A) => unit(f(a)))
    def map2[A, B, C](a: F[A], b: F[B])(f: (A, B) => C): F[C] =
      flatMap(a){ (ax: A) => map(b)((bx: B) => f(ax, bx)) }
    def sequence[A](lma: List[F[A]]): F[List[A]] =
      lma.foldRight(unit(List[A]()))((l, r) => map2(l ,r)((lx, rx) => lx :: rx))
    def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] =
      la.foldRight(unit(List[B]()))((l, r) => map2(f(l), r)(_ :: _))
    def replicateM[A](n: Int, ma: F[A]): F[List[A]] =
      sequence(List.fill(n)(ma))
    def filterM[A](ms: List[A])(f: A => F[Boolean]): F[List[A]] =
      ms match {
        case head :: tail => flatMap(f(head))(b => {
          if (!b) filterM(tail)(f)
          else map(filterM(tail)(f))(head :: _)
        })
        case Nil => unit(Nil)
      }
    def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] =
      (a: A) => flatMap(f(a))(g)
    // 我们也可以通过 compose 实现 flatMap
    def flatMap1[A, B](a: F[A])(f: A => F[B]): F[B] =
      compose((_:Unit) => a, f)(())

  }

  def init(): Unit = {
    val listFunctor = new Functor[List] {
      override def map[A, B](a: List[A])(f: A => B): List[B] = a map f
      // 对于list的distribute 就是 List[(A, B)] => (List[A], List[B])
      // 其实这就是一个标准的unzip操作
    }

    // 对list 实现 Monad
    val listMonad = new Monad[List] {
      override def unit[A](a: A): List[A] = List(a)

      override def flatMap[A, B](a: List[A])(f: A => List[B]): List[B] =
        a.flatMap(f) // 或者使用 尾递归实现
    }

    val xx: Seq[Int] = listMonad.map(List(1, 2, 3))(_ + 1)
    println(xx)

    // 实现Option的Monad
    val optionMonad = new Monad[Option] {
      override def unit[A](a: A): Option[A] = Option(a)
      override def flatMap[A, B](a: Option[A])(f: A => Option[B]): Option[B] = a match {
        case None => None
        case Some(value) => f(value)
      }
    }
    println(Option(1).map(_ + 1))

    val streamMonad = new Monad[Stream] {
      override def unit[A](a: A): Stream[A] = Stream(a)
      override def flatMap[A, B](a: Stream[A])(f: A => Stream[B]): Stream[B] =
        a flatMap f
    }


  }

  // 实现State的Monad
//  class StateMonadS[S] {
//    type StateS[A] = State[S, A]
//
//    new Monad[StateS] {
//      def unit[A](a: => A): State[S, A] = s => (a, s)
//
//      override def flatMap[A, B](a: State[S, A])(f: A => State[S, B]): State[S, B] =
//        a flatMap f
//    }
//  }

  def idMonad = {
    val idMonad1 = new Monad[Id] {
      override def unit[A](a: A): Id[A] = Id(a)
      override def flatMap[A, B](a: Id[A])(f: A => Id[B]): Id[B] = a flatMap f
    }

    // val v: Id[String] = idMonad1.map(Id("hello "))(_ + "world!")
    val v: Id[String] = for {
      a <- Id("hello ")
      b <- Id("world!")
    } yield a + b
    println(v)
  }

  def stateMonadS(): Unit = {
    // new Monad[({type IntState[A] = State[Int, A]})#IntState] {
    //   override def unit[A](a: A): State[Int, A] =
    // }
    def stateMonad[S] =
      new Monad[({type lambda[X] = State[S, X]})#lambda] {
        override def unit[A](a: A): State[S, A] = State(s => (a, s))
        override def flatMap[A, B](a: State[S, A])(f: A => State[S, B]): State[S, B] = a flatMap f
      }

    def getState[S]: State[S, S] = State(s => (s,s))
    def setState[S](s: => S): State[S, Unit] = State(_ => ((),s))

    val vv: State[Int, Int] = stateMonad.unit(11)
    println(vv.run(22))

    val F = stateMonad[Int]
    def zipWithIndex[A](as: List[A]): List[(Int, A)] =
      as.foldLeft(F.unit(List[(Int, A)]()))((acc, a) => for {
        xs <- acc
        n <- getState
        _ <- setState(n + 1)
      } yield (n, a) :: xs).run(0)._1.reverse
  }

  def main(args: Array[String]): Unit = {
    // stateMonadS
    val a = List(1, 2, 3)
    val b = List(11, 22, 33)
    val cc = for {
      aa <- a
      bb <- b
    } yield (aa, bb)
  }
}
