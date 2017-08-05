package canvas.matrix

import canvas.domain.{Canvas, Line, Point, Rectangle}
import org.scalatest.{Matchers, WordSpec}

class CanvasMatrixTest extends WordSpec with Matchers {

  val canvasCommand = Canvas(20, 4)
  val horizontalLineCommand = Line(Point(1, 2), Point(6, 2))
  val verticalLineCommand = Line(Point(6, 3), Point(6, 4))
  val rectangleCommand = Rectangle(Point(16, 1), Point(20, 3))
  
  "CanvasMatrix" when {

    "Canvas command is received" should {
      "create a canvas matrix representation" in {
        val canvas: CanvasMatrix = CanvasMatrix(canvasCommand)
        
        val expected: String = Array(
          "----------------------",
          "|                    |",
          "|                    |",
          "|                    |",
          "|                    |",
          "----------------------"
        ).mkString("\n")

        assert(canvas.toString == expected)
      }

      "Line command is received for a horizontal line" should {
        "draw a line on the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(canvasCommand)
          val line = horizontalLineCommand
          canvas.drawLine(line)
          
          val expected = Array(
            "----------------------",
            "|                    |",
            "|xxxxxx              |",
            "|                    |",
            "|                    |",
            "----------------------"
          ).mkString("\n")

          assert(canvas.toString == expected)
        }
      }

      "Line command is received for a vertical line" should {
        "draw a line on the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(canvasCommand)
          val line = verticalLineCommand
          canvas.drawLine(line)

          val expected = Array(
            "----------------------",
            "|                    |",
            "|                    |",
            "|     x              |",
            "|     x              |",
            "----------------------"
          ).mkString("\n")

          assert(canvas.toString == expected)
        }
      }


      "Line commands are received in sequence" should {
        "draw the 2 lines on the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(canvasCommand)
          canvas.drawLine(horizontalLineCommand)
          canvas.drawLine(verticalLineCommand)
          
          val expected = Array(
            "----------------------",
            "|                    |",
            "|xxxxxx              |",
            "|     x              |",
            "|     x              |",
            "----------------------"
          ).mkString("\n")

          assert(canvas.toString == expected)
        }
      }


      "Rectangle command is received" should {
        "draw the rectangle on the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(canvasCommand)
          canvas.drawRectangle(rectangleCommand)
          
          val expected = Array(
            "----------------------",
            "|               xxxxx|",
            "|               x   x|",
            "|               xxxxx|",
            "|                    |",
            "----------------------"
          ).mkString("\n")

          assert(canvas.toString == expected)
        }
      }


      "Rectangle command is received after 2 line commands" should {
        "draw the shapes on the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(canvasCommand)
          canvas.drawLine(horizontalLineCommand)
          canvas.drawLine(verticalLineCommand)
          canvas.drawRectangle(rectangleCommand)

          val result = Array(
            "----------------------",
            "|               xxxxx|",
            "|xxxxxx         x   x|",
            "|     x         xxxxx|",
            "|     x              |",
            "----------------------"
          ).mkString("\n")

          assert(canvas.toString == result)
        }
      }

    }
  }

}
