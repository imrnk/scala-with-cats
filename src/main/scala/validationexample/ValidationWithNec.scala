package validationexample

import cats.data.ValidatedNec

sealed trait DomainValidation {
  def errorMessage: String
}

case object EmailHasSpecialCharacters extends DomainValidation {
  def errorMessage: String = "Email cannot have special characters"
}

case object PhoneNumberNumericCriteria extends DomainValidation {
  def errorMessage: String = "Phone number cannot have non-digit characters"
}

case object EmailValidCriteria extends DomainValidation {
  def errorMessage: String = "Email must have @ sign and domain name"
}

import cats.data.Validated._
import cats.implicits._

sealed trait FormValidatorNec {

  type ValidationResult[A] = ValidatedNec[DomainValidation, A]

  private def validateEmail(email: String): ValidationResult[String] =
    if (email.matches("""^\w+@\w+[.]\w+$""")) email.validNec else EmailValidCriteria.invalidNec

  private def validatePhone(phone: String): ValidationResult[String] =
    if (phone.matches("""^[0-9]+$""")) phone.validNec
    else PhoneNumberNumericCriteria.invalidNec

  def validateData(d: Data): ValidationResult[Data] = {
    val validEmail = validateEmail(d.email)
    val validPhone = validatePhone(d.phone)

    (validEmail, validPhone).mapN(Data)
  }
}

object FormValidatorNec extends FormValidatorNec
