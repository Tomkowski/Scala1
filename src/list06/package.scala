package list06

import scala.collection.mutable.ListBuffer

trait Pluginable {
  def plugin(text: String): String = text
}

trait Reverting extends Pluginable {
  override def plugin(text: String): String = {
    def revert(text: String): String = {
      if (text.isEmpty) return ""

      text.last + revert(text.dropRight(1))
    }

    super.plugin(revert(text))
  }
}

trait LowerCasing extends Pluginable {
  override def plugin(text: String): String = {
    def lowerCasing(text: String): String = {
      if (text.isEmpty) return ""
      val first = text(0)
      val lowerBound = 'A' - 1
      val upperBound = 'Z' + 1
      val transform = if (lowerBound < first && first < upperBound) (first + 32).toChar else first

      transform + lowerCasing(text.drop(1))

    }

    super.plugin(lowerCasing(text))
  }
}

trait SingleSpacing extends Pluginable {
  override def plugin(text: String): String = {
    def singleSpace(): String = {
      if (text.isEmpty) return ""
      val space = 32
      val previous = text(0)

      def spacing(text: String, previous: Char): String = {
        if (text.isEmpty) return ""
        val current = text(0)
        if (current == space && previous == current) return spacing(text.drop(1), current)

        current + spacing(text.drop(1), current)

      }

      previous + spacing(text.drop(1), previous)

    }

    super.plugin(singleSpace())
  }

}

trait NoSpacing extends Pluginable {
  override def plugin(text: String): String = {
    if (text.isEmpty) return ""
    val space = 32

    def noSpace(text: String): String = {
      if (text.isEmpty) return ""

      val current = text(0)
      if (current == space) return noSpace(text.drop(1))

      current + noSpace(text.drop(1))

    }

    super.plugin(noSpace(text))
  }
}

trait DuplicateRemoval extends Pluginable {
  override def plugin(text: String): String = {
    if (text.isEmpty) return ""
    val ASCII = ListBuffer.fill[Int](128)(0)
    text.foreach(ch => ASCII(ch) = ASCII(ch) + 1)

    def dupRem(text: String): String = {
      if (text.isEmpty) return ""
      val current = text(0)
      if (ASCII(current) == 1) return current + dupRem(text.drop(1))
      dupRem(text.drop(1))
    }

    super.plugin(dupRem(text))
  }
}

trait Rotating extends Pluginable {
  override def plugin(text: String): String = {
    def rotate(): String = text.last + text.dropRight(1)

    super.plugin(rotate())
  }
}

trait Doubling extends Pluginable {
  override def plugin(text: String): String = {
    def double(): String = {
      val textLength = text.length

      (for (i <- 0 until textLength)
        yield if ((i & 1) == 0) {
          text(i)
        }
        else s"${text(i)}${text(i)}"
        ).mkString("")

    }

    super.plugin(double())
  }
}

trait Shortening extends Pluginable {
  override def plugin(text: String): String = {
    def short(): String = {
      val textLength = text.length

      (for (i <- 0 until textLength)
        yield if ((i & 1) == 0) {
          text(i)
        }
        else ""
        ).mkString("")

    }

    super.plugin(short())
  }
}

object Main {
  val actionA: Pluginable = new Pluginable with SingleSpacing with Doubling with Shortening
  val actionB: Pluginable = new Pluginable with NoSpacing with Shortening with Doubling
  val actionC: Pluginable = new Pluginable with LowerCasing with Doubling
  val actionD: Pluginable = new Pluginable with DuplicateRemoval with Rotating
  val actionE: Pluginable = new Pluginable with NoSpacing with Shortening with Doubling with Reverting
  val actionF: Pluginable = new Pluginable {
    val r: Rotating = new Rotating {}

    override def plugin(text: String): String = r.plugin(r.plugin(r.plugin(r.plugin(r.plugin(text)))))
  }
  val actionG: Pluginable = new Pluginable {
    override def plugin(text: String): String = actionA.plugin(actionB.plugin(text))
  }

  def main(args: Array[String]): Unit = {

    print(actionG.plugin("Ala ma kota!"))
  }
}


