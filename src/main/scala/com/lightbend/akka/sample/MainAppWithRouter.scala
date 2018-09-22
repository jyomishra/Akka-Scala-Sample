package com.lightbend.akka.sample

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.RoundRobinPool

object MainAppWithRouter extends App {

  override def main(args: Array[String]): Unit = {
    val system = ActorSystem()
    println("Actor System Created")
    val accumulatorRef = system.actorOf(PrimeAccumulatorFactory.props(), "PrimeAccumulator")
    //val pfActorRef = system.actorOf(PrimeFinderFactory.props(accumulatorRef), "PrimeFinder")
    val routerRef = system.actorOf(RoundRobinPool(5).props(PrimeFinderFactory.props(accumulatorRef)), "router2")
    (1 to 250000).foreach( x => routerRef ! x)
  }
}
