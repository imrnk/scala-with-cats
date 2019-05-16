package chapter5

import cats.data.OptionT

class OptionTExample {

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import cats.implicits._

  val customGreeting: Future[Option[String]] = Future.successful(Some("welcome back, Lola"))

  val customGreetingT: OptionT[Future, String] = OptionT(customGreeting)

  val excitedGreeting: OptionT[Future, String] = customGreetingT.map(_ + "!")

  val greetingFO: Future[Option[String]] = Future.successful(Some("Hello"))

  val firstnameF: Future[String] = Future.successful("Jane")

  val lastnameO: Option[String] = Some("Doe")

  def greetFromFuture = {
    val ot: OptionT[Future, String] = for {
      g <- OptionT(greetingFO)
      f <- OptionT.liftF(firstnameF) // Option[A] and/or F[A] and want to lift them into an OptionT[F, A]
      l <- OptionT.fromOption[Future](lastnameO) //From Option[String] to Future[Option[String]]
    } yield s"$g $f $l"
    ot.value
  }
}
