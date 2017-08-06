package canvas.matrix

import canvas.domain._
import org.scalatest.{Matchers, WordSpec}

class CanvasMatrixTest extends WordSpec with Matchers with CanvasOperations {

  val canvasCommand = Canvas(20, 4)
  val horizontalLineCommand = Line(Point(1, 2), Point(6, 2))
  val verticalLineCommand = Line(Point(6, 3), Point(6, 4))
  val rectangleCommand = Rectangle(Point(16, 1), Point(20, 3))
  val bucketFillCommand = BucketFill(Point(10, 3), 'o')

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
          drawLine(canvas, line)
          
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
          drawLine(canvas, line)

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
          drawLine(canvas, horizontalLineCommand)
          drawLine(canvas, verticalLineCommand)
          
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
          drawRectangle(canvas, rectangleCommand)
          
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
          drawLine(canvas, horizontalLineCommand)
          drawLine(canvas, verticalLineCommand)
          drawRectangle(canvas, rectangleCommand)

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

      "Bucket fill command is received" should {
        "fill the specified area in the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(canvasCommand)
          bucketFill(canvas, bucketFillCommand)

          val result = Array(
            "----------------------",
            "|oooooooooooooooooooo|",
            "|oooooooooooooooooooo|",
            "|oooooooooooooooooooo|",
            "|oooooooooooooooooooo|",
            "----------------------"
          ).mkString("\n")

          assert(canvas.toString == result)
        }
      }

      "Bucket fill command is received after 2 line commands and 1 rectangle commands" should {
        "fill the specified area in the canvas" in {
          val canvas: CanvasMatrix = CanvasMatrix(canvasCommand)
          drawLine(canvas, horizontalLineCommand)
          drawLine(canvas, verticalLineCommand)
          drawRectangle(canvas, rectangleCommand)
          bucketFill(canvas, bucketFillCommand)

          val result = Array(
            "----------------------",
            "|oooooooooooooooxxxxx|",
            "|xxxxxxooooooooox   x|",
            "|     xoooooooooxxxxx|",
            "|     xoooooooooooooo|",
            "----------------------"
          ).mkString("\n")

          assert(canvas.toString == result)
        }
      }

    }
  }

}
