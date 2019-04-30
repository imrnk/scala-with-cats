package chapter2


trait SemiGroup[A] {
  def combine ( x: A, y : A) : A
}

trait Monoid[A] extends SemiGroup[A] {
  def empty : A
}

object Monoid {
  def apply[A](implicit monoid : Monoid[A]) = monoid

  def associativeLaw[A] (x : A, y : A, z : A)(implicit m : Monoid[A]) = {
    m.combine(m.combine(x, y), z) == m.combine(x, m.combine(y,z))
  }

  def identityLaw[A](x : A)(implicit m : Monoid[A]) = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }
}
