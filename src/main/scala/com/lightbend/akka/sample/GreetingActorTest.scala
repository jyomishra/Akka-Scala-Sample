package com.lightbend.akka.sample

import akka.actor.{ActorSystem, Props}

object GreetingActorTest {
  def main(args: Array[String]) = {
    val actorSystem = ActorSystem.create("MySystem")
    val gaRef = actorSystem.actorOf(Props[GreetingActor],"greetingActor")
    gaRef tell (Greeting("Jyotirmay"), null)
    gaRef ! Greeting("Jyotirmay")
    gaRef ! SendOf
    gaRef ! Greeting("Jyotirmay")
    gaRef ! Greeting("ABC")
    gaRef ! Greet
    gaRef ! Greeting("ABC")
  }
}
