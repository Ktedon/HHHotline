package bloks

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import eXul.bloks.app._
import io.circe._

import slinky.core.facade.ReactElement

case class Messages(messages: String*)

@react class HHHComponent extends Component {
  case class Props(changeComponent: (String) => Unit)
  case class State(messageInput: String, num: Int, logo: Long)

  override def initialState: State = State("", 0, scala.util.Random.nextLong)

  var messages: scala.collection.mutable.ArrayBuffer[ReactElement] = scala.collection.mutable.ArrayBuffer()
  val icon = scala.util.Random.nextLong

  private val css = AppCSS

  var wsConnection = {
    new FetchMathsWebSocket(icon,
      {json =>
        val cursor: HCursor = json.hcursor
        (cursor.downField("icon").as[String], cursor.downField("message").as[String]) match {
          case (Right(messageIcon), Right(message)) =>
            println(messageIcon + " : " + icon)
            if (messageIcon.toLong != icon.toLong) {
              MessageComponent(message, messageIcon, "RightMessage") +=: messages
            } else {
              MessageComponent(message, icon.toString, "LeftMessage") +=: messages
            }

          case _ => println("Something wen't wrong parsing the JSON received from the server.")
        }
        println(messages)
      },
      (error =>
        println(error)
      ),
      (close =>
        println(close.reason)
      )
    )
  }


  def render() = {

    div()(
      HomeNavbar { component =>
        props.changeComponent(component)
      },
      div(className := "card border-primary mb-3", style := js.Dynamic.literal(width = "80%", marginLeft = "10%", marginTop = "100px"))(
        h2(className := "card-header")("Homework Help Hotline"),
        div(className := "card-body", style := js.Dynamic.literal(height = "350px", overflow = "scroll"))(
          div(style := js.Dynamic.literal(width = "80%", marginLeft = "10%", marginTop = "100px", id := "messages"))(

            div(className := "someData")(messages.toArray: _*)
          )
        ),
        div(style := js.Dynamic.literal(margin = "30px"))(
          textarea(
            placeholder := "Message Here",
            onChange := { event =>
              val e = event.target.value
              setState(_.copy(messageInput = e))
            },
            className :=
              (if (state.messageInput.filter(_ != '\n').length > 0 && state.messageInput.filter(_ != '\n').length <= 1500)
                "form-control is-valid"
              else
                "form-control is-invalid")
          ),
          (
            if (state.messageInput.filter(_ != '\n').length > 0 && state.messageInput.filter(_ != '\n').length <= 1500)
              div(className := "valid-feedback")("Message is Valid")
            else
              div(className := "invalid-feedback")("Message is Invalid")
          ),
          br(),
          button(
            className :=
              (if (state.messageInput.filter(_ != '\n').length > 0 && state.messageInput.filter(_ != '\n').length <= 1500)
                "btn btn-outline-success"
              else
                "btn btn-outline-danger"),
            onClick := { event =>
              if (state.messageInput.filter(_ != '\n').length > 0 && state.messageInput.filter(_ != '\n').length <= 1500)
                wsConnection.sendMessage(new Request(wsConnection.icon.toString, state.messageInput))
              else
                println("something wen't wrong")
            }
          )("Send Message")
        )
      )
    )
  }
}
