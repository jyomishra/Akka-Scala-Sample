package com.lightbend.akka.sample

import java.time.Instant

import akka.actor.{ActorSystem, Props}

object BasicMainApp extends App {

  override def main(args: Array[String]): Unit = {
    val system = ActorSystem.create("MySystem")
    println("Actor System Created")
    val accumulatorRef = system.actorOf(PrimeAccumulatorFactory.props(), "PrimeAccumulator")
    val pfActorRef = system.actorOf(PrimeFinderFactory.props(accumulatorRef), "PrimeFinder")
    (1 to 250000).foreach( x => pfActorRef ! x)
  }
}
