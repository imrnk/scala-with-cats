package scalaexercisecats

import cats.kernel.Semigroup
import org.scalatest.{FlatSpec, Matchers}
import cats.implicits._

class semigroupspec extends FlatSpec with Matchers {


  it should "combine two integers" in {
    Semigroup[Int].combine(1, 2) should be(3)
  }

  it should "combine two lists of integers" in {
    Semigroup[List[Int]].combine(List(1, 2, 3), List(3, 4, 5)) should be(List(1, 2, 3, 3, 4, 5))
  }

  it should "combine an optional integer and none" in {
    Semigroup[Option[Int]].combine(Option(1), None) should be(Option(1))
  }

  it should "even combine two functions one after another" in {
    Semigroup[Int => Int].combine(_ + 1, _ * 10).apply(6) should be(67)
  }

  it should "combine two maps value" in {
    val oneMap = Map("foo" -> Map("bar" -> 5))
    val otherMap = Map("foo" -> Map("bar" -> 6))

    val combinedMap = Semigroup[Map[String, Map[String, Int]]].combine(oneMap, otherMap)
    combinedMap.get("foo") should be(Some(Map("bar" -> 11)))

  }


}
