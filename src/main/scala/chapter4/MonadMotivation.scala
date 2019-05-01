package chapter4

import cats.Monad
import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap
import scala.language.higherKinds

/**
  * Each of these methods may “fail” by returning None.
  * The flatMap method allows us to ignore this when we sequence operations
  *
  * Option[A] -> flatMap -> A => Option[B] -> Option[B]
  */
class MonadMotivation {

  def parseInt(str: String): Option[Int] =
    scala.util.Try(str.toInt).toOption

  def divide(a: Int, b: Int): Option[Int] =
    if (b == 0) None else Some(a / b)

  def stringDividedBy(aStr: String, bStr: String) = {
    parseInt(aStr)
      .flatMap { aInt =>
        parseInt(bStr)
          .flatMap { bInt => divide(aInt, bInt)
          }
      }
  }

  def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] =
    a.flatMap(x => b.map(y => x * x + y * y))

}

object MonadMotivation {

  def stringDivision(aStr: String, bStr: String) = {
    val mm = new MonadMotivation
    mm.stringDividedBy(aStr, bStr)
  }

  def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] = {
    val mm = new MonadMotivation
    mm.sumSquare(a, b)
  }
}