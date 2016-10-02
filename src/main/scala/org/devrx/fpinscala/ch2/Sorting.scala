package org.devrx.fpinscala.ch2

object Sorting {
  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    def loop(n: Int): Boolean = {
      if (n >= (as.length - 1)) true
      else ordered(as(n), as(n + 1)) && loop(n + 1)
    }

    loop(0)
  }
}
