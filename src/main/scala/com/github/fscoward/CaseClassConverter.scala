package com.github.fscoward

case class PUMLClass(packageName: String, className: String)

object CaseClassConverter {
  def convertPUMLClass(classStr: String): PUMLClass = {
    val scalaFileReader = new ScalaFileReader(classStr)
    val packageName = scalaFileReader.readPackageName
    val className = scalaFileReader.readClassName

    PUMLClass(packageName.getOrElse(""), className.getOrElse(""))
  }
}
