package chapter3

import cats.Functor
import cats.instances.list._

class CatsFunctor {

  def identityLaw[T](list: List[T]) = Functor[List].map(list)(x => x) == list

  def compositionLaw[T, S, Q](list: List[T])(f: T => S)(g: S => Q) = {
    val appF = Functor[List].map(list)(f)
    val andThenG: Seq[Q] = Functor[List].map(appF)(g)
    val rhs: Seq[Q] = Functor[List].map(list)(l => g(f(l)))
    andThenG == rhs
  }

  /*def liftedFunc[A,B,T](f : A => B)(implicit functor : Functor[T]) = {
    Functor[T].lift(f)
  }*/
}

object CatsFunctor {

  /*def lifting[A,B,T](f: A=> B)(implicit functor: Functor[T])={
    val cf = new CatsFunctor
    cf.liftedFunc(f)(functor)
  }*/

  def identity[T](ls: List[T]): Boolean = {
    val cf = new CatsFunctor
    cf.identityLaw(ls)
  }

  def composition[T, S, Q](ls: List[T])(f: T => S)(g: S => Q) = {
    val cf = new CatsFunctor
    cf.compositionLaw(ls)(f)(g)
  }

  def main(args: Array[String]): Unit = {

    val cf = new CatsFunctor

    cf.identityLaw(List(1, 2, 3))
    cf.compositionLaw(List(1, 2, 3))(x => x + 1)(x => x + "")
  }
}
