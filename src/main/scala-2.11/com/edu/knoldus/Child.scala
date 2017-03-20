package com.edu.knoldus

import akka.actor.{Actor, Props}

class Child extends Actor{

  override def receive = {
    case "HALT" => sender ! "200"
    case line:String=>   sender ! line.trim.replaceAll(" +"," ").split(" ").length
  }

}

object Child{

  def prop:Props = Props[Child]

}
