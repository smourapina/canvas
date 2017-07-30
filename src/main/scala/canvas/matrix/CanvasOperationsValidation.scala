package canvas.matrix

import canvas.domain.{Canvas, Line}

trait CanvasOperationsValidation {

  def lineWithinCanvasBounds(dimensions: Canvas, line: Line): Boolean = {
    //TODO rework this check
    line.origin.x > 0 && line.origin.y <= dimensions.width &&
    line.last.x > 0 && line.last.y <= dimensions.height
  }

  def lineIsHorizontal(commandLine: Line): Boolean = {
    commandLine.origin.y == commandLine.last.y
  }

  def lineIsVertical(commandLine: Line): Boolean = {
    commandLine.origin.x == commandLine.last.x
  }

}

sealed trait CanvasOperationError

case class OperationNotAllowedError() extends CanvasOperationError
case class InvalidShape() extends CanvasOperationError
