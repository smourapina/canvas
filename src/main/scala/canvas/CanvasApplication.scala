package canvas

import canvas.actions.CanvasState
import canvas.domain._
import canvas.io.{InputError, InputReader, OutputWriter}
import canvas.matrix.{CanvasMatrix, CanvasOperationError, CanvasOperations}

import scala.annotation.tailrec
import scala.io.StdIn._

object CanvasApplication extends App with CanvasOperations{

  val promptMessage = "enter command: "

  val inputReader = new InputReader
  val writer = new OutputWriter

  val state = CanvasState(None, CanvasMatrix.apply(Canvas(1, 1)))

  loop(state)

  @tailrec
  def loop(state: CanvasState): Unit = {

    Console.print(promptMessage)

    val input = readLine()
    val res: Either[InputError, Command] = inputReader.inputToCommand(input)

    res match {

      case Right(_ @ Quit()) =>
        System.exit(0)

      case Right(c @ Canvas(_, _)) if !canvasAlreadyExists(state) =>
        val canvas: CanvasMatrix = CanvasMatrix.apply(c)
        printCanvas(canvas)
        loop(CanvasState(Some(canvas), canvas))

      case Right(c @ Canvas(_, _)) =>
        println("error: canvas already exists")
        loop(state)

      case Right(l @ Line(_, _)) =>
        val canvas: Either[CanvasOperationError, CanvasMatrix] = drawLine(state.currentCanvas, l)
        nextState(state, canvas)

      case Right(r @ Rectangle(_, _)) =>
        val canvas: Either[CanvasOperationError, CanvasMatrix] = drawRectangle(state.currentCanvas, r)
        nextState(state, canvas)

      case Right(b @ BucketFill(_, _)) =>
        val canvas: Either[CanvasOperationError, CanvasMatrix] = bucketFill(state.currentCanvas, b)
        nextState(state, canvas)

      case Left(error) =>
        println(error)
        loop(state)
    }

  }

 def printCanvas(canvas: CanvasMatrix): Unit = {
    writer.printCurrentCanvas(canvas)
  }

  def nextState(last: CanvasState, state: Either[CanvasOperationError, CanvasMatrix]): Unit = {
    state match {
      case Right(c) => printCanvas(c); loop(CanvasState(Some(last.currentCanvas), c))
      case Left(error) => println(error); loop(CanvasState(Some(last.currentCanvas), last.currentCanvas))
    }
  }

  def canvasAlreadyExists(state: CanvasState): Boolean = state.previousCanvas.isDefined

}

