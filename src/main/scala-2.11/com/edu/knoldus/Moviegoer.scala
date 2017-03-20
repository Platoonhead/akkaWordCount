package com.edu.knoldus

import akka.actor.{Actor, ActorRef, Props}

class Moviegoer(movieServiceProvider: ActorRef ) extends Actor{

  override def receive = {

    case x:Boolean => if(x) { println("seat booked")
                      sender ! "BookCornerSeat"}
                      else println("Oops! Corner Seat not available in this slot,try later")

    case "BookCornerSeat"  => movieServiceProvider ! "GetCornerSeatStatus"
    case "CancelCornerSeat"  =>println("Corner seat booking canceled") ; movieServiceProvider ! "cancel"
  }

}

object Moviegoer{

  def prop(ref: ActorRef):Props = Props(classOf[Moviegoer],ref)

}