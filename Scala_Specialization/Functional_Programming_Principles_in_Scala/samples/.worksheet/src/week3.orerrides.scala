package week3

object orerrides {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(52); 
  val b1 = new Sub;System.out.println("""b1  : week3.Sub = """ + $show(b1 ));$skip(9); val res$0 = 
  b1.bar;System.out.println("""res0: Int = """ + $show(res$0))}
}

abstract class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
  override def foo = 2
  def bar = 3
}
