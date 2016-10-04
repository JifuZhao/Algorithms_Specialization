
object intsets {
  val t1 = new NonEmpty(2, new Empty, new Empty)  //> t1  : NonEmpty = {.2.}
  val t2 = new NonEmpty(3, new Empty, new Empty)  //> t2  : NonEmpty = {.3.}
  val t3 = t1 incl 4                              //> t3  : IntSet = {.2{.4.}}
  val t4 = t2 incl 5                              //> t4  : IntSet = {.3{.5.}}
  val result1 = t1 contains  4                    //> result1  : Boolean = false
  val result2 = t2.contains(4)                    //> result2  : Boolean = false
  t3 union t4                                     //> res0: IntSet = {{.2.}3{{.4.}5.}}
  t1 union t2                                     //> res1: IntSet = {{.2.}3.}
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  def union(other: IntSet): IntSet = other
  
  override def toString = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
    
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
    
  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
  
  override def toString = "{" + left + elem + right + "}"
}