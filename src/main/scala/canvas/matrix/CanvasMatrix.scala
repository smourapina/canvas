package canvas.matrix

import canvas.domain._

case class CanvasMatrix(dimensions: Canvas, currentCanvas: Array[Array[Char]]) {

  override def toString: String = currentCanvas.map(_.mkString).mkString("\n")

}

object CanvasMatrix {

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

