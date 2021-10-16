package bloks

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import eXul.bloks.app._

import io.circe._
import io.circe.parser._

@JSImport("resources/bootstrapfile.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@react class App extends Component {
  type Props = Unit
  case class State(isLoggedIn: Boolean, component: String)

  override def initialState: State = State(false, "Home")
  private val css = AppCSS

  def render() =
    if (state.component == "Enter the HHH")
      HHHComponent(
        componentName => setState(_.copy(component = componentName))
      )
    else
      HomePage(componentName => setState(_.copy(component = componentName)))
}
