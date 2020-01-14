import java.io.File
import java.util.Scanner

import scala.collection.mutable.ListBuffer
import scala.io.Source

object ScalaScript {
  def scalarUgly(xs: List[Int], ys: List[Int]):Int = {

    var vectorLength = xs.length
    var scalarProduct = 0

    var counter: Int = 0

    do {
      scalarProduct += (xs(counter) * ys(counter))
      counter += 1
    }
    while (counter < vectorLength)

    return scalarProduct
  }

  def scalar(xs: List[Int], ys: List[Int]) :Int = {
    val vectorLength = xs.length

    (for (i <- 0 until vectorLength) yield xs(i) * ys(i)).sum
  }

  // def List.
  // sumList(xs: List[Int]) = xs.foldLeft(0)(_+_) extension???

  def sortUgly(xs: List[Int]): List[Int] = ???

  def sort(xs: List[Int]): List[Int] = ??? //quicksor

  def isPrimeUgly(n:Int): Boolean = {

    if(n <= 1) return false
    var loopRange = Math.sqrt(n)
    var i = 2
    do{
      if(n % i == 0) return false
      i += 1
    }while(i < loopRange)

    true

  }

  def isPrime(n:Int): Boolean = {
    if(n<=1) return false;
    val loopRange = Math.sqrt(n).toInt

    for(_ <-2 to loopRange){
      if(n % 2 == 0) return false;
    }
    return true;
  }

  def primePairsUgly(n: Int): List[(Int, Int)] = {

    var a = ListBuffer[(Int,Int)]()
    var i = 1
    do{
      var j = 1
      do{
        if(isPrime(i + j)){
         a += ((i,j))
        }
        j += 1
      }while(j < n)
      i += 1
    }while(i < n)

    return a.toList
  }

  def primePairs(n: Int): List[(Int,Int)] = {

    val a =  for(i<-1 until n;
       j<-1 until n if isPrime(i+j))
      yield (i,j)

    return a.toList
    }

  val filesHere = new java.io.File(".").listFiles

  def fileLinesUgly(file: java.io.File): List[String] = {
    var listBuffer = new ListBuffer[String]
    var fileLines = new Scanner(file)

    do{
        listBuffer += fileLines.nextLine()
    }while(fileLines.hasNextLine())

    return listBuffer.toList
  }



  def fileLines(file: java.io.File): List[String]={
    Source.fromFile(file.getPath)
      .getLines
      .toList
  }

  def printNonEmptyUgly(pattern: String): Unit = {
      var i = 0
      var loopRange = filesHere.size
      var currentFile: java.io.File = filesHere(i)
      do{
        if(fileToString(currentFile).endsWith(".scala") && currentFile.length() > 0) println(currentFile)
        i += 1
        currentFile = filesHere(i)
      }while(i < loopRange - 1)
  }

  def fileToString(file: File) = file.toString

  def printNonEmpty(pattern: String)= for(file <- filesHere){
      val fileString = fileToString(file)
      if(fileString.endsWith(".scala") && file.length() > 0) println(file)
    }


  def main(args: Array[String]): Unit = {
    printNonEmptyUgly("")
    printNonEmpty("")
    //filesHere.foreach(it => fileLines(it))
    filesHere.foreach(it => fileLinesUgly(it))
  }
}