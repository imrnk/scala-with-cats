package chapter1

object Main {

  def main(args: Array[String]): Unit = {
    import PrintableInstance._
    import PrintableSyntax._

    1.print

    val p = Person("Andy", 23)
    p.print
  }
}
