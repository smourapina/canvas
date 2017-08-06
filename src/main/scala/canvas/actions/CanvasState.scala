package canvas.actions

import canvas.io.OutputWriter
import canvas.matrix.{CanvasMatrix, CanvasOperationError}

case class CanvasState(previousCanvas: Option[CanvasMatrix], currentCanvas: CanvasMatrix)

object CanvasState {

  val writer = new OutputWriter

  def canvasAlreadyExists(state: CanvasState): Boolean = state.previousCanvas.isDefined

  def nextState(last: CanvasState, state: Either[CanvasOperationError, CanvasMatrix]): CanvasState = {

    state match {
      case Right(c) =>
        CanvasState(Some(last.currentCanvas), c)

      case Left(error) =>
        println(error)
        CanvasState(Some(last.currentCanvas), last.currentCanvas)
    }
  }

  def printCanvas(canvas: CanvasMatrix): Unit = {
    writer.printCurrentCanvas(canvas)
  }

}
