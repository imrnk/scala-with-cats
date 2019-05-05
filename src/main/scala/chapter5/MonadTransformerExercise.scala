package chapter5


import cats.data.EitherT

import scala.concurrent.{Await, Future}

/**
  * For example, let’s create a Future of an Either of Option. Once again we build this from the inside out
  * with an OptionT of an EitherT of Future.
  *
  * type FutureEither[A] = EitherT[Future, String, A]
  * type FutureEitherOption[A] = OptionT[FutureEither, A]
  */

class MonadTransformerExercise {


  //Future[Either[String, A]]
  //OptionT[List, A] transform List[Option[A]] to a single monad
  type Response[A] = EitherT[Future, String, A] //Future[Either[String, Int]]
  private val powerLevels = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def getPowerLevel(autobot: String): Response[Int] = {
    import scala.concurrent.{Future}
    import scala.concurrent.ExecutionContext.Implicits.global
    import cats.instances.future._ // for Monad

    powerLevels.get(autobot) match {
      case None => EitherT.left(Future(s"power level of $autobot not found"))
      case Some(level) => EitherT.right(Future(level))
    }
  }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] = {
    import scala.concurrent.{Future}
    import scala.concurrent.ExecutionContext.Implicits.global
    import cats.instances.future._ // for Monad
    import cats.syntax.applicative._ //for pure

    powerLevels.get(ally1) match {
      case None => EitherT.left(Future(s"power of $ally1 doesn't exist"))
      case Some(lvl1) => powerLevels.get(ally2) match {
        case None => EitherT.left(Future(s"power of $ally2 doesn't exist"))
        case Some(lvl2) => if (lvl1 + lvl2 > 15) true.pure[Response] else false.pure[Response]
      }
    }

  }

  def tacticalReport(ally1: String, ally2: String): String = {
    import scala.concurrent.duration._
    Await.result(canSpecialMove(ally1, ally2).value, 2.seconds) match {
      case Right(true) => s"$ally1 and $ally2 are ready to roll out!"
      case Right(false) => s"$ally1 and $ally2 need a recharge"
      case Left(message) => message
    }
  }

}
