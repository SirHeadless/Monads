object Program {

  private type Thunk[A] = () => A
  type Description[A] = Thunk[A]

  def createDescription(args: Array[String]): Description[Unit] = () =>  {
    display(hyphens)

    display(question)

    val input: String = "5"//prompt()
    val integerAmount: Int = convertStringToInt(input)
    val positiveAmount: Int = ensureAmountIsPositive(integerAmount)
    val balance: Int = round(positiveAmount)
    val message: String = createMessage(balance)

    display(message)

    display(hyphens)
  }

  private val question: String =
    "How much money would you like to deposit?"

  private val hyphens: String =
    "\u2500" * 50

  // side effect (writing to the console)
  private def display(input: Any): Unit = {
    println(input)
  }

  //side effect (reading from the console)
  private def prompt(): String =
    scala.io.StdIn.readLine

  //potential side effect (throwing Exception)
  private def convertStringToInt(input: String): Int =
    input.toInt

  private def ensureAmountIsPositive(amount: Int): Int =
    if(amount < 1)
      1
    else
      amount

  private def round(amount: Int): Int =
    if (isDivisibleByHundred(amount))
      amount
    else
      round(amount + 1)

  private def isDivisibleByHundred(amount: Int): Boolean =
    amount % 100== 0

  private def createMessage(balance: Int): String =
    s"Congratulations, you now have USD $balance"
}
