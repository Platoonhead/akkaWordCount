package com.edu.knoldus

import akka.actor.{Actor, ActorRef, Props}

class Moviegoer(movieServiceProvider: ActorRef ) extends Actor{

  override def receive = {

    case "BookCornerSeat"  => movieServiceProvider ! "BookCornerSeat"
    case "CancelCornerSeat"  =>movieServiceProvider ! "cancel"

  }

}

object Moviegoer{

  def prop(ref: ActorRef):Props = Props(classOf[Moviegoer],ref)

}
