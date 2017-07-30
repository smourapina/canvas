package canvas.io

import canvas.matrix.CanvasMatrix

class OutputWriter {

  def printCurrentCanvas(canvasMatrix: CanvasMatrix): Unit = {
    Console.println(canvasMatrix)
  }

}
