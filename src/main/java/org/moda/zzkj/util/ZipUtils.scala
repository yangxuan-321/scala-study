package org.moda.zzkj.util

import java.io.{BufferedInputStream, ByteArrayOutputStream, File, FileInputStream, FileOutputStream, OutputStream}
import java.util.zip.{ZipEntry, ZipOutputStream}



object ZipUtils {
  val buf: Array[Byte] = new Array[Byte](4096)

  def zipFileNew(files: Seq[File]): Array[Byte] = {
    val zipBos: ByteArrayOutputStream = new ByteArrayOutputStream()
    val zipOut: ZipOutputStream = new ZipOutputStream(zipBos)
    files.foreach(c => {
      val ze: ZipEntry = new ZipEntry(c.getName)
      zipOut.putNextEntry(ze)
      val fileBytes = readFilesByte(c)
      zipOut.write(fileBytes)
    })
    zipOut.flush()
    zipBos.flush()
    zipOut.close()
    zipBos.close()
    zipBos.toByteArray
  }

  def readFilesByte(file: File): Array[Byte] = {
    val bos: ByteArrayOutputStream = new ByteArrayOutputStream()
    val fis = new FileInputStream(file)
    val bis: BufferedInputStream = new BufferedInputStream(fis)
    while (bis.read(buf) >= 1) {
      bos.write(buf, 0, buf.length)
    }
    bos.flush()
    bos.close()
    bos.toByteArray
  }

  def main(args: Array[String]): Unit = {
    val bytes = zipFileNew(Array("/home/yangxuan/Desktop/学习/zzkj-study/doc/PubApi.md",
      "/home/yangxuan/Desktop/学习/zzkj-study/doc/Kafka.xmind").map(new File(_)))
    val fos: FileOutputStream = new FileOutputStream(new File("/opt/MODA/MODA-BAK/1.zip"))
    fos.write(bytes)
  }
}































