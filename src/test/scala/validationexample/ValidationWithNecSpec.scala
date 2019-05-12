package validationexample

import cats.data.Chain
import cats.data.Validated.{Invalid, Valid}
import org.scalatest.{FlatSpec, Matchers}

class ValidationWithNecSpec extends FlatSpec with Matchers {

  "ValidationWithNec" should "validate correct email and phone" in {
    val d = Data("email@email.com", "22002200")
    FormValidatorNec.validateData(d) should be(Valid(d))
  }

  it should "validate incorrect phone" in {
    val ind = Data("email@email.com", "1233aaaa")
    FormValidatorNec.validateData(ind) should be(Invalid(Chain(PhoneNumberNumericCriteria)))
  }

  it should "validate incorrect email and phone" in {
    val inc = Data("email", "phone")
    println(FormValidatorNec.validateData(inc).toEither.left.get) //should be (Chain(EmailValidCriteria, PhoneNumberNumericCriteria))

  }
}
