package canvas.matrix

import canvas.domain._

class CanvasMatrix(dimensions: Canvas, currentCanvas: Array[Array[Char]]) extends CanvasOperations {

  override def toString: String = currentCanvas.map(_.mkString).mkString("\n")

  def drawLine(lineCommand: Line): Either[CanvasOperationError, CanvasMatrix] = {

    val updatedCanvas = currentCanvas

    lineCommand match {

      case valid if lineWithinCanvasBounds(dimensions, lineCommand) => valid match {

        case _ if lineIsHorizontal(lineCommand) =>
          for (
            x <- lineCommand.origin.x to lineCommand.last.x
          ) updatedCanvas(lineCommand.origin.y)(x) = CanvasAscii.shapesBorder
          Right(new CanvasMatrix(dimensions, updatedCanvas))


        case _ if lineIsVertical(lineCommand) =>
          for (
            y <- lineCommand.origin.y to lineCommand.last.y
          ) updatedCanvas(y)(lineCommand.origin.x) = CanvasAscii.shapesBorder
          Right(new CanvasMatrix(dimensions, updatedCanvas))


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

  def bucketFill(fill: BucketFill): Either[CanvasOperationError, CanvasMatrix] = {

    val updatedCanvas = currentCanvas

    def bucketF(x: Int, y: Int): Unit = {
      //println("Point: " + x.toString + "  " + y.toString)
      if (pointIsWithinCanvasBounds(dimensions, Point(y, x)) && pointIsBlank(updatedCanvas, Point(x, y))) {
        updatedCanvas(x)(y) = fill.color
        bucketF(x + 1, y)
        bucketF(x - 1, y)
        bucketF(x, y + 1)
        bucketF(x, y - 1)
      }
    }

    bucketF(fill.area.x, fill.area.y)

    Right(new CanvasMatrix(dimensions, updatedCanvas))
  }

}


object CanvasMatrix extends CanvasOperations {

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

