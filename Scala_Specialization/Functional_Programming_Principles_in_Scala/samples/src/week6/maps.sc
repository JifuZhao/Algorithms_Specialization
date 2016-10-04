package week6

object maps {
  val romanNumerals = Map('I' -> 1, 'V' -> 5, 'X' -> 10)
                                                  //> romanNumerals  : scala.collection.immutable.Map[Char,Int] = Map(I -> 1, V -> 
                                                  //| 5, X -> 10)
  val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")
                                                  //> capitalOfCountry  : scala.collection.immutable.Map[String,String] = Map(US -
                                                  //| > Washington, Switzerland -> Bern)
  romanNumerals('I')                              //> res0: Int = 1
  capitalOfCountry("US")                          //> res1: String = Washington
  
  capitalOfCountry get "US"                       //> res2: Option[String] = Some(Washington)
  capitalOfCountry get "USSSS"                    //> res3: Option[String] = None
  
  def showCapital(country: String) = capitalOfCountry.get(country) match {
    case Some(capital) => capital
    case None => "missing data"
  }                                               //> showCapital: (country: String)String
  
  showCapital("US")                               //> res4: String = Washington
  showCapital("SDSFDSF")                          //> res5: String = missing data
  
  val freqs = List(('c', 4), ('a', 2), ('d', 3))  //> freqs  : List[(Char, Int)] = List((c,4), (a,2), (d,3))
  freqs.sortWith(_._2 < _._2)                     //> res6: List[(Char, Int)] = List((a,2), (d,3), (c,4))
  freqs.sortWith((a, b) => a._2 < b._2)           //> res7: List[(Char, Int)] = List((a,2), (d,3), (c,4))
  freqs.sorted                                    //> res8: List[(Char, Int)] = List((a,2), (c,4), (d,3))
 
}