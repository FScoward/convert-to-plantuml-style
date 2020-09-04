package com.github.fscoward

trait IScalaFileReader {
  val source: String
  def readPackageName: String
  def readClassName: String
}

object ScalaFileReaderFactory {
  def of(source: String): IScalaFileReader = ???
}

class ObjectFileReader(val source: String) extends IScalaFileReader {
  override def readPackageName: String = ???
  override def readClassName: String = ???
}
/**
  * https://qiita.com/suin/items/35bc4afe618cb77f80f6
  * https://qiita.com/JunSuzukiJapan/items/14b504ac304d420abaf2
  *
  * https://www.scala-lang.org/files/archive/spec/2.11/05-classes-and-objects.html
  * Expr1 ::=`class `
  * Body ::=
  * */
