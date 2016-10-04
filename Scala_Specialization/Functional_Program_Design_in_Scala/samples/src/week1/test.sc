package week1

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val f: String => String = {
  	case "ping" => "pong"
  	case _ => "other" }                       //> f  : String => String = <function1>
  
  f("ping")                                       //> res0: String = pong
  f("abc")                                        //> res1: String = other
  
  def g: PartialFunction[String, String] = { case "ping" => "pong"}
                                                  //> g: => PartialFunction[String,String]
  
  g.isDefinedAt("ping")                           //> res2: Boolean = true
  g.isDefinedAt("pong")                           //> res3: Boolean = false
  
  val n = 4                                       //> n  : Int = 4
  for {
  	x <- 2 to n
  	y <- 2 to x
  	if (x % y == 0)
  } yield (x, y)                                  //> res4: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,2), (3,3
                                                  //| ), (4,2), (4,4))
 
  (2 to n) flatMap (x =>
  	(2 to x) withFilter (y =>
  		x % y == 0) map (y => (x, y)))    //> res5: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,2), (3,3
                                                  //| ), (4,2), (4,4))
	(2 to n) flatMap (x => (2 to x))          //> res6: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 2, 3, 2, 3, 4)
	
	(2 to n) map (x => (2 to x))              //> res7: scala.collection.immutable.IndexedSeq[scala.collection.immutable.Range
                                                  //| .Inclusive] = Vector(Range(2), Range(2, 3), Range(2, 3, 4))
}