package canvas.io

import canvas.domain.Command

trait InputValidation {

  def validateInput(command: Command): Either[InputError, Command] = ???

}


sealed trait InputError

case class IncorrectCommandError(errorMessage: String) extends InputError