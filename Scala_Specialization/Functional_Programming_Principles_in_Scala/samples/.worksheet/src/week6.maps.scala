package week6

object maps {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(85); 
  val romanNumerals = Map('I' -> 1, 'V' -> 5, 'X' -> 10);System.out.println("""romanNumerals  : scala.collection.immutable.Map[Char,Int] = """ + $show(romanNumerals ));$skip(76); 
  val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern");System.out.println("""capitalOfCountry  : scala.collection.immutable.Map[String,String] = """ + $show(capitalOfCountry ));$skip(21); val res$0 = 
  romanNumerals('I');System.out.println("""res0: Int = """ + $show(res$0));$skip(25); val res$1 = 
  capitalOfCountry("US");System.out.println("""res1: String = """ + $show(res$1));$skip(31); val res$2 = 
  
  capitalOfCountry get "US";System.out.println("""res2: Option[String] = """ + $show(res$2));$skip(31); val res$3 = 
  capitalOfCountry get "USSSS";System.out.println("""res3: Option[String] = """ + $show(res$3));$skip(148); 
  
  def showCapital(country: String) = capitalOfCountry.get(country) match {
    case Some(capital) => capital
    case None => "missing data"
  };System.out.println("""showCapital: (country: String)String""");$skip(23); val res$4 = 
  
  showCapital("US");System.out.println("""res4: String = """ + $show(res$4));$skip(25); val res$5 = 
  showCapital("SDSFDSF");System.out.println("""res5: String = """ + $show(res$5));$skip(52); 
  
  val freqs = List(('c', 4), ('a', 2), ('d', 3));System.out.println("""freqs  : List[(Char, Int)] = """ + $show(freqs ));$skip(30); val res$6 = 
  freqs.sortWith(_._2 < _._2);System.out.println("""res6: List[(Char, Int)] = """ + $show(res$6));$skip(40); val res$7 = 
  freqs.sortWith((a, b) => a._2 < b._2);System.out.println("""res7: List[(Char, Int)] = """ + $show(res$7));$skip(15); val res$8 = 
  freqs.sorted;System.out.println("""res8: List[(Char, Int)] = """ + $show(res$8))}
 
}
