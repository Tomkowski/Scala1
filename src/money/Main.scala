package money

import money.Currencies.{EUR => `€`, USD => $, PLN => zl}
import money.Currencies._


object Main extends App {



  trait Currency {
    val name: String
  }

  implicit class DoubleOps(val value: Double) {
    def apply(currency: Currency)(implicit converter: Converter): Money = Money(value, currency)
  }

  val conversion: Map[(Currency, Currency), BigDecimal] = Map(
    ((PLN, USD), 0.26),
    ((PLN, EUR), 0.23),
    ((USD, PLN), 3.86),
    ((USD, EUR), 0.9),
    ((EUR, PLN), 4.28),
    ((EUR, USD), 1.11)
  )

  case class Converter(conversion: Map[(Currency, Currency), BigDecimal]) {

    def conversionRate(from: Currency, to: Currency): BigDecimal = {
      conversion.get((from, to)) match {
        case Some(key) => key
        case _ => 1
      }
    }

    def convert(money: Money, toCurrency: Currency)(implicit converter: Converter): Money = {
      val rate = conversionRate(money.currency, toCurrency)

      Money(money.value * rate, toCurrency)
    }
  }


  implicit val converter: Converter = Converter(conversion)

  val sum1: Money = 100.01 (`€`) + 200 (EUR)

  val sum2: Money = 100.01 (zl) + 200 ($)

  val sum3: Money = 5 (zl) + 3 (PLN) + 20.5 (USD)

  val sub: Money = 300.01 (USD) - 200 (EUR)

  val mult1: Money = 30 (zl) * 20
  val mult2: Money = 20 ($) * 11

  val conv1: Money = 150.01 (USD) as PLN
  val conv2: Money = 120.01 (USD) as `€`

  val compare1: Boolean = 300.30 (USD) > 200 (`€`)
  val compare2: Boolean = 300.30 ($) < 200 (EUR)

  printResults()


  def printResults(): Unit = {
    println("Sum")
    println(sum1, sum2, sum3)
    println("-" * 50)
    println("Sub")
    println(sub)
    println("-" * 50)
    println("Multiplication")
    println(mult1, mult2)
    println("-" * 50)
    println("Conversion")
    println(conv1, conv2)
    println("-" * 50)
    println("Comparison")
    println(compare1, compare2)
    println("-" * 50)

  }
}

