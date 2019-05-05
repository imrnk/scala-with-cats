package chapter4

import org.scalatest.{FlatSpec, Matchers}

class ReaderExerciseSpec extends FlatSpec with Matchers {

  it should "be able to check login for a list of users" in {

    val users = Map(
      1 -> "dade",
      2 -> "kate",
      3 -> "margo"
    )

    val passwords = Map(
      "dade" -> "zerocool",
      "kate" -> "acidburn",
      "margo" -> "secret"
    )

    val db = Db(users, passwords)
    val re = new ReaderExercise
    re.checkLogin(1, "zerocool").run(db) should be(true)
    re.checkLogin(2, "secret").run(db) should be(false)
    re.checkLogin(4, "davinci").run(db) should be(false)
  }
}
