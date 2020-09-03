package com.github.fscoward

case class PUMLClass(packageName: String, className: String)

object CaseClassConverter {
  def convertPUMLClass(classStr: String): PUMLClass = {
    val stringList: Array[String] = classStr.split(System.lineSeparator())
    val packageName = ScalaFileReader.readPackageName(stringList)
    val className = ScalaFileReader.readClassName(stringList)

    PUMLClass(packageName.getOrElse(""), className.getOrElse(""))
  }
}
