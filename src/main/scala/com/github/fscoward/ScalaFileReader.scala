package com.github.fscoward

/** https://murashun.jp/blog/20190215-01.html */
class ScalaFileReader(source: String) {
  val lines: Array[String] = source.split(System.lineSeparator())
  def readObjectName: Option[String] = {
    val r = """(^object)\s(\w*)\s??(\{.*)""".r("object", "name")
    lines.find(s => r.matches(s)).flatMap(r.findFirstMatchIn).map(_.group("name"))
  }

  def readPackageName: Option[String] = {
    val r = """(^package)\s(.*)""".r("package", "name")
    lines.find(s => r.matches(s)).flatMap(r.findFirstMatchIn).map(_.group("name"))
  }

  def readClassName: Option[String] = {
    val r = """(^[a-z\[\]]*|^)\s?(class)\s(.*)(\(.*)""".r("case", "class", "name")
    lines.find(s => r.matches(s)).flatMap(r.findFirstMatchIn).map(_.group("name"))
  }
}
