package com.lightbend.akka.sample

import java.time.Instant

import akka.actor.{Actor, ActorLogging, Props, Timers}

import scala.concurrent.duration.DurationLong

class PrimeAccumulator extends Actor with ActorLogging with Timers{
  var startTime = Instant.now.toEpochMilli
  timers.startPeriodicTimer("test", PrintSum, 2.second)

  var oldCounter : Long = -1
  var sum : Long = 0
  var counter : Long = 0
  println("Actor created : " + self.path)
  override def receive = {
    case x:Int => {
      sum = sum + x
      counter = counter + 1
    }
    case PrintSum => {
      if(oldCounter == counter)
        self ! StopTimer
      else
        println( "Sum = " + sum + " at " + counter + " count & " + "time taken " + (Instant.now.toEpochMilli - startTime)/1000 + " seconds")
        oldCounter = counter
    }
    case StopTimer => {
      timers.cancel("test")
      println( "Sum = " + sum + " at " + counter + " count & " + "time taken " + (Instant.now.toEpochMilli - startTime).toDouble/1000 + " seconds")
    }
    case _ => { println("Encountered wrong input")}
  }

  def printIfEnd:Unit ={
    if(counter % 2000 == 0) {
      println("time taken " + (Instant.now.toEpochMilli - startTime))
    }
    counter = counter + 1
  }
}

case  class PrintSum()
case class StopTimer()

object PrimeAccumulatorFactory {
  def props() = Props(new PrimeAccumulator())
}