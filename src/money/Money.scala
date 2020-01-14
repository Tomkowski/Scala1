package money


import money.Main.{Converter, Currency}


case class Money(value: BigDecimal, currency: Currency)(implicit converter: Converter) {

  implicit class BigDecimalOps(val value: BigDecimal) {
    def apply(currency: Currency)(implicit converter: Converter): Money = Money(value, currency)
  }

  def as(toCurrency: Currency): Money = {
    val rate = converter.conversionRate(currency, toCurrency)
    Money(value * rate, toCurrency)
  }

  def +(other: Money): Money = {
    val convertedMoney = converter.convert(other, currency)
    (value + convertedMoney.value) (currency)
  }

  def -(other: Money): Money = {
    val convertedMoney = converter.convert(other, currency)
    (value - convertedMoney.value) (currency)
  }

  def *(value: BigDecimal): Money = {
    (value * value) (currency)
  }

  def >(other: Money): Boolean = {
    val convertedMoney = converter.convert(other, currency)

    value > convertedMoney.value
  }

  def <(other: Money): Boolean = {
    val convertedMoney = converter.convert(other, currency)

    value < convertedMoney.value
  }


  override def toString: String = s"${currency.name} ${f"$value%1.2f"}"

}