
object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(47); 
  
  val xs = Array(1, 2, 3, 44);System.out.println("""xs  : Array[Int] = """ + $show(xs ));$skip(22); val res$0 = 
  xs map (x => 2 * x);System.out.println("""res0: Array[Int] = """ + $show(res$0));$skip(27); 
  
  val s = "Hello World";System.out.println("""s  : String = """ + $show(s ));$skip(28); val res$1 = 
  s filter (c => c.isUpper);System.out.println("""res1: String = """ + $show(res$1));$skip(30); 
  
  val r: Range = 1 until 5;System.out.println("""r  : Range = """ + $show(r ));$skip(24); 
  val t: Range = 1 to 5;System.out.println("""t  : Range = """ + $show(t ));$skip(15); val res$2 = 
  1 to 10 by 3;System.out.println("""res2: scala.collection.immutable.Range = """ + $show(res$2));$skip(15); val res$3 = 
  6 to 1 by -2;System.out.println("""res3: scala.collection.immutable.Range = """ + $show(res$3));$skip(29); val res$4 = 
  
  xs exists (x => x == 1);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(28); val res$5 = 
  s exists (c => c.isUpper);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(28); val res$6 = 
  s forall (c => c.isUpper);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(43); 
  
  val pairs = List(1, 2, 3, 4, 5) zip s;System.out.println("""pairs  : List[(Int, Char)] = """ + $show(pairs ));$skip(14); val res$7 = 
  pairs unzip;System.out.println("""res7: (List[Int], List[Char]) = """ + $show(res$7));$skip(33); val res$8 = 

  s flatMap (c => List('.', c));System.out.println("""res8: String = """ + $show(res$8));$skip(12); val res$9 = 
  
  xs.sum;System.out.println("""res9: Int = """ + $show(res$9));$skip(13); val res$10 = 
  xs.product;System.out.println("""res10: Int = """ + $show(res$10));$skip(9); val res$11 = 
  xs.max;System.out.println("""res11: Int = """ + $show(res$11));$skip(9); val res$12 = 
  xs.min;System.out.println("""res12: Int = """ + $show(res$12))}
}
