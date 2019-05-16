package chapter6

import cats.data.Validated.Invalid
import org.scalatest.{FlatSpec, Matchers}

class FormValidationSpec extends FlatSpec with Matchers {

  val fv = new FormValidation
  it should "validate a valid user" in {
    val fd = Map("name" -> "Dave Jones", "age" -> "10")
    fv.readUser(fd) should be(Right(User("Dave Jones", 10)))
  }

  it should "fail in case of zero age" in {
    val fd = Map("name" -> "Dave Jones", "age" -> "0")
    fv.readUser(fd) should be(Left(List("age must be positive")))
  }

  it should "fail in case of negative age" in {
    val fd = Map("name" -> "Dave Jones", "age" -> "-2")
    fv.readUser(fd) should be(Left(List("age must be positive")))
  }

  it should "fail in case of non existent field" in {
    val fd = Map("" -> "")
    fv.readUser(fd) should be(Left(List("name is not present")))
  }

  it should "fail in case of invalid age value" in {
    val fd = Map("name" -> "Dave Jones", "age" -> "abc")
    fv.readUser(fd) should be(Left(List("age must be an integer")))
  }

  it should "fail in case of blank name value" in {
    val fd = Map("name" -> " ", "age" -> "22")
    fv.readUser(fd) should be(Left(List("name cannot be blank")))
  }

  it should "fail fast in case multiple error" in {
    val fd = Map("name" -> " ", "age" -> "-2")
    fv.readUser(fd) should be(Left(List("name cannot be blank")))
  }

  it should "fail show in case of Validated" in {
    val fd = Map("name" -> " ", "age" -> "-2")
    fv.readUserFailSlow(fd) should be(Invalid(List("name cannot be blank", "age must be positive")))
  }
}
