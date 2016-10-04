
object listfun {
  val nums = List(2, 2, -1, 5, 7, 1)              //> nums  : List[Int] = List(2, 2, -1, 5, 7, 1)
  val fruits = List("apple", "pineapple", "orange", "banana")
                                                  //> fruits  : List[String] = List(apple, pineapple, orange, banana)
  
  nums filter (x => x > 0)                        //> res0: List[Int] = List(2, 2, 5, 7, 1)
  nums filterNot(x => x > 0)                      //> res1: List[Int] = List(-1)
  nums partition (x => x > 0)                     //> res2: (List[Int], List[Int]) = (List(2, 2, 5, 7, 1),List(-1))
  
  nums takeWhile (x => x > 0)                     //> res3: List[Int] = List(2, 2)
  nums dropWhile (x => x > 0)                     //> res4: List[Int] = List(-1, 5, 7, 1)
  nums span (x => x > 0)                          //> res5: (List[Int], List[Int]) = (List(2, 2),List(-1, 5, 7, 1))
  
  val data = List("a", "a", "a", "b", "c", "c", "a")
                                                  //> data  : List[String] = List(a, a, a, b, c, c, a)
  
  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  }                                               //> pack: [T](xs: List[T])List[List[T]]
  
  pack(data)                                      //> res6: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a))
                                                  //| 
  
  
  def encode[T](xs: List[T]): List[(T, Int)] =
    pack(xs) map (ys => (ys.head, ys.length))     //> encode: [T](xs: List[T])List[(T, Int)]
    
  encode(data)                                    //> res7: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))
  
  def concat[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys) (_ :: _)                    //> concat: [T](xs: List[T], ys: List[T])List[T]
    
  val a = List(1, 2, 3)                           //> a  : List[Int] = List(1, 2, 3)
  val b = List(4, 5, 6)                           //> b  : List[Int] = List(4, 5, 6)
  concat(a, b)                                    //> res8: List[Int] = List(1, 2, 3, 4, 5, 6)
  
  
  
  
}