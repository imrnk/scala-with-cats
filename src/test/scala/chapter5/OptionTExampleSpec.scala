package chapter5

import org.scalatest.{FlatSpec, Matchers}
import scala.concurrent.duration._

import scala.concurrent.Await

class OptionTExampleSpec extends FlatSpec with Matchers {

  "OptionTExample" should "compose greeting last name and first name from different context" in {
    val oe = new OptionTExample
    Await.result(oe.greetFromFuture, 2.second) should be(Some("Hello Jane Doe"))
  }
}
