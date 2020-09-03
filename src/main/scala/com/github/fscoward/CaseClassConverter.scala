package com.github.fscoward

case class PUMLClass(packageName: String, className: String)

object CaseClassConverter {
  def convertPUMLClass(classStr: String) = {
    val stringList: Array[String] = classStr.split(System.lineSeparator())
    val packageName = stringList.find(_.matches("^package"))

    PUMLClass("com.github.fscoward", "todo")
  }

  def readPackageName(source: Array[String]): Option[String] = {
    val r = """(^package) (.*)""".r("package", "name")
    source.headOption.flatMap(r.findFirstMatchIn).map(_.group("name"))
  }
}
