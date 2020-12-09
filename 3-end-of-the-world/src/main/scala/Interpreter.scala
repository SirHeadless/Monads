
object Interpreter {

  def main(args: Array[String]): Unit = {
    print(Console.RED)

    val description: IO[Unit] =
      PointProgram.createIOWithForComprehensions(args)

    def interpret[A](description: IO[A]): A = {
      // Description is a function. The apply method of a function executes the function
      description.unsafeRun()
    }
    print(Console.GREEN)
    interpret(description)
    print(Console.RESET)
  }
}
