package chapter6

import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap
/**
  * As we discussed at the beginning of this chapter, the parameters fa and fb are independent of
  * one another: we can compute them in either order before passing them to product. This is in
  * contrast to flatMap, which imposes a strict order on its parameters. This gives us more freedom
  * when defining instances of Semigroupal than we get when defining Monads
  *
  * trait Semigroupal[F[_]] {
  * def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
  * }
  */
class MySemigroupal {

  import cats.Monad

  implicit def MN = Monad

  //def flatMap[A,B](value: F[A])(func : A => F[B]) : F[B]
  def product[M[_] : Monad, A, B](ma: M[A], mb: M[B]): M[(A, B)] =
    ma.flatMap(a => mb.map(b => (a, b)))
}