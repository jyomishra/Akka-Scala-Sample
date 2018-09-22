package com.lightbend.akka.sample
import akka.actor.Actor

case class Greeting(who:String)
case class SendOf()
case class Greet()

class GreetingActor extends Actor{
  override def receive = greet

  val greet:Receive= {
    case Greeting(who) => println("Welcome ", who)
    case SendOf => context.become(sendOf)
    case _ => println("Unknown Message")
  }

  val sendOf:Receive= {
    case Greeting(who) => println("Bye ", who)
    case Greet => context.become(greet)
    case _ => println("Unknown Message")
  }
}
