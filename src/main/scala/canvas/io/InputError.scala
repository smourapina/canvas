package canvas.io

sealed trait InputError

case class UnknownCommandError(errorMessage: String) extends InputError
