package week6

import scala.io.Source

object wordCoder {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(198); 
  /* read a file of words */
  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt");System.out.println("""in  : scala.io.BufferedSource = """ + $show(in ));$skip(193); 
  
  /* create a list and filter all words where *all* their characters are not letters (like dashes) */
  val words = in.getLines.toList filter
    (word => word forall (chr => chr.isLetter));System.out.println("""words  : List[String] = """ + $show(words ));$skip(180); 
  
  /* define the map of numbers to letters */
  val nmem = Map( '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL", '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ");System.out.println("""nmem  : scala.collection.immutable.Map[Char,String] = """ + $show(nmem ));$skip(157); 
  
  /* invert the map the get a map of letters to digits */
  val charCode: Map[Char, Char] = for
    ((digit, str) <- nmem; ltr <- str) yield ltr -> digit;System.out.println("""charCode  : Map[Char,Char] = """ + $show(charCode ));$skip(143); 
  
  /* define a function that returns the numbers of a given word */
  def wordCode(word: String): String =
    word.toUpperCase map charCode;System.out.println("""wordCode: (word: String)String""");$skip(24); val res$0 = 
    
  wordCode("Java");System.out.println("""res0: String = """ + $show(res$0));$skip(161); 
  
  /* group all words of our long list with the same number */
  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq();System.out.println("""wordsForNum  : Map[String,Seq[String]] = """ + $show(wordsForNum ));$skip(487); 
  
  /* function that receives a number and finds the words that match it */
  def encode(number: String): Set[List[String]] =
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length // iterate over the number
        word <- wordsForNum(number take split) // get the word before the spilt
        rest <- encode(number drop split) //get the words after the split
      } yield word :: rest // join the results
    }.toSet;System.out.println("""encode: (number: String)Set[List[String]]""");$skip(126);  // pass a set to the for
  
  /* better print of the results */
  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ");System.out.println("""translate: (number: String)Set[String]""");$skip(155); val res$1 = 

  /* test the translate and print results*/
  //translate("7225247386") foreach println
                                                  
  encode("72");System.out.println("""res1: Set[List[String]] = """ + $show(res$1))}

}
