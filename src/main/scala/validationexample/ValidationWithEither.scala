package validationexample

case class Data(email: String, phone: String)

class ValidationWithEither {

  def validateEmail(email: String): Either[List[String], String] = {
    val emailPattern = """^\w+@\w+[.]\w+$""".r

    emailPattern.findFirstIn(email) match {
      case None => Left(List("Invalid email"))
      case Some(email) => Right(email)
    }
  }

  def validatePhone(phone: String): Either[List[String], String] = {
    val phonePattern = """^[0-9-]+$""".r

    phonePattern.findFirstIn(phone) match {
      case None => Left(List("Invalid phone"))
      case Some(phone) => Right(phone)
    }
  }

  def validateData(d: Data): Either[List[String], Data] = {
    for {
      e <- validateEmail(d.email)
      p <- validatePhone(d.phone)
    } yield Data(e, p)

  }
}
