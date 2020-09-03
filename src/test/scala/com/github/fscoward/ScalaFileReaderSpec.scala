package com.github.fscoward

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers._

import org.scalatest.prop.TableDrivenPropertyChecks._

class ScalaFileReaderSpec extends AnyFunSpec with should.Matchers {
  describe("ScalaFileReader") {
    it("#readPackageName") {
      val sample = """package com.github.fscoward
                     |
                     |case class SampleClass(name: String)
                     |""".stripMargin

      val actual = ScalaFileReader.readPackageName(sample.split(System.lineSeparator()))
      val expected = Some("com.github.fscoward")
      actual shouldEqual expected
    }
    describe("#readClassName") {
      val testCase = Table(
        ("case name", "class"),
        ("class", "class"),
        ("case class", "case class"),
        ("abstract class", "abstract class"),
        ("sealed class", "sealed class"),
        ("final class", "final class"),
        ("protected case name", "protected class"),
        ("protected[this] case name", "protected[this] class")
      )
      forAll(testCase) { (caseName, `class`) =>
        it(caseName) {
          val sample = s"""package com.github.fscoward
                          |
                          |${`class`} SampleClass(name: String)
                          |""".stripMargin

          val actual = ScalaFileReader.readClassName(sample.split(System.lineSeparator()))
          val expected = Some("SampleClass")
          actual shouldEqual expected
        }
      }

      it("コメントアウトされている") {
        val sample = """package com.github.fscoward
                       |//class Sample(name: String)
                       |// class Sample(name: String)
                       |/* class Sample(name: String) */
                       |/*
                       | * class Sample(name: String)
                       |**/
                       |class Amazing(name: String)
                       |""".stripMargin

        val actual = ScalaFileReader.readClassName(sample.split(System.lineSeparator()))
        val expected = Some("Amazing")
        actual shouldEqual expected
      }
    }
    describe("#readObjectName") {
      it("object名の後ろに空白あり") {

        val testData = """package com.github.fscoward
                     |
                     |object Sample {
                     |}
                     |""".stripMargin

        val actual = ScalaFileReader.readObjectName(testData.split(System.lineSeparator()))
        val expected = Some("Sample")
        actual shouldEqual expected
      }
      it("object名の後ろに空白なし") {

        val testData = """package com.github.fscoward
                         |
                         |object Sample{
                         |}
                         |""".stripMargin

        val actual = ScalaFileReader.readObjectName(testData.split(System.lineSeparator()))
        val expected = Some("Sample")
        actual shouldEqual expected
      }
    }
  }

}
