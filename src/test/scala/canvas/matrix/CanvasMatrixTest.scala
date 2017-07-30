package canvas.matrix

import canvas.domain.{Canvas, Line, Point}
import org.scalatest.{Matchers, WordSpec}

class CanvasMatrixTest extends WordSpec with Matchers {

  //TODO match drawing results to resulting strings (asserts)

  "CanvasMatrix" when {

    "Canvas command is received" should {
      "create a canvas matrix representation" in {
        val canvas: CanvasMatrix = CanvasMatrix(Canvas(20, 4))
        val result = Array("\n",
          "----------------------",
          "|                    |",
          "|                    |",
          "|                    |",
          "|                    |",
          "----------------------"
          ).mkString("\n")

        println(result)
        //assert(canvas == result)
      }

      "Line command is received for a horizontal line" should {
        "draw a line on the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(Canvas(20, 4))
          val line = Line(Point(1, 2), Point(6, 2))
          val drawResult = canvas.drawLine(line)
          val result = Array("\n",
            "----------------------",
            "| x                  |",
            "| x                  |",
            "| x                  |",
            "|                    |",
            "----------------------"
          ).mkString("\n")

          drawResult match { case Right(c) => println(c)}
          //assert(canvas == result)
        }
      }

      "Line command is received for a vertical line" should {
        "draw a line on the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(Canvas(20, 4))
          val line = Line(Point(6, 3), Point(6, 4))
          val drawResult = canvas.drawLine(line)

          drawResult match { case Right(c) => println(c)}
          //assert(canvas == result)
        }
      }


      "Line commands are received in sequence" should {
        "draw the 2 lines on the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(Canvas(20, 4))
          val hor = Line(Point(1, 2), Point(6, 2))
          canvas.drawLine(hor)
          val vert = Line(Point(6, 3), Point(6, 4))
          val drawResult = canvas.drawLine(vert)

          drawResult match { case Right(c) => println(c)}
          //assert(canvas == result)
        }
      }


    }
  }

}
