abstract class Figure{
  def area() : Double
  val description: String
}

class Triangle() extends Figure{
  override def area(): Double = 10.3

  override val description: String = "Triangle"
}

class Rectangle() extends Figure{
  override def area(): Double = 5.2

  override val description: String = "Rectangle"
}

class Square() extends Figure{
  override def area(): Double = 11.5

  override val description: String = "Square"
}

object FigureManager{
  def areaSum(figures: List[Figure]): Double = (for(f <- figures) yield f.area()).sum

  def printAll(figures: List[Figure]) ={
    figures.foreach(it => println(it.description))
  }
}
