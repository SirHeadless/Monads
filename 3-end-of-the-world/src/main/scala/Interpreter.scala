import Program.Description
import javax.management.Descriptor

object Interpreter {

  def main(args: Array[String]): Unit = {
    print(Console.RED)

    val description: Description[Unit] =
      Program.createDescription(args)

    def interpret[A](description: Description[A]): A = {
      // Description is a function. The apply method of a function executes the function
      description.apply()
    }
    print(Console.GREEN)
    interpret(description)
    print(Console.RED)
  }
}
