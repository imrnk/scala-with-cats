package validationexample

import cats.data.{Validated, ValidatedNel}
import cats.data.Validated.Valid
import validationexample.ErrorCode.ErrorCode
import cats.data.Validated._
import cats.implicits._

object ErrorCode extends Enumeration {
  type ErrorCode = Value

  val InvalidCharactersInEmail, EmailDoesNotHaveDomainCriteria, PhoneMustBeNumericCriteria = Value
}

case class Err(code: ErrorCode, msg: String)

class ValidationWithTypedError {

  def validateEmail(email: String): ValidatedNel[Err, String] = {
    val emailPattern = """^\w+@\w+[.]\w+$""".r

    emailPattern.findFirstIn(email) match {
      case None => Validated.invalidNel(Err(ErrorCode.InvalidCharactersInEmail, "Invalid email")) //Invalid(NonEmptyList[String]("Invalid email", List.empty))
      case Some(email) => Valid(email)
    }
  }

  def validatePhone(phone: String): ValidatedNel[Err, String] = {
    val phonePattern = """^[0-9-]+$""".r

    phonePattern.findFirstIn(phone) match {
      case None => Validated.invalidNel(Err(ErrorCode.PhoneMustBeNumericCriteria, "Invalid phone"))
      case Some(phone) => Valid(phone)
    }
  }

  def validateData(d: Data): ValidatedNel[Err, Data] = {
    val validEmail = validateEmail(d.email)
    val validPhone = validatePhone(d.phone)

    (validEmail, validPhone).mapN(Data)
  }

}
