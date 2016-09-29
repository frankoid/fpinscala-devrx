package org.devrx.fpinscala.ch2

import org.scalatest.{Matchers, WordSpec}

class FibonacciSpec extends WordSpec with Matchers {
  "fib" should {
    "return 0 as the first number in the series" in {
      Fibonacci.fib(1) shouldBe 0
    }

    "return 1 as the second number in the series" in {
      Fibonacci.fib(2) shouldBe 1
    }

    "return 1 as the 3rd number in the series" in {
      Fibonacci.fib(3) shouldBe 1
    }

    "return further numbers in the series" in {
      Fibonacci.fib(4) shouldBe 2
      Fibonacci.fib(5) shouldBe 3
      Fibonacci.fib(6) shouldBe 5
      Fibonacci.fib(7) shouldBe 8
      Fibonacci.fib(8) shouldBe 13
      Fibonacci.fib(9) shouldBe 21
    }
  }
}
