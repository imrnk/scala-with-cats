package chapter3

import cats.Functor
import cats.instances.option._
import org.scalatest.{FlatSpec, Matchers}

class CatsFunctorSpec extends FlatSpec with Matchers {

  it should "obey the law of identity for a list of int" in {
    CatsFunctor.identity(List(1, 2, 3)) should be(true)
  }

  it should "obey the law of composition for a list of int and two functions Int => Int and Int => String" in {
    CatsFunctor.composition(List(1, 2, 3))(x => x * 2)(x => x + "") should be(true)
  }

  it should "lift a func from A => B to Option[A] => Option[B]" in {
    val onePlus = (x: Int) => x + 1

    val onePlusOpt = Functor[Option].lift(onePlus)

    onePlusOpt(Option(21)) should be(Some(22))


  }
}
