package chapter6

import org.scalatest.{FlatSpec, Matchers}
import cats.Semigroupal
import cats.instances.option._ // for Semigroupal
import cats.syntax.apply._ // for tupled and mapN

class SemigroupalExampleSpec extends FlatSpec with Matchers {

  it should "join two different values" in {

    val product = Semigroupal[Option].product(Some(123), Some("abc"))
    product should be(Some((123, "abc")))
  }

  it should "return None if one of them is None" in {
    val product = Semigroupal[Option].product(None, Some("abc"))
    product should be(None)
  }

  it should "produce a semigroupal using tupled syntax" in {
    (Option(123), Option("abc")).tupled should be(Option((123, "abc")))
  }
}
