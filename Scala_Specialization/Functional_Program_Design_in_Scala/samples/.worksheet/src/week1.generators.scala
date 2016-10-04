package week1

object generators {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(78); 
  println("Welcome to the Scala worksheet");$skip(91); 
  
  val integers = new Generator[Int] {
  	def generate = scala.util.Random.nextInt()
  };System.out.println("""integers  : AnyRef{def generate: Int} = """ + $show(integers ));$skip(20); 
 	
 	val booleans =;System.out.println("""booleans  : Null = """ + $show(booleans ))}
 
  
  
  
}
