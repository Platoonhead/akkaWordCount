package com.edu.knoldus

import akka.actor.ActorSystem
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

object Initializer extends App{


  val people = ConfigFactory.parseString(  // single screen theater,nr-of-instances = 1 ;)
    """
      |akka.actor.deployment {
      | /poolRouter {
      |   router = balancing-pool
      |   nr-of-instances = 4
      | }
      |}
    """.stripMargin
  )

  val system = ActorSystem("Bollywood",people)
  val bookMyShowRef  = system.actorOf(BookMyShow.prop)
  val moviegoer = system.actorOf(FromConfig.props(Moviegoer.prop(bookMyShowRef)),"poolRouter")

  moviegoer ! "BookCornerSeat"
  Thread.sleep(1000)
  moviegoer ! "BookCornerSeat"
  Thread.sleep(1000)
  moviegoer ! "BookCornerSeat"
  Thread.sleep(1000)
  moviegoer ! "CancelCornerSeat"
  Thread.sleep(1000)
  moviegoer ! "BookCornerSeat"
  Thread.sleep(1000)
  moviegoer ! "BookCornerSeat"




}
