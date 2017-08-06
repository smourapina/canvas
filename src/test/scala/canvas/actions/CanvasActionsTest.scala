package canvas.actions

import canvas.domain.Canvas
import canvas.matrix.CanvasMatrix
import canvas.util.DomainFixture
import org.scalatest.WordSpec

class CanvasActionsTest extends WordSpec with CanvasActions with DomainFixture {

  val initialState = CanvasState(None, CanvasMatrix.apply(Canvas(1, 1)))
  val newState = CanvasState(Some(initialState.currentCanvas), CanvasMatrix.apply(canvasCommand))

  "CanvasActions" when {

    "a canvas command is received for the first time" should {
      "update the canvas state accordingly" in {
        val actionResult = commandToCanvasAction(canvasCommand, initialState)
        assert(actionResult.currentCanvas.toString == newState.currentCanvas.toString)
      }
    }

    "a canvas command is received after a previous one" should {
      "not update the canvas state" in {
        val actionResult = commandToCanvasAction(canvasCommand, newState)
        assert(actionResult.currentCanvas.toString == newState.currentCanvas.toString)
      }
    }

  }

}
