package validationexample

import cats.data.NonEmptyList
import cats.data.Validated.Invalid
import org.scalatest.{FlatSpec, Matchers}

class ValidationWithTypedErrorSpec extends FlatSpec with Matchers {

  val vt = new ValidationWithTypedError

  "ValidationWithTypedError" should "validate correct email and password" in {

    val d = Data("email@email.com", "22002200")
    val res = vt.validateData(d)
    res.exists(x => x == d) should be(true)
  }

  it should "validate incorrect phone" in {
    val ind = Data("email@email.com", "1233aaaa")

    val res = vt.validateData(ind)

    res.leftMap(err => err.head.leftSide.code).leftSideValue should be(Invalid(ErrorCode.PhoneMustBeNumericCriteria))

  }

  it should "validate incorrect email" in {
    val ind = Data("email@emailcom", "123334222")

    val res = vt.validateData(ind)
    res.leftMap(err => err.head.leftSide.code).leftSideValue should be(Invalid(ErrorCode.InvalidCharactersInEmail))
  }

  it should "validate incorrect email and phone will fail on the first instance and will only return that error" in {
    val ind = Data("email@emailcom", "1233aa22")

    val res = vt.validateData(ind)
    println(res)

    res.toEither.left.get match {
      case NonEmptyList(head, tail) => {
        head should be(Err(ErrorCode.InvalidCharactersInEmail, "Invalid email"))
        tail.head should be(Err(ErrorCode.PhoneMustBeNumericCriteria, "Invalid phone"))

      }
    }


  }

}
