package canvas

import canvas.domain.Command
import canvas.io.{InputError, InputReader}

import scala.io.StdIn._

object CanvasApplication extends App {

  val promptMessage = "enter command: "

  val inputReader = new InputReader
  runApplication()


  def runApplication(): Unit = {

    Console.print(promptMessage)

    val input = readLine()
    val res: Either[InputError, Command] = inputReader.inputToCommand(input)

    res match {
      case Right(command) => println(command)
      case Left(error) => println(error)
    }

    runApplication()
  }

}
