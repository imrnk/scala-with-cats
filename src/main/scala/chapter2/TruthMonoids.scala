package chapter2

object BooleanMonoidInstance {
  implicit val orMonoid = new Monoid[Boolean] {

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
    override def empty: Boolean = true
  }

  implicit val andMonoid = new Monoid[Boolean] {

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
    override def empty: Boolean = false
  }

  implicit val xorMonoid = new Monoid[Boolean] {

    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
  }

  implicit val xnorMonoid = new Monoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (x || !y ) && (!x || y)

    override def empty: Boolean = true

  }
}
