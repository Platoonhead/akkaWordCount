package com.edu.knoldus

import akka.actor.{Actor,Props}

class BookMyShow extends Actor{

  var isCornerSeatAvailable = true

  override def receive = {

    case "BookCornerSeat"      => if(isCornerSeatAvailable){println(s"seat booked") ; isCornerSeatAvailable =false}
                                  else println("Oops! Corner Seat not available in this slot,try later")

    case "cancel"              => println("SUCCESS: seat canceled");isCornerSeatAvailable = true

  }

}

object BookMyShow{

  def prop:Props = Props[BookMyShow]

}




