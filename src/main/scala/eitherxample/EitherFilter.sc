
val er: Either[String, Int] = Left("wrong")
val el: Either[String, Int] = Right(2)

for {r <- er} yield r * 2
for {l <- el} yield l * 2

el.filterOrElse(_ > 2, "low")

val fil = er match {
  case Right(n) if n > 2 => Right(n * 2)
  case _ => Left("")
}

val list = List(3, 4, 5, 4)

val uniquePairs = for {
  (x, idxX) <- list.zipWithIndex
  (y, idxY) <- list.zipWithIndex
  if idxY - idxX > 1
} yield (x, y)

uniquePairs.maxBy(tup => tup._1 + tup._2)

List(1, 2, 3).foldLeft(0) { (z, e) => z + e }
List(1, 2, 3).foldRight(0) { (e, z) => z + e }

List(1, 2, 3).foldLeft(0) { (z, e) => z - e }
List(1, 2, 3).foldRight(0) { (e, z) => e - z }

List(1, 2, 3).foldLeft(Nil: List[Int])((z, e) => e :: z)
List(1, 2, 3).foldRight(Nil: List[Int])((e, z) => e :: z)