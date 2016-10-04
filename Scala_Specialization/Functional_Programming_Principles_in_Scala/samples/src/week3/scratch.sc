//package week3

import week3.{Rational, hello}

object scratch {
  new Rational(1, 2)                              //> res0: week3.Rational = 1/2
  def a = None                                    //> a: => None.type
  println(a)                                      //> None
  
  def error(msg: String) = throw new Error(msg)   //> error: (msg: String)Nothing
  
  val x = null                                    //> x  : Null = null
  val y: String = null                            //> y  : String = null
  val z: Int = 1                                  //> z  : Int = 1
  
  if (true) 1 else false                          //> res1: AnyVal = 1
  
  def ss = Array(1, 2, "S")                       //> ss: => Array[Any]
}