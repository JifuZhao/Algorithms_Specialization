//package week3

import week3.{Rational, hello}

object scratch {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(86); val res$0 = 
  new Rational(1, 2);System.out.println("""res0: week3.Rational = """ + $show(res$0));$skip(15); 
  def a = None;System.out.println("""a: => None.type""");$skip(13); 
  println(a);$skip(51); 
  
  def error(msg: String) = throw new Error(msg);System.out.println("""error: (msg: String)Nothing""");$skip(18); 
  
  val x = null;System.out.println("""x  : Null = """ + $show(x ));$skip(23); 
  val y: String = null;System.out.println("""y  : String = """ + $show(y ));$skip(17); 
  val z: Int = 1;System.out.println("""z  : Int = """ + $show(z ));$skip(28); val res$1 = 
  
  if (true) 1 else false;System.out.println("""res1: AnyVal = """ + $show(res$1));$skip(31); 
  
  def ss = Array(1, 2, "S");System.out.println("""ss: => Array[Any]""")}
}
