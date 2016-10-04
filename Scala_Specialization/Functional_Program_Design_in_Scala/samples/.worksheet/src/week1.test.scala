package week1

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(72); 
  println("Welcome to the Scala worksheet");$skip(81); 
  
  val f: String => String = {
  	case "ping" => "pong"
  	case _ => "other" };System.out.println("""f  : String => String = """ + $show(f ));$skip(15); val res$0 = 
  
  f("ping");System.out.println("""res0: String = """ + $show(res$0));$skip(11); val res$1 = 
  f("abc");System.out.println("""res1: String = """ + $show(res$1));$skip(71); 
  
  def g: PartialFunction[String, String] = { case "ping" => "pong"};System.out.println("""g: => PartialFunction[String,String]""");$skip(27); val res$2 = 
  
  g.isDefinedAt("ping");System.out.println("""res2: Boolean = """ + $show(res$2));$skip(24); val res$3 = 
  g.isDefinedAt("pong");System.out.println("""res3: Boolean = """ + $show(res$3));$skip(15); 
  
  val n = 4;System.out.println("""n  : Int = """ + $show(n ));$skip(74); val res$4 = 
  for {
  	x <- 2 to n
  	y <- 2 to x
  	if (x % y == 0)
  } yield (x, y);System.out.println("""res4: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$4));$skip(91); val res$5 = 
 
  (2 to n) flatMap (x =>
  	(2 to x) withFilter (y =>
  		x % y == 0) map (y => (x, y)));System.out.println("""res5: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$5));$skip(34); val res$6 = 
	(2 to n) flatMap (x => (2 to x));System.out.println("""res6: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$6));$skip(32); val res$7 = 
	
	(2 to n) map (x => (2 to x));System.out.println("""res7: scala.collection.immutable.IndexedSeq[scala.collection.immutable.Range.Inclusive] = """ + $show(res$7))}
}
