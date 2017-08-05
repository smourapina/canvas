package canvas.actions

import canvas.domain.{Command, Quit}

class CanvasActions {

  def commandToCanvasAction(command: Command): Unit = command match {
    case _: Quit => System.exit(0)
    //case c: Canvas => new CanvasMatrix(c)
  }

}

object CanvasS {

}