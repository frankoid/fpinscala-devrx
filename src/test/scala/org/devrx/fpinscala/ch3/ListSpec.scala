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
}
