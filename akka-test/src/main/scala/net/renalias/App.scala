import se.scalablesolutions.akka.actor.Actor
import se.scalablesolutions.akka.remote.{RemoteClient, RemoteNode}
import se.scalablesolutions.akka.util.Logging
import Actor._
 
class HelloWorldActor extends Actor {
  def receive = {
    case "Hello" => self.reply("World")
  }
}
 
object ServerInitiatedRemoteActorServer {
 
  def run = {
    RemoteNode.start("localhost", 9999)
    RemoteNode.register("hello-service", actorOf[HelloWorldActor])
  }
 
  def main(args: Array[String]) = run
}

object RemoteActorClient {
	def main(args: Array[String]) = {

		val actor = RemoteClient.actorFor("hello-service", "localhost", 9999)
		val response = actor !! "Hello"
		println("The response from the server was = " + response.getOrElse("no response"))
	}
}