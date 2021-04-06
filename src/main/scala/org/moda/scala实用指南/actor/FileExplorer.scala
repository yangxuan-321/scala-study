package org.moda.scala实用指南.actor

import java.io.File

import akka.actor.Actor

class FileExplorer extends Actor{
  def receive: Receive = {
    case dirName: String => {
      val file = new File(dirName)
      val children = file.listFiles()
      var fileCount = 0

      if (children != null){
        children filter {_.isDirectory} foreach {sender ! _.getAbsolutePath}
        fileCount = children count {!_.isDirectory}
      }

      sender ! fileCount
    }
  }
}
