package chapter4

import chapter5.MonadTransformerExercise
import org.scalatest.{FlatSpec, Matchers}
import scala.concurrent.duration._
import scala.concurrent.Await

class MonadTransformerExerciseSpec extends FlatSpec with Matchers {

  it should "return the power level of existing autobot, if the autobot doesn't exist, return an error message" in {

    val mte = new MonadTransformerExercise
    Await.result(mte.getPowerLevel("Jazz").value, 2.second) should be(Right(6))
    Await.result(mte.getPowerLevel("heroku").value, 2.second) should be(Left("power level of heroku not found"))
  }

  it should "return true if two ally's combined power is greater than 15" in {
    val mte = new MonadTransformerExercise
    Await.result(mte.canSpecialMove("Jazz", "Hot Rod").value, 2.second) should be(Right(true))
  }

  it should "return false if two ally's combined power is less than 15" in {
    val mte = new MonadTransformerExercise
    Await.result(mte.canSpecialMove("Jazz", "Bumblebee").value, 2.second) should be(Right(false))
  }

  it should "return error message if at least any one of the ally is unavailable" in {
    val mte = new MonadTransformerExercise
    Await.result(mte.canSpecialMove("Jazz", "heroku").value, 2.second) should be(Left("power of heroku doesn't exist"))
  }

  it should "produce tactical report based on power levels of the allies" in {
    val mte = new MonadTransformerExercise
    mte.tacticalReport("Jazz", "Hot Rod") should be("Jazz and Hot Rod are ready to roll out!")
    mte.tacticalReport("Jazz", "Bumblebee") should be("Jazz and Bumblebee need a recharge")
    mte.tacticalReport("Jazz", "Ironhead") should be("power of Ironhead doesn't exist")
  }
}
