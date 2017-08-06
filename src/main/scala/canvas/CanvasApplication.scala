package canvas

import canvas.actions.{CanvasActions, CanvasState}
import canvas.domain._
import canvas.io.{InputError, InputReader}
import canvas.matrix.CanvasMatrix

import scala.annotation.tailrec
import scala.io.StdIn._

object CanvasApplication extends App with CanvasActions {

  val promptMessage = "enter command: "

  val inputReader = new InputReader

  val initialState = CanvasState(None, CanvasMatrix.apply(Canvas(1, 1)))

  loop(initialState)

  @tailrec
  def loop(state: CanvasState): Unit = {

    Console.print(promptMessage)

    val input = readLine()
    val res: Either[InputError, Command] = inputReader.inputToCommand(input)

    res match {

      case Right(_ @ Quit()) =>
        System.exit(0)

      case Right(c) =>
        val next = commandToCanvasAction(c, state)
        Console.println(next.currentCanvas)
        loop(next)

      case Left(error) =>
        Console.println(error)
        loop(state)
    }

  }

}
