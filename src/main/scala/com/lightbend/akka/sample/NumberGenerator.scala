package com.lightbend.akka.sample

import akka.actor.{Actor, ActorRef, Props}

class NumberGenerator(router: ActorRef) extends Actor{
  override def receive = {
    case (start:Int, end:Int) => {
      println("Receive Numbers Start : " + start + " end : " + end)
      (start to end).foreach( x => router ! x)
    }
    case _ => println("Wrong Input")
  }
}

object NumberGeneratorFactory {
  def props(router: ActorRef) = Props(new NumberGenerator(router))
}