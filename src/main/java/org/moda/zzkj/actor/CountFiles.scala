package org.moda.zzkj.actor

import akka.actor._
import akka.stream.ActorMaterializer

object CountFiles {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("sample")

    val filesCounter = system.actorOf(Props[FilesCounter])

    filesCounter ! "/media/fubeixian/_dde_data/MODA-CODE/scala/scala-study/src/main/java/org/moda"
  }
}
