package net.renalias.server

import unfiltered.request.{Accepts, GET, Path, Seg}
import unfiltered.response.{NotFound, Ok, ResponseString}

object SimpleServer {
	def main(args: Array[String]) {
		unfiltered.jetty.Http(8080).filter(unfiltered.filter.Planify {
			case GET(Path(Seg("hello" :: message :: Nil))) => Ok ~> ResponseString(message)
			case _ => Ok ~> ResponseString("Use /hello/<text>")
		}).run
	}
}