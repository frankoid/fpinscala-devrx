package org.devrx.fpinscala.ch3

import org.scalatest.{Matchers, WordSpec}

class ListSpec extends WordSpec with Matchers {
  "tail" should {
    "return the tail of the list for a non-empty list" in {
      List.tail(List(1,2,3)) shouldBe List(2,3)
    }
    "throw an exception for an empty list" in {
      intercept[UnsupportedOperationException] {
        List.tail(List())
      }
    }
  }

  "setHead" should {
    "set the first element of the list for a non-empty list" in {
      List.setHead(List(1,2,3), 10) shouldBe List(10,2,3)
    }
    "throw an exception for an empty list" in {
      intercept[UnsupportedOperationException] {
        List.setHead(List(), 10)
      }
    }
  }

  "drop" should {
    "drop first n elements" in {
      List.drop(List(1,2,3), 2) shouldBe List(3)
      List.drop(List(1,2,3), 3) shouldBe List()
    }
    "throw an exception when there aren't enough elements" in {
      intercept[UnsupportedOperationException] {
        List.drop(List(1, 2), 3)
      }
    }
    "throw an exception when n is negative" in {
      intercept[IllegalArgumentException] {
        List.drop(List(1, 2), -1)
      }
    }
  }

  "dropWhile" should {
    "drop elements while they match f" in {
      List.dropWhile[Int](List(9,8,7,6,7,8), _ > 6) shouldBe List(6,7,8)
    }

    "return Nil when passed Nil" in {
      List.dropWhile[Int](Nil, _ > 6) shouldBe Nil
    }

    "return Nil when passed a predicate that matches all elements" in {
      List.dropWhile[Int](List(1, 2, 3), _ < 10) shouldBe Nil
    }
  }

  "init" should {
    "return all but the last element of a List" in {
      List.init(List(1,2,3,4)) shouldBe List(1,2,3)
    }

    "throw UnsupportedOperationException for an empty list" in {
      intercept[UnsupportedOperationException] {
        List.init(List())
      }
    }
  }

  "length" should {
    "return the length of the List" in {
      List.length(List(1,2,3)) shouldBe 3
    }
  }

  "foldLeft" should {
    "be usable to implement product" in {
      def productL(ns: List[Double]) = List.foldLeft(ns, 1.0)(_ * _)

      productL(List(2.0, 4.0, 3.0)) shouldBe 24.0
    }

    "be usable to implement sum" in {
      def sumL(ns: List[Int]) =
        List.foldLeft(ns, 0)((x,y) => x + y)

      sumL(List(3,4,5)) shouldBe 12
    }

    "be usable to implement length" in {
      def lengthL[A](l: List[A]): Int =
        List.foldLeft(l, 0)((acc, _) => acc + 1)

      lengthL(List(3,4,5,6)) shouldBe 4
    }
  }
}
