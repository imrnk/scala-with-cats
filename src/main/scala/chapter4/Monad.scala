package chapter4

/*trait Monad[F[_]] {

  def pure[A] (a : A) :F[A]
  def flatMap[A,B](value: F[A])(func : A => F[B]) : F[B]
  def map[A,B](value : F[A])(func : A => B) : F[B] = flatMap(value)(a => pure(func(a)))

}*/

/*
object Monad {
  def apply[A,F[A]](implicit m : Monad[F[A]]): Monad[F[A]] = m

  def leftIdentityLaw[A, B, F[A]](value : A, func : A=> F[B])(implicit m: Monad[F[A]]) = {
    m.flatMap(m.pure(value))(func) == func(value)
  }

  def rightIdentityLaw[A, F[A]](value : A)(implicit m : Monad[F[A]]) = {
    m.flatMap(m.pure(value)) == m
  }

  def associativityLaw[A,B,C, F[A]](value : A) (f : A => F[B])(g: B => F[C])(implicit m: Monad[F[A]]) = {
    m.flatMap(f(value))(g) == m.flatMap(m.flatMap(f(value))(g))
  }
}
*/