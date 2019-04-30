package chapter1

trait Printable[A] {
  def format(value : A) : String
}

object PrintableInstance {
  implicit val intPrintable = new Printable[Int] {
    override def format(i: Int): String = i.toString

  }

  implicit val stringPrintable = new Printable[String] {
    override def format(str: String): String = str
  }

  implicit  val personPrintable = new Printable[Person] {
    override def format(person: Person): String = {
      val name = Printable.format(person.name)
      val age = Printable.format(person.age)
      s"$name is a $age years old person"
    }
  }
 }

case class Person(name : String, age : Int)
object PrintableSyntax {
  implicit class PrintableOps[A](value : A) {
    def format(value:A)(implicit p : Printable[A]): Unit ={
      p.format(value)
    }

    def print(implicit p: Printable[A]) : Unit = println(p.format(value))
  }

}

object Printable {
  def format[A](value : A)(implicit p : Printable[A]) : String = p.format(value)
  def print[A](value: A)(implicit  p : Printable[A]) : Unit = println(format(value))
}

