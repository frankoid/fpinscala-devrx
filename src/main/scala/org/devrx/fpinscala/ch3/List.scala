package org.devrx.fpinscala.ch3

import scala.annotation.tailrec

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def append2[A](a1: List[A], a2: List[A]): List[A] =
    foldRight(a1, a2)((h, t) => Cons(h, t))

  def flatten[A](ls: List[List[A]]): List[A] =
    foldRight(ls, Nil: List[A])(append2)

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  private class ZeroException extends Exception

  def product3(ns: List[Double]) =
    try {
      foldRight(ns, 1.0)((x, y) =>
        if (x == 0.0) throw new ZeroException
        else x * y)
    } catch {
      case _: ZeroException => 0.0
    }

  def tail[A](l: List[A]): List[A] = l match {
    case Nil => throw new UnsupportedOperationException("Can't take the tail of an empty list")
    case Cons(x, xs) => xs
  }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil => throw new UnsupportedOperationException("Can't set the head of an empty list")
    case Cons(x, xs) => Cons(h, xs)
  }

  def drop[A](l: List[A], n: Int): List[A] = (l, n) match {
    case (_, n) if (n < 0) => throw new IllegalArgumentException("n cannot be negative")
    case (l, 0) => l
    case (Cons(x, xs), n) => drop(xs, n - 1)
    case _ => throw new UnsupportedOperationException("Can't drop more elements than the list contains")
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(x, xs) => if (f(x)) dropWhile(xs, f) else l
    case Nil => Nil
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => throw new UnsupportedOperationException
    case Cons(x, Nil) => Nil
    case Cons(x, xs) => Cons(x, init(xs))
  }

  def length[A](l: List[A]): Int =
    foldRight(l, 0)((_, y) => y + 1)

  @tailrec
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  def reverse[A](as: List[A]): List[A] =
    foldLeft[A,List[A]](as, Nil)((as, a) => Cons(a, as))

  def foldLeft2[A,B](as: List[A], z: B)(f: (B, A) => B): B =
    foldLeft(as, z)(f) //TODO implement in terms of foldRight
    //foldRight(as, z)((a: A, b: B) => f(b, a))

  def reverse2[A](as: List[A]): List[A] =
    foldLeft2[A,List[A]](as, Nil)((as, a) => Cons(a, as))

  def map[A,B](l: List[A])(f: A => B): List[B] = sys.error("todo")
}
