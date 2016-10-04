object week5 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(64); 

  var fruit = List("apples", "oranges", "pears");System.out.println("""fruit  : List[String] = """ + $show(fruit ));$skip(27); 
  var nums = List(1, 2, 3);System.out.println("""nums  : List[Int] = """ + $show(nums ));$skip(64); 
  var diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1));System.out.println("""diag3  : List[List[Int]] = """ + $show(diag3 ));$skip(21); 
  var empty = List();System.out.println("""empty  : List[Nothing] = """ + $show(empty ));$skip(75); 
  
  def removeAt(n: Int, xs: List[Int]) = (xs take n) ::: (xs drop n + 1);System.out.println("""removeAt: (n: Int, xs: List[Int])List[Int]""");$skip(23); val res$0 = 
  
  removeAt(1, nums);System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(34); 
  
  
  val pair = ("answer", 42);System.out.println("""pair  : (String, Int) = """ + $show(pair ));$skip(28); 
  val (label, value) = pair;System.out.println("""label  : String = """ + $show(label ));System.out.println("""value  : Int = """ + $show(value ))}
                                                  
                                              
}
