package eitherxample

import cats.data.{Writer}
import cats.syntax.either._


class Result(cobDate: String, df: List[String])

object TSAggregation {

  type ResultLog[A] = Writer[Vector[String], A]

  /*def process2(inputs : List[Input]): ResultLog[Option[Double]]  = {
    import cats.syntax.applicative._

     inputs.foldLeft(Writer(Vector(""), None)) {
       (z, ins) => {
         if(ins.cobDate == "28092018") {
           Writer(Vector(s"wrong cobdate ${ins.cobDate} for ${ins.reportingSetId}"), None)
         } else {
           z.value.flatMap((zz:Double) => Option(zz+ins.value)).pure[ResultLog]
         }
       }
     }
  }*/

  def process(inputs: List[Input]): Seq[Either[String, Double]] = {

    inputs.foldLeft(0.0.asRight[String]) {
      (z, v) =>
        if (v.cobDate == "28092018") {
          val cd = v.cobDate
          val rsi = v.reportingSetId
          Left(s"Wrong cobdate $cd of $rsi .")
        } else {
          z.map(_ + v.value)
        }
    }

    val x: Seq[Either[String, Double]] = inputs.flatMap { i => {
      if (i.cobDate == "28092018") {
        List(s"Wrong cobdate ${i.cobDate} of reportingSetId: ${i.reportingSetId}".asLeft[Double])
      } else {
        List(i.value.asRight[String])
      }
    }

    }
    x
  }


}
