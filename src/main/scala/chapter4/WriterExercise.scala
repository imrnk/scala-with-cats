package chapter4

import cats.data.Writer

class WriterExercise {

  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

  def factorial(n: Int): Int = {
    val ans = slowly(if (n == 0) 1 else n * factorial(n - 1))
    println(s"fact $n $ans")
    ans
  }

  type Logged[A] = Writer[Vector[String], A]

  def writerFactorial(n: Int): Logged[Int] = {
    import cats.instances.vector._
    import cats.syntax.applicative._
    import cats.syntax.writer._ // for Monoid

    for {
      ans <- if (n == 0)
        1.pure[Logged]
      else {
        slowly(writerFactorial(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
  }

}

object WriterExercise {
  val w = new WriterExercise

  def factorial(n: Int) = w.factorial(n)

  def writerFactorial(n: Int) = w.writerFactorial(n)
}