package bloks

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

class MessageDirection

case object RightMessage extends MessageDirection
case object LeftMessage extends MessageDirection

@react class MessageComponent extends StatelessComponent {
  case class Props(messageText: String, icon: String, direction: String)

  private val css = AppCSS


  def render() = {
    props.direction match {
      case "RightMessage" =>
        div(style := js.Dynamic.literal(marginBottom = "30px"))(
          div(style := js.Dynamic.literal(display = "flex", justifyContent = "left"))(
            div(style := js.Dynamic.literal(width = "100%"))(
              div(className := "card border-primary mb-3 bg-dark")(
                div(className := "card-title")(
                  div(style := js.Dynamic.literal(margin = "30px", color = "azure"))(
                    b()(props.messageText)
                  )
                )
              )
            ),
            div(style := js.Dynamic.literal(marginLeft = "30px"))(
              img(
                src := s"https://avatars.dicebear.com/api/big-smile/${props.icon}.svg",
                className := "App-logo",
                alt := "profile",
                style := js.Dynamic.literal(
                  boxShadow = "4px 3px 8px 1px #969696",
                  width = "50px",
                  marginLeft = "15%",
                  marginTop = "-30px",
                  backgroundColor = "#f2f3f4",
                  borderRadius = "5%"
                )
              )
            )
          )
        )

      case "LeftMessage" =>
        div(style := js.Dynamic.literal(marginBottom = "30px"))(
          div(style := js.Dynamic.literal(display = "flex", justifyContent = "left"))(
            div()(
              img(
                src := s"https://avatars.dicebear.com/api/big-smile/${props.icon}.svg",
                className := "App-logo",
                alt := "profile",
                style := js.Dynamic.literal(
                  boxShadow = "4px 3px 8px 1px #969696",
                  width = "50px",
                  marginLeft = "15%",
                  marginTop = "-30px",
                  backgroundColor = "#f2f3f4",
                  borderRadius = "5%"
                )
              )
            ),
            div(style := js.Dynamic.literal(marginLeft = "40px", width = "100%"))(
              div(className := "card border-primary mb-3 bg-warning")(
                div(className := "card-title")(
                  div(style := js.Dynamic.literal(margin = "30px", color = "black"))(
                    b()(props.messageText)
                  )
                )
              )
            )
          )
        )
    }
  }
}
