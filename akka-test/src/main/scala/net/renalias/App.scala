import se.scalablesolutions.akka.actor.Actor
import se.scalablesolutions.akka.remote.{RemoteClient, RemoteNode}
import se.scalablesolutions.akka.util.Logging
import Actor._

case class EchoMessage(val msg:String)
case class EchoResponse(val msg:String)
 
class HelloWorldActor extends Actor {
  def receive = {
    case "Hello" => self.reply("World")
	case EchoMessage(msg) => self.reply(EchoResponse("echo: " + msg))
  }
}
 
object ServerInitiatedRemoteActorServer {
 
  def run = {
    RemoteNode.start("localhost", 4444)
    RemoteNode.register("hello-service", actorOf[HelloWorldActor])
  }
 
  def main(args: Array[String]) = run
}

object RemoteActorClient {
	def main(args: Array[String]) = {

		val actor = RemoteClient.actorFor("hello-service", "localhost", 4444)
		val response = actor !! "Hello"
		println("The response from the server was = " + response.getOrElse("no response"))
		
		val response2 = actor !! EchoMessage("Hello, world")
		println(response2)
	}
}