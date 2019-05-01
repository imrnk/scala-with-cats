package chapter2

case class Order(totalCost: Double, quantity: Double)

object MonoidInstance {

  implicit val intMonoid = new Monoid[Int] {
    override def combine(x: Int, y: Int): Int = x + y

    override def empty: Int = 0
  }

  implicit val optionMonoid = new Monoid[Option[Int]] {

    override def combine(x: Option[Int], y: Option[Int]): Option[Int] = x match {
      case Some(a) => y match {
        case Some(b) => Some(a + b)
        case None => Some(a)
      }
      case None => y
    }

    override def empty: Option[Int] = None
  }

  implicit val doubleMonoid = new Monoid[Double] {
    override def combine(x: Double, y: Double): Double = x + y

    override def empty: Double = 0.0
  }

  implicit val orderMonoid = new Monoid[Order] {
    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)

    override def empty: Order = Order(doubleMonoid.empty, doubleMonoid.empty)
  }
}

object SuperAdder {
  def add[A](items: List[A])(implicit m: Monoid[A]): A = {
    items.foldLeft(m.empty)((x, z) => m.combine(x, z))
  }


}
