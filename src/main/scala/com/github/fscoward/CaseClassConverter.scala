package com.github.fscoward

case class PUMLClass(packageName: String, className: String)

object CaseClassConverter {
  def convertPUMLClass(classStr: String) = {
    val stringList: Array[String] = classStr.split(System.lineSeparator())
    val packageName = readPackageName(stringList)

    PUMLClass(packageName.getOrElse(""), "todo")
  }

  def readPackageName(source: Array[String]): Option[String] = {
    val r = """(^package) (.*)""".r("package", "name")
    source.find(s => r.matches(s)).flatMap(r.findFirstMatchIn).map(_.group("name"))
  }

  def readClassName(source: Array[String]): Option[String] = {
    val r = """(.*\s|^)(class)\s(.*)(\(.*)""".r("case", "class", "name")
    source.find(s => r.matches(s)).flatMap(r.findFirstMatchIn).map(_.group("name"))
  }
}
