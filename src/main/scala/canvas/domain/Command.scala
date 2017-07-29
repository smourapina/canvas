package canvas.domain

sealed trait Command

case class Canvas(width: Int, height: Int) extends Command

case class Line(origin: Point, last: Point) extends Command
case class Rectangle(upperLeftCorner: Point, lowerRightCorner: Point) extends Command

case class BucketFill(area: Point, color: Char) extends Command

case class Point(x: Int, y: Int)
