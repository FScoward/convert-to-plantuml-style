package com.github.fscoward

object ScalaFileReader {
  def readPackageName(source: Array[String]): Option[String] = {
    val r = """(^package) (.*)""".r("package", "name")
    source.find(s => r.matches(s)).flatMap(r.findFirstMatchIn).map(_.group("name"))
  }

  def readClassName(source: Array[String]): Option[String] = {
    val r = """(^[a-z\[\]]*|^)\s?(class)\s(.*)(\(.*)""".r("case", "class", "name")
    source.find(s => r.matches(s)).flatMap(r.findFirstMatchIn).map(_.group("name"))
  }
}
