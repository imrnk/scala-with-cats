package chapter5

import cats.data.{OptionT, Writer}
import cats.instances.list._

import scala.util.Try // for Monad


class MonadTransformer {

  type ListOption[A] = OptionT[List, A]

  def compose(result1: ListOption[Int], result2: ListOption[Int]) = result1.flatMap {
    (x: Int) =>
      result2.map {
        (y: Int) => x + y
      }
  }

  type Logged[A] = Writer[List[String], A]

  def parseNumber(str: String): Logged[Option[Int]] = {
    Try(Integer.parseInt(str)).toOption match {
      case None => Writer(List(s"failed to parse $str"), None)
      case Some(num) => Writer(List(s"read $num"), Some(num))
    }
  }

  def addAll(a: String, b: String, c: String): Logged[Option[Int]] = {
    val res: OptionT[Logged, Int] = for {
      ai <- OptionT(parseNumber(a))
      bi <- OptionT(parseNumber(b))
      ci <- OptionT(parseNumber(c))
    } yield (ai + bi + ci)

    res.value
  }

}
