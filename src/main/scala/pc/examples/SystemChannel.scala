package pc.examples

import pc.modelling.System

object SystemChannel:
  // enabling analysis
  export pc.modelling.SystemAnalysis.*

  // Specification of my data-type for states
  enum State:
    case IDLE,SEND,DONE,FAIL
  export State.*

  // System specification
  def channel: System[State] = System.ofTransitions(
    IDLE->SEND,
    SEND->SEND,
    SEND->DONE,
    SEND->FAIL,
    FAIL->IDLE //,DONE->DONE
  )

@main def mainSystemChannel() =
  import SystemChannel.*
  // Analysis, by querying
  println(channel.normalForm(IDLE))
  println(channel.normalForm(DONE))
  println(channel.next(IDLE))
  println(channel.next(SEND))
  println("P1  "+channel.paths(IDLE,1).toList)
  println("P2  "+channel.paths(IDLE,2).toList)
  println("P3  "+channel.paths(IDLE,3).toList)
  println("P4  "+channel.paths(IDLE,4).toList)
  println("CMP:\n"+channel.completePaths(IDLE).take(100).toList.mkString("\n"))