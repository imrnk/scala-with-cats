package validationexample

import org.scalatest.{FlatSpec, Matchers}

class ValidationWithEitherSpec extends FlatSpec with Matchers {

  val v = new ValidationWithEither

  "ValidationWithEither" should "validate correct email and phone" in {

    val d = Data("email@email.com", "22002200")

    val res = v.validateData(d)
    res.left.foreach(println)
    res.right should be(Data("email@email.com", "22002200"))

  }

  it should "validate incorrect phone" in {
    val ind = Data("email@email.com", "1233aaaa")

    val res = v.validateData(ind)
    res.left should be(List("Invalid phone"))

  }

  it should "validate incorrect email" in {
    val ind = Data("email@emailcom", "123334222")

    val res = v.validateData(ind)
    res.left should be(List("Invalid email"))
  }

  it should "validate incorrect email and phone will fail on the first instance and will only return that error" in {
    val ind = Data("email@emailcom", "1233aa22")

    val res = v.validateData(ind)
    res.left should be(List("Invalid email"))

  }
}
