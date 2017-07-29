package canvas

import org.scalatest._

/*
  This class contains acceptance tests for the Canvas application.
 */
class CanvasApplicationSpec extends FeatureSpec with Matchers {

  feature("Canvas command") {
    scenario("should draw canvas with correct dimensions") {
      //tbd
    }

    scenario("should ask the user to re-enter command if input values are invalid") {
      //tbd
    }
  }

  feature("Draw line command") {
    scenario("should draw a line correctly within the canvas") {
      //tbd
    }

    scenario("should ask the user to re-enter command if input values are invalid") {
      //tbd
    }
  }

  feature("Draw rectangle command") {
    scenario("should draw a rectangle within the canvas with correct dimensions") {
      //tbd
    }

    scenario("should ask the user to re-enter command if input values are invalid") {
      //tbd
    }
  }

  feature("Bucket fill command") {
    scenario("should fill a delimited shape within the canvas correctl  y, with the specified color") {
      //tbd
    }

    scenario("should ask the user to re-enter command if input values are invalid") {
      //tbd
    }
  }

}
