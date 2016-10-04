package forcomp

object temp {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]


  type Occurrences = List[(Char, Int)];import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(432); 

 
  def unOccur(sub_occurrences: Occurrences): Occurrences = {
      (for( i <- 1 to sub_occurrences.head._2) yield
        List(List(sub_occurrences.head._1, i), (sub_occurrences.head._1, i)::unOccur(sub_occurrences.tail))
  };System.out.println("""unOccur: (sub_occurrences: forcomp.temp.Occurrences)forcomp.temp.Occurrences""");$skip(53); 
    
  val list = List(('a', 1), ('b', 2), ('c', 3));System.out.println("""list  : List[(Char, Int)] = """ + $show(list ));$skip(19); val res$0 = 
  
  unOccur(list);System.out.println("""res0: forcomp.temp.Occurrences = """ + $show(res$0))}
  
}
