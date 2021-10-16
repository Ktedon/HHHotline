package bloks

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@react class HomeNavbar extends StatelessComponent {
  case class Props(changeComponent: (String) => Unit)

  private val css = AppCSS


  def render() = {
    nav(className := "navbar navbar-expand-lg navbar-light bg-light")(
      div(className := "container-fluid")(
        a(className := "navbar-brand")(
          img(
            src := "https://www.ewsd.org/cms/lib/VT02217845/Centricity/Template/GlobalAssets/images///Logos/EHS_Hornet.png",
            className := "App-logo",
            alt := "logo",
            onClick := { e => {props.changeComponent("Home")}},
            height := "70px"
          )
        ),
        button(
          className := "navbar-toggler",
          `type` := "button",
          (data - "bs-toggle") := "collapse",
          (data - "bs-target") := "#navbarColor03",
          (aria - "controls") := "navbarColor03",
          (aria - "expanded") := "false",
          (aria - "label") := "Toggle navigation"
        )(span(className := "navbar-toggler-icon")()),
        div(className := "collapse navbar-collapse", id := "navbarColor03")(
          ul(className := "navbar-nav me-auto")(
            li(className := "nav-item")(
              a(className := "nav-link active", href := "#")(
                span(className := "visually-hidden")("(Current)")
              )
            ),
            li(className := "nav-item")(
              a(
                className := "nav-link",
                onClick := { e => {props.changeComponent("Enter the HHH")}}
              )("Enter the HHH")
            )
          )
        )
      )
    )
  }
}
