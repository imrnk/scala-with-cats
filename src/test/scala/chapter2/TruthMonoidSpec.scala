package chapter2

import org.scalatest.{FlatSpec, Matchers}

class TruthMonoidSpec extends FlatSpec with Matchers{


  it should "Obey the laws for Boolean And" in {
    booleanLaws(BooleanMonoidInstance.andMonoid)
  }

  it should "Obey the laws for Boolean Or" in {
    booleanLaws(BooleanMonoidInstance.orMonoid)
  }

  it should "Obey the laws for Boolean Xor" in {
    booleanLaws(BooleanMonoidInstance.xorMonoid)
  }

  it should "Obey the laws for Boolean Xnor" in {
    booleanLaws(BooleanMonoidInstance.xnorMonoid)
  }


  def booleanLaws(implicit monoid: Monoid[Boolean]): Unit = {
    Monoid.associativeLaw(true, false, true) should be (true)
    Monoid.associativeLaw(false, true, false) should be (true)
    Monoid.identityLaw(true)
    Monoid.identityLaw(false)
  }
}
