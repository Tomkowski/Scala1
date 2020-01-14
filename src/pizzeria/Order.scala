package pizzeria

class Order(
             name: String,
             address: String,
             phone: PhoneNumber,
             pizzas: List[Pizza],
             drinks: List[Drink],
             discount: Option[Discount] = None,
             specialInfo: Option[String] = None
           ) {

  override def toString: String = {
    s"""{
        "order_${this.hashCode()}" : {
        "name" : "$name",
        "address" : "$address",
        "phone" : "$phone",
        "pizzas" : "${for (pizza <- pizzas) yield pizza.toString}",
        "drinks" : "${for (drink <- drinks) yield drink}",
        "discount" : "$discount",
        }
        }""".trim

  }

  def extraMeatPrice: Option[Double] = {
    Some((for (pizza <- pizzas) yield pizza.meatPrice).iterator.sum * DiscountManager.calculateDiscount(discount))
  }

  def pizzasPrice: Option[Double] = {
    Some((for (pizza <- pizzas) yield pizza.price).iterator.sum * DiscountManager.calculateDiscount(discount))
  }

  def drinksPrice: Option[Double] = {
    Some((for (drink <- drinks) yield PriceManager.drinkPrice(drink))
      .iterator
      .sum * DiscountManager.calculateDiscount(discount))
  }

  def priceByType(pizzaType: PizzaType): Option[Double] = {
    Some(pizzas.filter(pizza => pizza.pizzaType == pizzaType)
      .map(pizza => pizza.price)
      .sum * DiscountManager.calculateDiscount(discount))
  }

  val price: Double = f"${pizzasPrice.get + drinksPrice.get}%1.2f".toDouble
}

case class Discount(value: Double)

object DiscountManager {
  def calculateDiscount(discount: Option[Discount]) = discount match {
    case Some(s) => s.value
    case None => 1.0
  }
}

class PhoneNumber(phone: String) {
  val regex = "^[0-9]*$".r

  override def toString: String = phone match {
    case regex(_*) => phone
    case _ => s"Invalid Phone number - $phone"
  }
}

object Main {
  def main(args: Array[String]): Unit = {

    val MARGARITA = PizzaType("Margarita", 5.0)
    val FUNGHI = PizzaType("Funghi", 7.0)
    val PEPPERONI = PizzaType("Pepperoni", 6.5)

    val small = Size("Small", 0.9)
    val regular = Size("Regular", 1.0)
    val large = Size("Large", 1.5)

    val thin = Crust("Thin")
    val thick = Crust("Thick")

    val salami = Meat("Salami", 1.0)

    val ketchup = Topping("Ketchup", 0.5)
    val garlic = Topping("Garlic", 0.5)

    val lemonade = Drink("Lemonade", 2.0)

    val student = Discount(0.95)
    val senior = Discount(0.93)

    val margarita = Pizza(MARGARITA, large, thin)
    val funghi = Pizza(FUNGHI, small, thin)
    val pepperoni1 = Pizza(PEPPERONI, large, thin, extraTopping = Some(garlic))
    val pepperoni2 = Pizza(PEPPERONI, regular, thick, extraTopping = Some(ketchup))

    val order = new Order("Albert",
      "Mostowa 41",
      new PhoneNumber("674365245"),
      List(margarita, funghi, pepperoni1, pepperoni2),
      List(lemonade, lemonade),
      Some(student))
    println(s"Price by type: Pepperoni -> ${order.priceByType(PEPPERONI)}")
    println(s"order price = ${order.price}")
    println()
    println(order)
    println(order.extraMeatPrice)
  }

}