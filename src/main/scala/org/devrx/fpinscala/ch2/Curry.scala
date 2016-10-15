package org.devrx.fpinscala.ch2

object Curry {
  def partial1[A,B,C](a: A, f: (A,B) => C): B => C =
    (b: B) => f(a,b)

  def curry[A,B,C](f: (A, B) => C): A => (B => C) =
//    (a: A) => partial1(a, f)
    a => b => f(a, b)

  def uncurry[A,B,C](f: A => B => C): (A, B) => C =
    (a: A, b: B) => f(a)(b)
}
