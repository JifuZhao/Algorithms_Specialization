
object orerrides {
  val b1 = new Sub                                //> b1  : Sub = Sub@61e4705b
  b1.bar                                          //> res0: Int = 3
}

abstract class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
  override def foo = 2
  def bar = 3
}