package com.edu.knoldus

import akka.actor.{Actor, ActorRef, Props}

import scala.io.Source

class Parent extends Actor {

  val childRef: ActorRef = context.actorOf(Child.prop)
  var totalWords = 0
  var caller:Option[ActorRef] = None

  override def receive = {
    case "200"       => caller.foreach(x=>x ! s"$totalWords Words Found")
    case "404"       => caller.foreach(x=>x ! "Oops! FILE NOT FOUND")
    case path:String => caller = Some(sender)
                        try   {for (line <- Source.fromFile(path).getLines()) childRef ! line
                              childRef ! "HALT"}
                        catch {case e:Exception => self ! "404"}
    case wordsPerLine:Int => totalWords+=wordsPerLine
  }

}
object Parent{

def props:Props = Props[Parent]

}



