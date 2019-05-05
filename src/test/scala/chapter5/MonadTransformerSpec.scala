package chapter5

import cats.data.OptionT
import org.scalatest.{FlatSpec, Matchers}
import cats.syntax.applicative._ // for pure
import cats.instances.list._

class MonadTransformerSpec extends FlatSpec with Matchers {
  type ListOption[A] = OptionT[List, A]

  it should "compose two options of list of integers" in {

    val mt = new MonadTransformer
    val result1: ListOption[Int] = OptionT(List(Option(10)))

    val result2: ListOption[Int] = 32.pure[ListOption]

    mt.compose(result1, result2) should be(OptionT(List(Option(42))))
  }

  it should "add three numbers in string format" in {
    val mt = new MonadTransformer
    val res = mt.addAll("2", "3", "4")
    res.value should be(Some(9))
    res.written should be(List("read 2", "read 3", "read 4"))
  }

  it should "fail if a string cannot be parse as number" in {
    val mt = new MonadTransformer
    val res = mt.addAll("2", "b", "4")

    res.value should be(None)
    res.written should be(List("read 2", "failed to parse b"))
  }


}
