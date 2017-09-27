package at.co.sdt.herb.actors.doc.akka.io.architecture

import akka.actor.{ Actor, ActorSystem, Props }

import scala.io.StdIn

case object PrintIt

object PrintMyActorRefActor {
  def props: Props = Props[PrintMyActorRefActor]

  val firstName = "first-actor"
  val secondName = "second-actor"
}

class PrintMyActorRefActor extends Actor {

  override def receive: Receive = {
    case PrintIt =>
      val secondRef = context.actorOf(Props.empty, PrintMyActorRefActor.secondName)
      println(s"Second: $secondRef")
  }
}

object ActorHierarchyExperiments extends App {
  val system = ActorSystem()

  val firstRef = system.actorOf(PrintMyActorRefActor.props, PrintMyActorRefActor.firstName)
  println(s"First: $firstRef")
  firstRef ! PrintIt

  println(">>> Press ENTER to exit <<<")
  try StdIn.readLine()
  finally system.terminate()
}