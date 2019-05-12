package validationexample

import cats.data.Validated.Invalid
import org.scalatest.{FlatSpec, Matchers}

class ValidationWithValidatedSpec extends FlatSpec with Matchers {

  val vv = new ValidationWithValidated

  "ValidationWithValidated" should "validate correct email and password" in {

    val d = Data("email@email.com", "22002200")
    val res = vv.validateData(d)
    res.exists(x => x == d) should be(true)
  }

  it should "validate incorrect phone" in {
    val ind = Data("email@email.com", "1233aaaa")

    val res = vv.validateData(ind)
    res.leftSideValue should be(Invalid(List("Invalid phone")))

  }

  it should "validate incorrect email" in {
    val ind = Data("email@emailcom", "123334222")

    val res = vv.validateData(ind)
    res.leftSideValue should be(Invalid(List("Invalid email")))
  }

  it should "validate incorrect email and phone will fail on the first instance and will only return that error" in {
    val ind = Data("email@emailcom", "1233aa22")

    val res = vv.validateData(ind)
    res.leftSideValue should be(Invalid(List("Invalid email", "invalid phone")))

  }
}
