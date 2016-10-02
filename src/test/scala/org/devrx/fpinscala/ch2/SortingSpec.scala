package org.devrx.fpinscala.ch2

import org.scalatest.{Matchers, WordSpec}

class SortingSpec extends WordSpec with Matchers {
  "isSorted" should {
    "return true when array is sorted" in {
      Sorting.isSorted(Array(1, 2, 3, 4), (x: Int, y: Int) => x < y) shouldBe true
    }

    "return false when array is not sorted" in {
      Sorting.isSorted(Array(1, 2, 5, 4), (x: Int, y: Int) => x < y) shouldBe false
    }
  }
}