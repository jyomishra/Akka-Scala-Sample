package com.lightbend.akka.sample

import akka.actor.ActorSystem
import akka.routing.RoundRobinPool

object MainAppWithNumberGenerator extends App {

  override def main(args: Array[String]): Unit = {
    val system = ActorSystem()
    println("Actor System Created")
    val accumulatorRef = system.actorOf(PrimeAccumulatorFactory.props(), "PrimeAccumulator")
    //val pfActorRef = system.actorOf(PrimeFinderFactory.props(accumulatorRef), "PrimeFinder")
    val routerRef = system.actorOf(RoundRobinPool(5).props(PrimeFinderFactory.props(accumulatorRef)), "router2")
    val numbergenref = system.actorOf(RoundRobinPool(5).props(NumberGeneratorFactory.props(routerRef)));
    (0 to 24).foreach(x => numbergenref ! (x*10000+1, (x+1)*10000))
  }
}
