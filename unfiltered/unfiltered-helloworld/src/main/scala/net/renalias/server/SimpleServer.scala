package net.renalias.server

import unfiltered.response.{Ok, ResponseString}

object SimpleServer {
  def main(args: Array[String]) {
    unfiltered.jetty.Http(8080).filter(unfiltered.filter.Planify {
      case _ => Ok ~> ResponseString("Hello, world!")
    }).run
  }
}