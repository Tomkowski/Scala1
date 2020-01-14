/*
package list3

import java.util.Scanner

object Utils {

  def isSorted(as: List[Int], ordering: (Int, Int) => Boolean): Boolean = {
    if(as.isEmpty || as.length == 1) true


    else
      ordering(as(0),as(1)) && isSorted(as.drop(1),ordering)
  }

  def isAscSorted(as: List[Int]): Boolean = {
    isSorted(as,(a,b) => a <= b)
  }

  def isDescSorted(as: List[Int]): Boolean = {
    isSorted(as,(a,b) => a >= b)
  }


  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = {
    if(l.isEmpty) z

    else
    foldLeft(l.dropRight(1), f(z,l.last))(f)
  }


  def sum(l: List[Int]): Int  = {
    foldLeft(l,0)((a: Int, b: Int ) => a + b)
  }

  def length[A](l: List[A]): Int = {
    foldLeft(l,0)((z: Int, _: A) => z + 1)
  }

  def compose[A, B, C](f: (A) => B, g: (B) => C): (A) => C  = {
//    x => f(g(x))
    f.andThen(g)
  }

  def repeated[A, B](f: (A) => A, n: Int): (A) => A = {
    if (n < 1)
      f
    else
      compose(f, repeated(f, n - 1))
  }

  def curry[A, B, C](f: (A,B) => C): (A) => ((B) => C)  = {
    def cur(a: A) : (B) => C = {
      b: B => f(a,b)
    }
    cur
  }

  def uncurry[A, B, C](f: (A) => ((B) => C)): (A,B) => C   = {
    def uncur: (A,B) => C = {
      (a: A, b: B) => f(a)(b)
    }
    uncur
  }

  def unsafe[T](e : Exception,msg: String)(op: => T) = {
    try{
      op
    }
    catch{
      case e : Exception => println(msg) ;throw e
  }
  }

  def add(x: Int, y: Int) = x + y

  def main(args: Array[String]): Unit = {
    println(isAscSorted(List(1,3,3,4,5)))
    println(isDescSorted(List(1,3,3,4,5)))
    println(sum(List(1,2,3,4,-5)))
    println(repeated((a: Int) => a*a, 2)(2))
    println(uncurry(curry(add))(1,2))
    unsafe(NullPointerException, "failure"){
      println("Hello")
      println("World")
      val a: Scanner = null
      a.close()
  }
}
}
*/
