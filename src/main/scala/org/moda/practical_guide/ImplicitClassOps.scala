package org.moda.practical_guide

object DateUtil {
  val ago = "ago"
  val from_now = "from_now"

  implicit class DateHelper(val offset: Int) extends AnyVal {
    import java.time.LocalDate
    def days(when: String) = {
      val today = LocalDate.now
      when match {
        case "ago" => today.minusDays(offset)
        case "from_now" => today.plusDays(offset)
        case _ => today
      }
    }
  }
}

object ImplicitClassOps {
  import DateUtil._
  def main(args: Array[String]): Unit = {
    println(2 days ago)
  }
}
