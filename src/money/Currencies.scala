package money

import money.Main.Currency

object Currencies {

  object USD extends Currency {
    val name = "USD"

    override def toString: String = name
  }

  object EUR extends Currency {
    val name = "EUR"

    override def toString: String = name
  }

  object PLN extends Currency {
    val name = "PLN"

    override def toString: String = name
  }

}
