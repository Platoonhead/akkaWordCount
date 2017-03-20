package com.edu.knoldus

import akka.actor.{Actor,Props}

class BookMyShow extends Actor{

  var isCornerSeatAvailable = true

  override def receive = {

    case "GetCornerSeatStatus" => sender() ! isCornerSeatAvailable
    case "BookCornerSeat"      => isCornerSeatAvailable = false
    case "cancel"              => isCornerSeatAvailable = true

  }

}

object BookMyShow{

  def prop:Props = Props[BookMyShow]

}


