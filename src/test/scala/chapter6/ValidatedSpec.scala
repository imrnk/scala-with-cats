package chapter6

import cats.data.Validated
import cats.data.Validated.{Invalid, Valid}
import org.scalatest.{FlatSpec, Matchers}

class ValidatedSpec extends FlatSpec with Matchers {

  "ValidatedSpec" should "" in {
    Validated.valid(123) should be(Valid(123))
    Validated.invalid(List("Wham")) should be(Invalid(List("Wham")))


    Validated.catchOnly[NumberFormatException]("foo".toInt)
    // res7: cats.data.Validated[NumberFormatException,Int] = Invalid(java.lang.NumberFormatException: For input string: "foo")

    Validated.catchNonFatal(sys.error("Badness"))
    // res8: cats.data.Validated[Throwable,Nothing] = Invalid(java.lang.RuntimeException: Badness)

    Validated.fromTry(scala.util.Try("foo".toInt))
    // res9: cats.data.Validated[Throwable,Int] = Invalid(java.lang.NumberFormatException: For input string: "foo")

    Validated.fromEither[String, Int](Left("Badness"))
    // res10: cats.data.Validated[String,Int] = Invalid(Badness)
  }
}