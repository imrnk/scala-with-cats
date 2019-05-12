package validationexample

import cats.data.{Validated, ValidatedNel}
import cats.data.Validated.{Valid}
import cats.data.Validated._
import cats.implicits._

class ValidationWithNel {


  def validateEmail(email: String): ValidatedNel[String, String] = {
    val emailPattern = """^\w+@\w+[.]\w+$""".r

    emailPattern.findFirstIn(email) match {
      case None => Validated.invalidNel("Invalid email") //Invalid(NonEmptyList[String]("Invalid email", List.empty))
      case Some(email) => Valid(email)
    }
  }

  def validatePhone(phone: String): ValidatedNel[String, String] = {
    val phonePattern = """^[0-9-]+$""".r

    phonePattern.findFirstIn(phone) match {
      case None => Validated.invalidNel("Invalid phone")
      case Some(phone) => Valid(phone)
    }
  }

  def validateData(d: Data): ValidatedNel[String, Data] = {
    val validEmail = validateEmail(d.email)
    val validPhone = validatePhone(d.phone)

    (validEmail, validPhone).mapN(Data)
  }


}
