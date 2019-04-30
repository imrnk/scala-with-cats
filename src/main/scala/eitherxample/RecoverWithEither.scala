/*
package eitherxample

sealed trait DomainFailure

case class AggregationFailure(cobDate : String, reportingSetId : Int) extends  DomainFailure
case object DeallocationFailure extends DomainFailure
case class Input(cobDate :String, reportingSetId: Int, value : Double)


class RecoverWithEither {

  def deAllocationProcess = {
   val values =  for {
      le <- tsAggregationProcess
      tup <- le.right

    } yield tup._2

    DeAllocation.process(values)
  }

  def tsAggregationProcess = {
    val inputs = List(Input("31122018", 4, 124.24),
      Input("31122018",2, 120.20), Input("31122018",1, 56.35))

    TSAggregation.process(inputs)
  }

}

object RecoverWithEither  {
  def main(args : Array[String]): Unit = {
    val d = new RecoverWithEither
    d.deAllocationProcess
  }
}
*/
