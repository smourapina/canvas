package canvas.io

import canvas.domain._

import scala.util.{Success, Try}

class InputReader {

  def inputToCommand(input: String): Either[InputError, Command] = input.replaceAll("\\s{2,}", " ").trim.split(" ") match {

    case Array("Q") => Right(Quit())
    case Array("C", width, height) => {
      val wd = convertStringToInt(width)
      val hg = convertStringToInt(height)
      (wd, hg) match {
        case (Success(w), Success(h)) => Right(Canvas(w, h))
        case _ => Left(UnknownCommandError("error: the command does not exist"))
      }

    }
    case Array("L", x1, y1, x2, y2)  => Right(Line(Point(x1.toInt, y1.toInt), Point(x2.toInt, y2.toInt)))
    case Array("R", x1, y1, x2, y2)  => Right(Rectangle(Point(x1.toInt, y1.toInt), Point(x2.toInt, y2.toInt)))
    case Array("B", x, y, c)  => Right(BucketFill(Point(x.toInt, y.toInt), c.charAt(0)))
    case _ => Left(UnknownCommandError("error: the command does not exist"))

  }

  def convertStringToInt(input: String): Try[Int] =  {
  Try {
      input.toInt
    }

  }

}
