package chapter2

import org.scalatest.{FlatSpec, Matchers}

class SuperAdderSpec extends FlatSpec with Matchers {
  it should "Add together Ints" in {
    SuperAdder.add(List(1, 2, 3))(MonoidInstance.intMonoid)  should be(6)
    SuperAdder.add(List[Int]())(MonoidInstance.intMonoid) should be(0)
  }

  it should "Add together Options of Ints" in {
    val input1: List[Option[Int]] = List(Some(1), Some(2), Some(3))
    SuperAdder.add(input1)(MonoidInstance.optionMonoid) should be(Some(6))
    SuperAdder.add(List(Some(1), None, Some(3)))(MonoidInstance.optionMonoid) should be(Some(4))
    SuperAdder.add(List[Option[Int]]())(MonoidInstance.optionMonoid) should be(None)
  }

  it should "Create and empty Order and add two orders together" in {

    Monoid[Order](MonoidInstance.orderMonoid).empty should be(Order(0, 0))

    SuperAdder.add(List(Order(1, 1), Order(2, 2)))(MonoidInstance.orderMonoid) should be(Order(3, 3))

  }

}
