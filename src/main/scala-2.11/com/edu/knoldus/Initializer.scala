package com.edu.knoldus

import akka.actor.ActorSystem
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

object Initializer extends App{


  val people = ConfigFactory.parseString(
    """
      |akka.actor.deployment {
      | /poolRouter {
      |   router = round-robin-pool
      |   nr-of-instances = 4
      | }
      |}
    """.stripMargin
  )

  val system = ActorSystem("Bollywood",people)
  val bookMyShowRef  = system.actorOf(BookMyShow.prop)
  val moviegoer = system.actorOf(FromConfig.props(Moviegoer.prop(bookMyShowRef)),"poolRouter")

  moviegoer ! "BookCornerSeat"
  moviegoer ! "BookCornerSeat"
  moviegoer ! "BookCornerSeat"
  moviegoer ! "CancelCornerSeat"
  moviegoer ! "BookCornerSeat"
  moviegoer ! "BookCornerSeat"
  

}
