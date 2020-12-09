
object PointFreeProgram {

  import Package._

  lazy val createIO: Array[String] => IO[Unit] =
    ignoreArgs --> hyphens --> displayKleisli >=>
      question --> displayKleisli >=>
      promptKleisli >=>
      convertStringToInt --> ensureAmountIsPositive --> round --> createMessage --> displayKleisli >=>
      hyphens --> displayKleisli

//  lazy val createIO: Array[String] => IO[Unit] =
//    ignoreArgs -->
//      hyphens -->
//      display -->
//      question -->
//      display -->
//      prompt -->
//      convertStringToInt -->
//      ensureAmountIsPositive -->
//      round -->
//      createMessage -->
//      display -->
//      hyphens -->
//      display -->
//      IO.brokenCreate




  private val ignoreArgs: Array[String] => Unit = _ => ()

  private lazy val question: Any => String = input =>
    "How much money would you like to deposit?"

  private lazy val hyphens: Any => String = input =>
    "\u2500" * 50

  // side effect (writing to the console)
  private lazy val display: Any => Unit = input =>
    println(input)

  private lazy val displayKleisli: Any => IO[Unit] = input =>
    IO.create(println(input))


  //side effect (reading from the console)
  private lazy val prompt: Any => String = input =>
    "5" //scala.io.StdIn.readLine

  private lazy val promptKleisli: Any => IO[String] = input =>
    IO.create("5") //scala.io.StdIn.readLine

  //potential side effect (throwing Exception)
  private lazy val convertStringToInt: String => Int = input =>
    input.toInt

  private lazy val ensureAmountIsPositive: Int => Int = amount =>
    if (amount < 1)
      1
    else
      amount

  private lazy val round: Int => Int = amount =>
    if (isDivisibleByHundred(amount))
      amount
    else
      round(amount + 1)

  private lazy val isDivisibleByHundred: Int => Boolean = amount =>
    amount % 100 == 0

  private lazy val createMessage: Int => String = balance => s"Congratulations, you now have USD $balance"

}


