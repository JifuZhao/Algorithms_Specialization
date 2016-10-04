
object test {
  
  val xs = Array(1, 2, 3, 44)                     //> xs  : Array[Int] = Array(1, 2, 3, 44)
  xs map (x => 2 * x)                             //> res0: Array[Int] = Array(2, 4, 6, 88)
  
  val s = "Hello World"                           //> s  : String = Hello World
  s filter (c => c.isUpper)                       //> res1: String = HW
  
  val r: Range = 1 until 5                        //> r  : Range = Range(1, 2, 3, 4)
  val t: Range = 1 to 5                           //> t  : Range = Range(1, 2, 3, 4, 5)
  1 to 10 by 3                                    //> res2: scala.collection.immutable.Range = Range(1, 4, 7, 10)
  6 to 1 by -2                                    //> res3: scala.collection.immutable.Range = Range(6, 4, 2)
  
  xs exists (x => x == 1)                         //> res4: Boolean = true
  s exists (c => c.isUpper)                       //> res5: Boolean = true
  s forall (c => c.isUpper)                       //> res6: Boolean = false
  
  val pairs = List(1, 2, 3, 4, 5) zip s           //> pairs  : List[(Int, Char)] = List((1,H), (2,e), (3,l), (4,l), (5,o))
  pairs unzip                                     //> res7: (List[Int], List[Char]) = (List(1, 2, 3, 4, 5),List(H, e, l, l, o))

  s flatMap (c => List('.', c))                   //> res8: String = .H.e.l.l.o. .W.o.r.l.d
  
  xs.sum                                          //> res9: Int = 50
  xs.product                                      //> res10: Int = 264
  xs.max                                          //> res11: Int = 44
  xs.min                                          //> res12: Int = 1
}