package com.reactivebbq.customers

//#quick-00start-server
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.RouteConcatenation._
import akka.management.scaladsl.AkkaManagement
import akka.stream.ActorMaterializer
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.lightbend.cinnamon.scala.future.named._
import scala.concurrent.Future
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.util.Success
import scala.util.Failure


//#main-class
object CustomerAppFut extends App {

  println("I'm up ")

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

  def factorial(n: Int): BigInt = (BigInt(1) to BigInt(n)).product

  val f1 = FutureNamed("Factorial.100000"){
    factorial(100000)
  }

  f1.onComplete{
    case Success(value) => println(value.toString().substring(0,100))
    case Failure(ex) => println(ex)
  }

}
