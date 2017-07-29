package canvas.io

import canvas.domain._
import org.scalatest.{Matchers, WordSpec}

class InputReaderTest extends WordSpec with Matchers {

  val inputReader = new InputReader

  "InputReader" when {

    "user inputs the correct quit command" should {
      "emit a Quit command" in {
        val command = inputReader.inputToCommand("Q")
        val commandWithSpaces = inputReader.inputToCommand("   Q    ")

        assert(command == Right(Quit()))
        assert(commandWithSpaces == Right(Quit()))
      }
    }

    "user inputs the correct canvas command" should {
      "emit a Canvas command" in {
        val command = inputReader.inputToCommand("C 3 5")
        val commandMoreThanOneDigit = inputReader.inputToCommand("C 30 52")
        val commandWithSpaces = inputReader.inputToCommand("  C   30       120  ")

        assert(command == Right(Canvas(3, 5)))
        assert(commandMoreThanOneDigit == Right(Canvas(30, 52)))
        assert(commandWithSpaces == Right(Canvas(30, 120)))
      }
    }

    "user inputs an incorrect canvas command" should {
      "result in an IncorrectCommandError" in {
        val invalidCommand = inputReader.inputToCommand("Cx 3 5")
        val invalidNumArgs = inputReader.inputToCommand("C 30")

        assert(invalidCommand == Left(IncorrectCommandError("tbd")))
        assert(invalidNumArgs == Left(IncorrectCommandError("tbd")))
      }
    }

    "user inputs the correct line command" should {
      "emit a Line command" in {
        val command = inputReader.inputToCommand("L 1 2 3 4")
        val commandMoreThanOneDigit = inputReader.inputToCommand("L 12 1 34 20")
        val commandWithSpaces = inputReader.inputToCommand("  L 12    121   34 20    ")

        assert(command == Right(Line(Point(1,2), Point(3, 4))))
        assert(commandMoreThanOneDigit == Right(Line(Point(12, 1), Point(34, 20))))
        assert(commandWithSpaces == Right(Line(Point(12, 121), Point(34, 20))))
      }
    }

    "user inputs an incorrect line command" should {
      "result in an IncorrectCommandError" in {
        val invalidCommand = inputReader.inputToCommand("Lx 3 5 4 5")
        val invalidNumArgs = inputReader.inputToCommand("L 30 20")

        assert(invalidCommand == Left(IncorrectCommandError("tbd")))
        assert(invalidNumArgs == Left(IncorrectCommandError("tbd")))
      }
    }

    "user inputs the correct rectangle command" should {
      "emit a Rectangle command" in {
        val command = inputReader.inputToCommand("R 1 2 3 4")
        val commandMoreThanOneDigit = inputReader.inputToCommand("R 12 1 34 20")
        val commandWithSpaces = inputReader.inputToCommand("  R 12    121   34 20    ")

        assert(command == Right(Rectangle(Point(1,2), Point(3, 4))))
        assert(commandMoreThanOneDigit == Right(Rectangle(Point(12, 1), Point(34, 20))))
        assert(commandWithSpaces == Right(Rectangle(Point(12, 121), Point(34, 20))))
      }
    }

    "user inputs an incorrect rectangle command" should {
      "result in an IncorrectCommandError" in {
        val invalidCommand = inputReader.inputToCommand("Rx 3 5 4 5")
        val invalidNumArgs = inputReader.inputToCommand("R 30 20")

        assert(invalidCommand == Left(IncorrectCommandError("tbd")))
        assert(invalidNumArgs == Left(IncorrectCommandError("tbd")))
      }
    }

    "user inputs the correct bucket fill command" should {
      "emit a BucketFill command" in {
        val command = inputReader.inputToCommand("B 1 2 c")
        val commandMoreThanOneDigit = inputReader.inputToCommand("B 12 1 o")
        val commandWithSpaces = inputReader.inputToCommand("  B 2    121  g    ")

        assert(command == Right(BucketFill(Point(1, 2), 'c')))
        assert(commandMoreThanOneDigit == Right(BucketFill(Point(12, 1), 'o')))
        assert(commandWithSpaces == Right(BucketFill(Point(2, 121), 'g')))
      }
    }

    "user inputs an incorrect bucket fill command" should {
      "result in an IncorrectCommandError" in {
        val invalidCommand = inputReader.inputToCommand("Bx 3 5 4 5")
        val invalidNumArgs = inputReader.inputToCommand("B 30 20")

        assert(invalidCommand == Left(IncorrectCommandError("tbd")))
        assert(invalidNumArgs == Left(IncorrectCommandError("tbd")))
      }
    }

    "user inputs an unknown command" should {
      "result in an IncorrectCommandError" in {
        val command = inputReader.inputToCommand("X 1")

        assert(command == Left(IncorrectCommandError("tbd")))
      }
    }

  }

}
