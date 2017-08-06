package canvas.util

import canvas.domain._

trait DomainFixture {

  val canvasCommand = Canvas(20, 4)
  val horizontalLineCommand = Line(Point(1, 2), Point(6, 2))
  val verticalLineCommand = Line(Point(6, 3), Point(6, 4))
  val rectangleCommand = Rectangle(Point(16, 1), Point(20, 3))
  val bucketFillCommand = BucketFill(Point(10, 3), 'o')

}
