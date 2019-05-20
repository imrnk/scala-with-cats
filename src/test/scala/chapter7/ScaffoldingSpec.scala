package chapter7

import org.scalatest.{FlatSpec, Matchers}

class ScaffoldingSpec extends FlatSpec with Matchers {

  "Scaffolding" should "be able to map over a list of ints" in {
    Scaffolding.mapUsingFoldRight(List(1, 2, 3))(_ * 2) should be(List(2, 4, 6))
  }

  it should "be able to flatMap over a list of ints" in {
    Scaffolding.flatMapUsingFoldRight(List(1, 2, 3))(e => List(e, e * 2)) should be(List(1, 2, 2, 4, 3, 6))
  }

  it should "be able to filter a list on given predicate" in {
    Scaffolding.filterUsingFoldRight(List(-1, 2, 3, -5, 6))(e => e > 0) should be(List(2, 3, 6
    ))
  }

}
