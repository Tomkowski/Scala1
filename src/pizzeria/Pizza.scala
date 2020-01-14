package pizzeria

case class Pizza(
                pizzaType: PizzaType,
                size: Size,
                crust: Crust ,
                extraMeat: Option[Meat] = None,
                extraTopping: Option[Topping] = None
                )
{
  override def toString: String = {
    s"""{
       |"pizza_type" : "${pizzaType.name}",
        "crust" : "${crust.name}",
        "extraMeat" : "${extraMeat}",
        "extraTopping" : "${extraTopping}",
      }
      """.stripMargin
  }

  val pizzaTypePrice = PriceManager.pizzaPrice(pizzaType)

  val sizeTypePrice = PriceManager.sizePrice(size)

  val meatPrice = PriceManager.eval(PriceManager.meatPrice(extraMeat))

  val toppingPrice = PriceManager.eval(PriceManager.toppingPrice(extraTopping))

  val price = sizeTypePrice * List(pizzaTypePrice, meatPrice, toppingPrice).sum

}
case class Drink(name: String, price: Double)

case class Crust(name: String)

case class Meat(name: String, price: Double)

case class Topping(name: String, price: Double)

case class Size(name: String, multiplier: Double)

case class PizzaType(name: String, price: Double)

object PriceManager {
  def pizzaPrice(pizza: PizzaType): Double = pizza.price

  def sizePrice(size: Size): Double = size.multiplier

  def meatPrice(meat: Option[Meat]): Option[Double] = meat match {
    case Some(meat) => Option(meat.price)
    case _ => None
   }

  def toppingPrice(topping: Option[Topping]) = topping match {
    case Some(topping) => Option(topping.price)
    case _ => None

  }

  def drinkPrice(drink: Drink) = drink.price

  def eval(calc: Option[Double]) = calc match{
    case Some(i) => i
    case _ => 0.0
  }
}