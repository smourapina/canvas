package canvas.actions

import canvas.actions.CanvasState.{canvasAlreadyExists, nextState}
import canvas.domain._
import canvas.matrix
import canvas.matrix.CanvasMatrix

trait CanvasActions extends matrix.CanvasOperations {

  def commandToCanvasAction(command: Command, state: CanvasState): CanvasState = command match {

    case c @ Canvas(_, _) if !canvasAlreadyExists(state) =>
      val canvas: CanvasMatrix = CanvasMatrix.apply(c)
      CanvasState(Some(state.currentCanvas), canvas)

    case _ @ Canvas(_, _) =>
      println("error: canvas already exists. please enter next command")
      state

    case l @ Line(_, _) =>
      val canvas = drawLine(state.currentCanvas, l)
      nextState(state, canvas)

    case r @ Rectangle(_, _) =>
      val canvas = drawRectangle(state.currentCanvas, r)
      nextState(state, canvas)

    case b @ BucketFill(_, _) =>
      val canvas = bucketFill(state.currentCanvas, b)
      nextState(state, canvas)

    case _ =>
      println("error: unknown canvas error. please enter next command")
      state
  }

}


