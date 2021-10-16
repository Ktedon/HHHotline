package eXul.bloks.app

import org.scalajs.dom.raw.{WebSocket, Console, CloseEvent, Event}

import io.circe._
import io.circe.parser._

case class Request(
    icon: String,
    message: String
) {
  def getJson: Json = Json.obj(
    ("icon", Json.fromString(this.icon)),
    ("message", Json.fromString(this.message))
  )
}

// class for dealing with information to and from the server via a websocket.
class FetchMathsWebSocket(
    inIcon: Long,
    onMessage: (Json) => Unit,
    onError: (Event) => Unit,
    onClose: (CloseEvent) => Unit
) {
  // generates icon seed for user.
  val icon: Long = this.inIcon
  def getIcon = icon

  // creates new websocket connection.
  val socket = new WebSocket("ws://localhost:9000/MathsSocket")

  // socket events:
  socket.onopen = (event => socket.send(new Request(icon.toString, "New User Connected!").getJson.toString))
  socket.onerror = (event => this.onError(event))
  socket.onclose = (closeEvent => this.onClose(closeEvent))
  socket.onmessage = { messageEvent =>
    this.onMessage(getJson(messageEvent.data.toString))
  }

  private def getJson(message: String): Json = {
    parse(message).getOrElse(Json.Null)
  }

  def sendMessage(message: Request): Unit = {
  	socket.send(message.getJson.toString)
  }
}
