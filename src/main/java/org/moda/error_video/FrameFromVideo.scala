package org.moda.error_video

import java.awt.image.BufferedImage
import java.io.File

import com.xuggle.mediatool.event.IVideoPictureEvent
import com.xuggle.mediatool.{IMediaReader, MediaListenerAdapter, ToolFactory}
import javax.imageio.ImageIO

import scala.util.Random

object FrameFromVideo  {

  def extractFrameFromVideo(videoPath: String, framePath: String): (String, String) = {
    var count = 0
    // Call the reader from "xuggle.mediatool.ToolFactory"
    val mediaReader: IMediaReader = ToolFactory.makeReader(videoPath)
    val destPath: String = s"$framePath${System.currentTimeMillis() + Random.nextLong()}.jpg"
    // Setting buffered image type
    mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR)
    mediaReader.addListener(new ImageSnapListener)
    while (count < 1) {
      mediaReader.readPacket()
    }
    class ImageSnapListener extends MediaListenerAdapter {
      override def onVideoPicture(event: IVideoPictureEvent): Unit = {
        dumpImageToFile(event.getImage)
        count = count + 1
      }
      def dumpImageToFile(image: BufferedImage): Unit = {
        ImageIO.write(image, "jpg", new File(destPath))
      }
    }
    (destPath, "123223123123")
  }

  def main(args: Array[String]): Unit = {
    extractFrameFromVideo("/home/yangxuan/Desktop/error_video/24f1a6e8658238e6db49cd6257babb22.mp4",
    "/home/yangxuan/Desktop/error_video/screen/")
  }
}