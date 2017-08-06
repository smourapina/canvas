package canvas.matrix

import canvas.domain.{BucketFill, Line, Point, Rectangle}

trait CanvasOperations extends CanvasValidation {

  def drawLine(canvas: CanvasMatrix, lineCommand: Line): Either[CanvasOperationError, CanvasMatrix] = {

    val updatedCanvas = canvas.currentCanvas

    lineCommand match {

      case valid if lineWithinCanvasBounds(canvas.dimensions, lineCommand) => valid match {

        case _ if lineIsHorizontal(lineCommand) =>
          for (
            x <- lineCommand.origin.x to lineCommand.last.x
          ) updatedCanvas(lineCommand.origin.y)(x) = CanvasAscii.shapesBorder
          Right(new CanvasMatrix(canvas.dimensions, updatedCanvas))


        case _ if lineIsVertical(lineCommand) =>
          for (
            y <- lineCommand.origin.y to lineCommand.last.y
          ) updatedCanvas(y)(lineCommand.origin.x) = CanvasAscii.shapesBorder
          Right(new CanvasMatrix(canvas.dimensions, updatedCanvas))

        case _ => Left(InvalidShape("error: invalid shape"))
      }

      case _ => Left(OperationNotAllowedError("error: operation not allowed"))
    }

  }

  def drawRectangle(canvas: CanvasMatrix, rectangle: Rectangle): Either[CanvasOperationError, CanvasMatrix] = {
    val upperRight = Point(rectangle.upperLeftCorner.x, rectangle.lowerRightCorner.y)
    val lowerLeft = Point(rectangle.lowerRightCorner.x, rectangle.upperLeftCorner.y)

    drawLine(canvas, Line(rectangle.upperLeftCorner, upperRight))
    drawLine(canvas, Line(lowerLeft, rectangle.lowerRightCorner))
    drawLine(canvas, Line(rectangle.upperLeftCorner, lowerLeft))
    drawLine(canvas, Line(upperRight, rectangle.lowerRightCorner))
  }

  def bucketFill(canvas: CanvasMatrix, fill: BucketFill): Either[CanvasOperationError, CanvasMatrix] = {

    val updatedCanvas = canvas.currentCanvas

    def bucketF(x: Int, y: Int): Unit = {
      if (pointIsWithinCanvasBounds(canvas.dimensions, Point(y, x)) && pointIsBlank(updatedCanvas, Point(x, y))) {
        updatedCanvas(x)(y) = fill.color
        bucketF(x + 1, y)
        bucketF(x - 1, y)
        bucketF(x, y + 1)
        bucketF(x, y - 1)
      }
    }

    bucketF(fill.area.y, fill.area.x)

    Right(new CanvasMatrix(canvas.dimensions, updatedCanvas))
  }

}
