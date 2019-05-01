package chapter5

import cats.data.OptionT
import cats.instances.list._ // for Monad


class MonadTransformer {

  type ListOption[A] = OptionT[List, A]

  def compose(result1: ListOption[Int], result2: ListOption[Int]) = result1.flatMap {
    (x: Int) =>
      result2.map {
        (y: Int) => x + y
      }
  }
}
