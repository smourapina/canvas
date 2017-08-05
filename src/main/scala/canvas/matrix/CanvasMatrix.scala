package canvas.matrix

import canvas.domain._

class CanvasMatrix(dimensions: Canvas, currentCanvas: Array[Array[Char]]) extends CanvasOperationsValidation {

  override def toString: String = currentCanvas.map(_.mkString).mkString("\n")

  def drawLine(lineCommand: Line): Either[CanvasOperationError, CanvasMatrix] = {

    lineCommand match {

      case valid if lineWithinCanvasBounds(dimensions, lineCommand) => valid match {

        case _ if lineIsHorizontal(lineCommand) =>
          for (
            x <- lineCommand.origin.x to lineCommand.last.x
          ) currentCanvas(lineCommand.origin.y)(x) = CanvasAscii.shapesBorder
          Right(this)


        case _ if lineIsVertical(lineCommand) =>
          for (
            y <- lineCommand.origin.y to lineCommand.last.y
          ) currentCanvas(y)(lineCommand.origin.x) = CanvasAscii.shapesBorder
          Right(this)


        case _ => Left(InvalidShape())
      }

      case _ => Left(OperationNotAllowedError())
    }

  }

  def drawRectangle(rectangle: Rectangle): Either[CanvasOperationError, CanvasMatrix] = {
    val upperRight = Point(rectangle.upperLeftCorner.x, rectangle.lowerRightCorner.y)
    val lowerLeft = Point(rectangle.lowerRightCorner.x, rectangle.upperLeftCorner.y)

    drawLine(Line(rectangle.upperLeftCorner, upperRight))
    drawLine(Line(lowerLeft, rectangle.lowerRightCorner))
    drawLine(Line(rectangle.upperLeftCorner, lowerLeft))
    drawLine(Line(upperRight, rectangle.lowerRightCorner))
  }
}


object CanvasMatrix extends CanvasOperationsValidation {

  def apply(dimensions: Canvas): CanvasMatrix = {

    val horizontalBorder: Array[Char] = Array.fill(dimensions.width + 2)(CanvasAscii.horizontalBorder)
    val middleSection: Array[Array[Char]] = Array.fill(dimensions.height)(
      Array(CanvasAscii.verticalBorder)
        ++ Array.fill(dimensions.width)(CanvasAscii.fillingSpace)
        ++ Array(CanvasAscii.verticalBorder)
    )

    new CanvasMatrix(dimensions, horizontalBorder +: middleSection :+ horizontalBorder)
  }
}

