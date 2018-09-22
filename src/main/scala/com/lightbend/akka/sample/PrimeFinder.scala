package com.lightbend.akka.sample

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

class PrimeFinder(pa: ActorRef) extends Actor with ActorLogging{
  println("Actor created : " + self.path)
  def receive = {
    case x:Int => if(isPrime(x)) pa ! x
    case _ => println("Unrecognize type.")
  }

  def isPrime(i :Int) : Boolean = {
      if (i <= 1)
         false
      else if (i == 2)
         true
      else
        !(2 to (i-1)).exists(x => i % x == 0)
  }
}

object PrimeFinderFactory {
  def props(pa:ActorRef) = Props(new PrimeFinder(pa))
}