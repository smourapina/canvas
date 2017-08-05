package canvas.matrix

import canvas.domain.{Canvas, Line, Point}

trait CanvasOperations {

  def pointIsWithinCanvasBounds(dimensions: Canvas, point: Point): Boolean = {
    point.x <= dimensions.width && point.y <= dimensions.height &&
      point.x >= 1 && point.y >= 1
  }

  def pointIsBlank(canvas: Array[Array[Char]], point: Point): Boolean = {
    canvas(point.x)(point.y) == CanvasAscii.fillingSpace
  }

  def lineWithinCanvasBounds(dimensions: Canvas, line: Line): Boolean = {
    pointIsWithinCanvasBounds(dimensions, line.origin) && pointIsWithinCanvasBounds(dimensions, line.last)
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
