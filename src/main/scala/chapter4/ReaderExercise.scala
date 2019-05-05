package chapter4

import cats.data.Reader

case class Db(usernames: Map[Int, String], passwords: Map[String, String])

class ReaderExercise {

  import cats.syntax.applicative._ // for pure

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = {
    Reader(Db => Db.usernames.get(userId))
  }

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(Db => Db.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      userNameOpt <- findUsername(userId)
      passwordOk <- userNameOpt.map {
        userName => checkPassword(userName, password)
      }.getOrElse {
        false.pure[DbReader]
      }
    } yield passwordOk

}