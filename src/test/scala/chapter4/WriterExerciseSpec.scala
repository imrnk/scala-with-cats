package chapter4

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class WriterExerciseSpec extends FlatSpec with Matchers {

  /*it should "produce the factorial" in {
    WriterExercise.factorial(4)
  }*/

  it should "produce factorials from two different threads with interleaved messages from diff threads" in {
    Await.result(Future.sequence(Vector(
      Future(WriterExercise.factorial(3)),
      Future(WriterExercise.factorial(3))
    )), 5.seconds)
  }

  it should "produce factorials from two different threads with separate messages from diff threads" in {
    val writerResults: immutable.Seq[(Vector[String], Int)] = Await.result(Future.sequence(Vector(
      Future(WriterExercise.writerFactorial(3).run),
      Future(WriterExercise.writerFactorial(3).run)
    )), 5.seconds)

    writerResults.foreach(logResult => {
      logResult._1.foreach(l => print(l + " "))
      println(" " + logResult._2)
    }
    )

  }

}
