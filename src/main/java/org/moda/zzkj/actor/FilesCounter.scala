package org.moda.zzkj.actor

import akka.actor._
import akka.routing.RoundRobinPool

class FilesCounter extends Actor{
  var filesCount = 0L
  var pending = 0

  val fileExplorers: ActorRef = context.actorOf(RoundRobinPool(100).props(Props[FileExplorer]))

  def receive: Receive = {
    // 如果是 文件夹 那就继续 发送给 对应的Actor处理
    case dirName: String =>
      pending = pending + 1
      fileExplorers ! dirName

    case count: Int =>
      filesCount += count
      pending -= 1

      // 说明 结束了
      if (pending == 0)
        println(s"files count => ${filesCount}")

  }
}
