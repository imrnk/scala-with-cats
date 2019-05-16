package chapter6

import cats.data.Validated

case class User(name: String, age: Int)

class FormValidation {

  type FailFast[A] = Either[List[String], A]
  type FailSlow[A] = Validated[List[String], A]
  type FormData = Map[String, String]

  def readUser(data: FormData): FailFast[User] =
    for {
      name <- readName(data)
      age <- readAge(data)
    } yield User(name, age)

  import cats.instances.list._ // for Semigroupal
  import cats.syntax.apply._ // for mapN

  def readUserFailSlow(data: FormData): FailSlow[User] =
    (Validated.fromEither(readName(data)), Validated.fromEither(readAge(data))).mapN(User.apply)

  def readName(data: FormData): FailFast[String] =
    getValue("name")(data).flatMap(nonBlanks("name"))

  def readAge(data: FormData): FailFast[Int] =
    getValue("age")(data).flatMap(parseInt("age")).flatMap(nonNegative("age"))

  def getValue(name: String)(data: FormData): FailFast[String] = data.get(name) match {
    case Some(value) => Right(value)
    case None => Left(List(s"$name is not present"))
  }

  import cats.syntax.either._ // for catchOnly
  type NumFmtExn = NumberFormatException

  def parseInt(name: String)(data: String): FailFast[Int] =
    Either.catchOnly[NumFmtExn](data.toInt).
      leftMap(_ => List(s"$name must be an integer"))

  def nonBlanks(name: String)(data: String): FailFast[String] =
    if (data.isBlank) Left(List(s"$name cannot be blank")) else Right(data)

  def nonNegative(name: String)(data: Int): FailFast[Int] =
    if (data > 0) Right(data) else Left(List(s"$name must be positive"))

}


