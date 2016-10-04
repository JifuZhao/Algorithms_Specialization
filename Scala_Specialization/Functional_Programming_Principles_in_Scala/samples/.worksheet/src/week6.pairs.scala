package week6

object pairs {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(42); 

  val n = 7;System.out.println("""n  : Int = """ + $show(n ));$skip(73); val res$0 = 
  
  ((1 until n) map (i =>
     (1 until i) map (j => (i, j)))).flatten;System.out.println("""res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$0));$skip(63); val res$1 = 
  (1 until n) flatMap (i =>
    (1 until i) map (j => (i, j)));System.out.println("""res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$1));$skip(56); 
  def isPrime(n: Int) = (2 until n) forall (n % _ != 0);System.out.println("""isPrime: (n: Int)Boolean""");$skip(117); val res$2 = 
   
  (1 until n) flatMap (i =>
    (1 until i) map (j => (i, j))) filter (pair =>
      isPrime(pair._1 + pair._2));System.out.println("""res2: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$2));$skip(87); val res$3 = 
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
    } yield (i, j);System.out.println("""res3: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$3))}
}
