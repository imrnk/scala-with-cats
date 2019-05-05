package chapter4

import cats.Id
import cats.data.{Kleisli, Reader}

case class Cat(name: String, favoriteFood: String)

class ReaderExamples {

  val catName: Reader[Cat, String] = Reader(cat => cat.name)
  val greetKitty: Kleisli[Id, Cat, String] = catName.map(name => s"Hello ${name}")
  val feedKitty: Reader[Cat, String] = Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")

  val greetAndFeed = for {
    greet <- greetKitty
    feed <- feedKitty
  } yield s"$greet, $feed"
}

object ReaderExamples {
  def main(args: Array[String]): Unit = {
    val re = new ReaderExamples
    //val x: Id[String] = re.catName.run(Cat("Garfield", "lasagne"))
    print(re.greetAndFeed.run(Cat("Garfield", "lasagne")))

  }

}