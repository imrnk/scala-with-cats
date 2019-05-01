/*
package chapter4

import cats.Id

trait MonadicSecretIdentity[Id[_]] {
  def pure[A] (a : A) :Id[A]
  def flatMap[A,B](value: Id[A])(func : A => Id[B]) : Id[B]
  def map[A,B](value : Id[A])(func : A => B) : Id[B] = flatMap(value)(a => pure(func(a)))
}

// type of Id[A] and A is same! So A => Id[B] is === A => B

class MonadicSecretIdentityInstances {
  implicit val intIdentity = new MonadicSecretIdentity[Id[Int]] {
    override def pure[A](a: A): Id[A] = a
    override def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)
    override def map[A, B](value: Id[A])(func: A => B): Id[B] = func(value)
  }
}
*/
