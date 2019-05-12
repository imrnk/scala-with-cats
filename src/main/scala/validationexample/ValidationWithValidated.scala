package validationexample

import cats.data._
import cats.data.Validated._
import cats.implicits._

class ValidationWithValidated {


  def validateEmail(email: String): Validated[List[String], String] = {
    val emailPattern = """^\w+@\w+[.]\w+$""".r

    emailPattern.findFirstIn(email) match {
      case None => Invalid(List("Invalid email"))
      case Some(email) => Valid(email)
    }
  }

  def validatePhone(phone: String): Validated[List[String], String] = {
    val phonePattern = """^[0-9-]+$""".r

    phonePattern.findFirstIn(phone) match {
      case None => Invalid(List("Invalid phone"))
      case Some(phone) => Valid(phone)
    }
  }

  def validateData(d: Data): Validated[List[String], Data] = {
    val validEmail = validateEmail(d.email)
    val validPhone = validatePhone(d.phone)

    (validEmail, validPhone).mapN(Data)
  }

}
