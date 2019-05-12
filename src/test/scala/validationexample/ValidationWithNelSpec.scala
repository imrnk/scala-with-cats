package validationexample

import cats.data.NonEmptyList
import cats.data.Validated.Invalid
import org.scalatest.{FlatSpec, Matchers}

class ValidationWithNelSpec extends FlatSpec with Matchers {

  val vn = new ValidationWithNel

  "ValidationWithNel" should "validate correct email and password" in {

    val d = Data("email@email.com", "22002200")
    val res = vn.validateData(d)
    res.exists(x => x == d) should be(true)
  }

  it should "validate incorrect phone" in {
    val ind = Data("email@email.com", "1233aaaa")

    val res = vn.validateData(ind)
    res.leftSideValue should be(Invalid(NonEmptyList("Invalid phone", List.empty)))

  }

  it should "validate incorrect email" in {
    val ind = Data("email@emailcom", "123334222")

    val res = vn.validateData(ind)
    res.leftSideValue should be(Invalid(NonEmptyList("Invalid email", List.empty)))
  }

  it should "validate incorrect email and phone will fail on the first instance and will only return that error" in {
    val ind = Data("email@emailcom", "1233aa22")

    val res = vn.validateData(ind)
    res.leftSideValue should be(Invalid(NonEmptyList("Invalid email", List("invalid phone"))))

  }

}
