package com.edu.knoldus

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import scala.util.{Failure, Success}


object Initializer extends App{

    val system = ActorSystem("WordCountEnvironment")
    val parentProp = Parent.props
    val parentRef  = system.actorOf(parentProp)

    val filePath = "DemoFile.txt"
    implicit val timeOut = Timeout(3000 seconds)
    val wordProcessing = parentRef ? filePath

    wordProcessing onComplete {
        case Success(says) =>println(s"$says")
        case Failure(msg) =>println("failed due to: " + msg)
    }

    Thread sleep 2000
    system.terminate

}
