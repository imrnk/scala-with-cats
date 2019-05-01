package eitherexample


import eitherxample.{Input, RecoverWithEither}
import org.scalatest.{FlatSpec, Matchers}

class RecoverWithEitherSpec extends FlatSpec with Matchers {

  it should "return the list of reporting set values wrapped in Right of cobdate is not 28092018" in {

    val re = new RecoverWithEither()
    val inputs = List(Input("31122018", 4, 124.24),
      Input("31122018", 2, 120.20), Input("31122018", 1, 56.35))

    val res: Seq[Either[String, Double]] = re.tsAggregationProcess(inputs)

    res.foreach(println)

    val acumVal: Seq[Double] = res.collect { case Right(item) => item }

    /*val (errors, items) = res.foldRight[(List[String], List[Double])](Nil,Nil) {
      case (Left(error), (e, i)) => (error :: e, i)
      case (Right(result), (e, i)) => (e, result :: i)
    }*/

    acumVal.reduce(_ + _) should be(124.24 + 120.2 + 56.35)
  }

  it should "return the Error wrapped in Left if one of the cobdate is 28092018" in {

    val re = new RecoverWithEither()
    val inputs = List(Input("31122018", 4, 124.24),
      Input("31122018", 2, 120.20), Input("28092018", 1, 56.35))

    val res: Seq[Either[String, Double]] = re.tsAggregationProcess(inputs)

    res.foreach(println)

    val (errors, resVal) = res.partition(_.isLeft)

    val errorMessage: Seq[String] = errors.collect { case Left(err) => err }
    val rightVal: Seq[Double] = resVal.collect { case Right(value) => value }

    errorMessage.size should be(1)
    errorMessage(0) should startWith("Wrong cobdate")

    rightVal.size should be(2)
  }
}
