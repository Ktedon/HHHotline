package bloks

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@react class HomePage extends StatelessComponent {
  case class Props(changeComponent: (String) => Unit)
  private val css = AppCSS

  def render() = {

    div()(
      HomeNavbar { component =>
        props.changeComponent(component)
      },
      div(className := "container", style := js.Dynamic.literal(marginTop = "100px"))(
        div(className := "card border-primary mb-3")(
          div(className := "card-header")(h2()("Welcome to the HHH (Homework Help Hotline)")),
          div(className := "card-title")(
            div(style := js.Dynamic.literal(padding = "30px"))(
              h5()(
                "The HHHotline is a student run website for homework help dedicated to absolute free speech, free access, and free thought.",
                br(),
                br(),
                "This means there are no accounts, no advertisements, and no paywalls.",
                br(),
                br(),
                "The HHHotline is run and maintained by Bradly Ovitt. A sophomore at the essex high school."
              )
            )
          )
        )
      )
    )
  }
}
