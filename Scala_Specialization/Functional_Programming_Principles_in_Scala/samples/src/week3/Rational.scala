package week3

object Rational {

  val x = new Rational(1, 3)                      //> x  : week2.Rational = 1/3
  val y = new Rational(5, 7)                      //> y  : week2.Rational = 5/7
  val z = new Rational(3, 2)                      //> z  : week2.Rational = 3/2
  
  x.numer                                         //> res0: Int = 1
  x.denom                                         //> res1: Int = 3
  y + y                                           //> res2: week2.Rational = 10/7
  x - y - z                                       //> res3: week2.Rational = -79/42
  x < y                                           //> res4: Boolean = true
  x max y                                         //> res5: week2.Rational = 5/7
  
  new Rational(2)                                 //> res6: week2.Rational = 2/1
  new Rational(4, 5)                              //> res7: week2.Rational = 4/5
  
  new Rational(1045540, 5232500)                  //> res8: week2.Rational = 52277/261625
  
}

class Rational(x: Int, y: Int) {

  require(y != 0, "denominator must be nonzero")
  
  def this(x: Int) = this(x, 1)
  
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)
  
  def numer = x
  def denom = y
  
  def +(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom, denom * that.denom)
      
  def unary_- : Rational = new Rational(-this.numer, this.denom)
  
  def -(that: Rational) = this + -that
  
  def <(that: Rational) = this.numer * that.denom < that.numer * this.denom
  
  def max(that: Rational) = if (this < that) that else this
  
      
  override def toString = numer / g + "/" + denom / g
}