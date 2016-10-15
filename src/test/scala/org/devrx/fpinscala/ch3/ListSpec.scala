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
}
