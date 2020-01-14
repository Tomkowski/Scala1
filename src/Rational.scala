class Rational(num: Int, dem: Int) {
  require(dem > 0)
  val (value, numerator, denominator) = reduceDenominator(num, dem)
  val absoluteNumerator = (value * denominator + numerator)

  def +(other: Rational) = {
    val lcd = lcm(denominator, other.denominator)

    val num = absoluteNumerator * (lcd / denominator)
    val other_num = other.absoluteNumerator * (lcd / other.denominator)

    new Rational(num + other_num, lcd)

}

  def -(other: Rational) = {

    val lcd = lcm(denominator, other.denominator)

    val num = absoluteNumerator * (lcd / denominator)
    val other_num = other.absoluteNumerator * (lcd / other.denominator)

    new Rational(num - other_num, lcd)

  }

  def *(other: Rational) = {
    val num = absoluteNumerator * other.absoluteNumerator
    val den = this.denominator * other.denominator

    new Rational(num, den)

  }

  def /(other: Rational) = {
    this * new Rational(other.denominator, other.absoluteNumerator)
  }

  override def toString: String = s"$value $numerator/$denominator"

  private def reduceDenominator(numerator: Int, denominator: Int): (Int, Int, Int) = {
    val value = numerator / denominator

    val tmp = numerator - (value * denominator)

    if(tmp == 0) return (value, tmp, denominator)

    val num = tmp / gcd(tmp, denominator)

    val den = denominator / gcd(tmp,denominator)
    (value, num, Math.abs(den))
  }

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a%b)
  }
  private def lcm(a: Int, b: Int): Int = {
    (a * b) / gcd(a, b)
  }
}

object Rational{
  val ZERO = new Rational(0,1)
  val ONE = new Rational(1,1)
  def apply(nominator: Int) = new Rational(nominator, 1)
}

object Main{
  def main(args: Array[String]): Unit = {
    val a = new Rational(8,16)
    val b = new Rational(1, 2)
    val c = new Rational(15,7)
    val d = new Rational(8,3)
    val e = Rational(1)

    println(s"1 + 1 = ${Rational.ONE + Rational.ONE}")
    //println(s"1 / 0 = ${RationalFactory.ONE() / RationalFactory.ZERO()}")
    println(s"(8/16) / (1 / 2) = ${a / b}")
    println(s"(8 / 3) - (15/7) = ${d - c}")
    println(s"(8 / 3) * (1/2) = ${d * b}")


    val figureList: List[Figure] = List(new Rectangle(), new Square(), new Triangle())

    println(FigureManager.areaSum(figureList))
    FigureManager.printAll(figureList)
  }
}
