import scala.Array._
import scala.util.matching.Regex

object Main {

  def main(args: Array[String]) {

    // Manipulation des lists
    println("\n Manipulation des Lists: ")
    manipArray

    // Manipulation des Matrix
    println("\n Manipulation des Matrix: ")
    manipMatrix

    // Manipulate Trail (like abstract class in java)
    println("\n Manipulation des Trails: ")
    manipTrail

    // Manipulate Pattern matching
    println("\n Manipulation des Pattern matching: ")
    manipPatternMatching

    // Manipulate Pattern Regex
    println("\n Manipulation des Pattern matching: ")
    manipPatternRegex

    // Manipulate Yield
    println("\n Manipulation des Yield: ")
    manipYield

    // Manipulate Implicit
    println("\n Manipulation des implicit")
    manipImplicit

    // Manipulate Option
    println("\n Manipulation des Options")
    manipOptions
  }

  def manipArray : Unit = {
    var myList = Array(1.9, 2.9, 3.4, 3.5)

    // Print all the array elements
    myList.foreach((s: Double) => println(s"element = ${s}"));

    // Summing all elements
    println("Total is " + myList.sum);

    // Finding the largest element
    println("Max is " + myList.last);

    var myList2 = Array(8.9, 7.9, 0.4, 1.5)

    // Print concat arrays
    println("\nConcat arrays, results: ")
    concat(myList, myList2).foreach {println}
  }

  def manipMatrix: Unit = {
    var myMatrix = ofDim[Int](3,3)


    // build a matrix
    0 to 2 foreach((i : Int) => {0 until 3 foreach((j: Int)=> {myMatrix(i)(j) = j})})

    // Print two dimensional array
    myMatrix.foreach((i: Array[Int]) => {i.foreach((j: Int) => print(" " + j)); println();})

  }

  def manipTrail: Unit = {
    val p1 = new Point(2, 3)
    val p2 = new Point(2, 4)
    val p3 = new Point(3, 3)

    println(p1.isNotEqual(p2))
    println(p1.isNotEqual(p3))
    println(p1.isNotEqual(2))
  }

  def manipPatternMatching: Unit = {
    println(matchTest("two"))
    println(matchTest("test"))
    println(matchTest(1))
    println(matchTest(2))


    def matchTest(x: Any): Any = x match {
      case 1 => "one"
      case "two" => 2
      case Int => "scala.Int"
      case _ => "many"
    }
  }

  def manipPatternRegex: Unit = {
    var pattern = "(S|s)cala".r
    var str = "Scala is scalable and cool"

    println(pattern findFirstIn str)
    println((pattern findAllIn str).mkString(","))
    println(pattern replaceFirstIn(str, "Java"))


    pattern = new Regex("abl[ae]\\d+")
    str = "ablaw is able1 and cool"

    println((pattern findAllIn str).mkString(","))
  }

  def manipYield: Unit = {
    println((for (d <- 1 to 25 if d > 10) yield d).mkString(","))

    println()
    val a = Array(1, 2, 3, 4, 5)
    println((for (e <- a if e > 1) yield e * 2).mkString(","))
  }

  def manipImplicit : Unit = {
    // Exemple 1
    class BetterString(val s: String) {
      def increment = s.map(c => (c + 1).toChar)
    }
    implicit def stringToString(s: String) = new BetterString(s)
    println("HAL".increment)

    // Exemple 2
    implicit val n = "John"
    implicit val a = 20
    def greet(greeting:String)(implicit name:String, age:Int) = {
      println(s"$greeting $name, you are $age")
    }
    greet("Hello") //compile et renvoie Hello John, you are 20
  }

  def manipOptions : Unit = {
    def getIntOption(x: Int) = if (x == 0) None else Some(x)
    val firstOption:Option[Int] = getIntOption(1)
    val secondOption:Option[Int] = getIntOption(2)

    // Avec flatmap
    var result = firstOption.flatMap { first =>
      secondOption.map { second =>
        first + second }
    }
    println(result);

    // Avec yield
    result = for {
      first <- firstOption
      second <- secondOption
    } yield (first + second)
    println(result);
  }
}
