
package eitherxample

sealed trait DomainFailure

case class AggregationFailure(cobDate : String, reportingSetId : Int) extends  DomainFailure
case object DeallocationFailure extends DomainFailure
case class Input(cobDate :String, reportingSetId: Int, value : Double)

class RecoverWithEither {

  def deAllocationProcess() {}

  def tsAggregationProcess(inputs: List[Input]) = {

    TSAggregation.process(inputs)
  }

  def tsAggregationProcess2(inputs: List[Input]) = {

    //TSAggregation.process2(inputs)
  }

}

object RecoverWithEither  {
  def main(args : Array[String]): Unit = {
    val d = new RecoverWithEither
    d.deAllocationProcess
  }
}

