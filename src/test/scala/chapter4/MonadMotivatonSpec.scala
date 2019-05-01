package chapter4

import org.scalatest.{FlatSpec, Matchers}

class MonadMotivatonSpec extends FlatSpec with Matchers {

  it should "return the division result in integer wrapped in Option or in case of failure return None" in {
    MonadMotivation.stringDivision("18", "3") should be(Some(6))
    MonadMotivation.stringDivision("1", "0") should be(None)
    MonadMotivation.stringDivision("a", "2") should be(None)
  }

  it should "sum the square of list of numbers and wrap it the passed container" in {
    import cats.instances.option._ // for Monad
    import cats.instances.list._ // for Monad
    MonadMotivation.sumSquare(List(1, 2), List(3)) should be(List(10, 13))
    MonadMotivation.sumSquare(Option(4), Option(3)) should be(Option(25))
  }

  /**
    * Id allows us to call our monadic method using plain values
    * type Id[A] = A
    */
  it should "also accept non-monadic value and sum the square of them" in {
    import cats.Id
    MonadMotivation.sumSquare(3: Id[Int], 4: Id[Int]) should be(25)
  }
}
