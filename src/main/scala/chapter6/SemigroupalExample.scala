package chapter6

import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap
/**
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