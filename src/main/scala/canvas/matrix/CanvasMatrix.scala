package canvas.matrix

import canvas.domain.{Canvas, Line}


/**
  * The drawing canvas is internally represented using a two-dimensional Array of characters.
  */


class CanvasMatrix(dimensions: Canvas, currentCanvas: Array[Array[Char]]) extends CanvasOperationsValidation {

  override def toString: String = currentCanvas.map(_.mkString).mkString("\n")

  def drawLine(commandLine: Line): Either[CanvasOperationError, CanvasMatrix] = {

    val updatedCanvas = currentCanvas

    commandLine match {

      case valid if lineWithinCanvasBounds(dimensions, commandLine) => valid match {
        case _ if lineIsHorizontal(commandLine) =>
          for (
            x <- commandLine.origin.x to commandLine.last.x
          ) updatedCanvas(commandLine.origin.y)(x) = CanvasAscii.shapesBorder
          Right(new CanvasMatrix(dimensions, updatedCanvas))


        case _ if lineIsVertical(commandLine) =>
          for (
            y <- commandLine.origin.y to commandLine.last.y
          ) updatedCanvas(y)(commandLine.origin.x) = CanvasAscii.shapesBorder
          Right(new CanvasMatrix(dimensions, updatedCanvas))


        case _ => Left(InvalidShape())
      }

      case _ => Left(OperationNotAllowedError())
    }

  }

}


object CanvasMatrix extends CanvasOperationsValidation {

  def apply(dimensions: Canvas): CanvasMatrix = {

    val beginAndEnd: Array[Char] = Array.fill(dimensions.width + 2)(CanvasAscii.horizontalBorder)
    val middle: Array[Array[Char]] = Array.fill(dimensions.height)(
      Array(CanvasAscii.verticalBorder) ++ Array.fill(dimensions.width)(' ') ++ Array(CanvasAscii.verticalBorder)
    )

    new CanvasMatrix(dimensions, beginAndEnd +: middle :+ beginAndEnd)

  }
}

