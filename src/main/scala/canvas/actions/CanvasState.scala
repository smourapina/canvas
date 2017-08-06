package canvas.actions

import canvas.matrix.CanvasMatrix

case class CanvasState(previousCanvas: Option[CanvasMatrix], currentCanvas: CanvasMatrix)
