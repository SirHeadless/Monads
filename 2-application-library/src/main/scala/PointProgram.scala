import Maybe.{Just, Nothing}
import Package.InfixNotationForFlatMap

object PointProgram {

//  def createIO(args: Array[String]): IO[Unit] =  {
//    val firstIO: IO[Unit] = displayKleisli(hyphens())
//
//    val secondIO: IO[Unit] = IO.create{
//      val _: Unit = firstIO.unsafeRun()
//
//      val secondIO = displayKleisli(question())
//      secondIO.unsafeRun()
//    }
//
//    val thirdIO: IO[String] = IO.create{
//      val _ = secondIO.unsafeRun()
//
//      val thirdIO = promptKleisli()
//      thirdIO.unsafeRun()
//    }
//
//    val fourthIO: IO[String] = IO.create {
//      val input = thirdIO.unsafeRun()
//
//      val integerAmount: Int = convertStringToInt(input)
//      val positiveAmount: Int = ensureAmountIsPositive(integerAmount)
//      val balance: Int = round(positiveAmount)
//      val message: String = createMessage(balance)
//
//      message
//    }
//
//    val fifthIO: IO[Unit] = IO.create {
//      val message = fourthIO.unsafeRun()
//
//      val fifthIO = displayKleisli(message)
//      fifthIO.unsafeRun()
//    }
//
//    val sixthIO: IO[Unit] = IO.create {
//      val _: Unit = fifthIO.unsafeRun()
//
//      val sixthIO = displayKleisli(hyphens())
//      sixthIO.unsafeRun()
//
//    }
//
//    sixthIO
//  }

//  def createIOWithMonadFlatmap(args: Array[String]): IO[Unit] =  {
//    import Package._
//    val firstIO: IO[Unit] = displayKleisli(hyphens())
//
//    val secondIO = firstIO.flatMap(_ => displayKleisli(question()))
//
//    val thirdIO = secondIO.flatMap(promptKleisli)
//
//    val fourthIO = thirdIO.flatMap(input => IO.create {
//      val integerAmount: Int = convertStringToInt(input)
//      val positiveAmount: Int = ensureAmountIsPositive(integerAmount)
//      val balance: Int = round(positiveAmount)
//      val message: String = createMessage(balance)
//
//      message
//    })
//
//    val fifthIO = fourthIO.flatMap(message => displayKleisli(message))
//
//    val sixthIO = fifthIO.flatMap(_ => displayKleisli(hyphens()))
//
//    sixthIO
//  }

  def createIOWithForComprehensions(args: Array[String]): IO[Unit] =  {
    import Package._

    for {
      _ <- displayKleisli(hyphens())
      _ <- displayKleisli(question())
      input <- promptKleisli()
      _ <- displayKleisli(fromInputToMessage(input))
      _ <- displayKleisli(hyphens())
    } yield ()
  }

  private def fromInputToMessage(input: String): String = {
    convertStringToInt(input).mapOrElse
    { amount =>
      val positiveAmount: Int = ensureAmountIsPositive(amount)
      val balance: Int = round(positiveAmount)
      val message: String = createMessage(balance)
      message
    }("Sorry, invalid input")

  }

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
    IO.create("sdfds") //scala.io.StdIn.readLine

  //potential side effect (throwing Exception)
  private lazy val convertStringToInt: String => Maybe[Int] = input =>
    try Just(input.toInt) catch {
      case _: NumberFormatException => Nothing
    }

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
