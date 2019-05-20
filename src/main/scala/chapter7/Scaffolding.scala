package chapter7

trait Scaffolding {


  def mapUsingFoldRight[A, B](l: List[A])(f: A => B): List[B] =
    l.foldRight(List.empty[B])((a, acc) => f(a) :: acc)

  def flatMapUsingFoldRight[A, B](l: List[A])(f: A => List[B]): List[B] =
    l.foldRight(List[B]())((a, acc) => f(a) ++ acc)

  def filterUsingFoldRight[A](l: List[A])(f: A => Boolean): List[A] =
    l.foldRight(List.empty[A])((a, acc) => if (f(a)) a :: acc else acc)


}


object Scaffolding extends Scaffolding