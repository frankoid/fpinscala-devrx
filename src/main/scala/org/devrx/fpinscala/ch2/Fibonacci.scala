package org.devrx.fpinscala.ch2

object Fibonacci {
  def fib(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, prev: Int, acc: Int): Int = {
      if (n <= 1) acc
      else go(n - 1, acc, acc + prev)
    }

    go(n, 1, 0)
  }
}
