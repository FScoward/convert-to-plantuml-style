package com.github.fscoward

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class CaseClassConverterSpec extends AnyFunSpec with should.Matchers {
  describe("CaseClassConverter") {
    it("#convert") {
      val sample =
        """
                  |package com.github.fscoward
                  |
                  |case class SampleClass(name: String)
                  |""".stripMargin

      val actual = CaseClassConverter.convertPUMLClass(sample)
      val expected = PUMLClass("com.github.fscoward", "SampleClass")
      actual shouldEqual expected
    }
    it("#readPackageName") {
      val sample = """package com.github.fscoward
                  |
                  |case class SampleClass(name: String)
                  |""".stripMargin

      val s = sample.split(System.lineSeparator())
      val actual = CaseClassConverter.readPackageName(sample.split(System.lineSeparator()))
      val expected = Some("com.github.fscoward")
      actual shouldEqual expected
    }
    describe("#readClassName") {
      val testCase = Table(
        ("case name", "class"),
        ("case class", "case class"),
        ("abstract class", "abstract class"),
        ("sealed class", "sealed class"),
        ("final class", "final class"),
        ("protected case name", "class"),
        ("protected[this] case name", "class")
      )
      forAll(testCase) { (caseName, `class`) =>
        it(caseName) {
          val sample = s"""package com.github.fscoward
                      |
                      |${`class`} SampleClass(name: String)
                      |""".stripMargin

          val s = sample.split(System.lineSeparator())

          val actual = CaseClassConverter.readClassName(sample.split(System.lineSeparator()))
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
        |class Amazing(naem: String)
        |""".stripMargin
        val s = sample.split(System.lineSeparator())

        val actual = CaseClassConverter.readClassName(sample.split(System.lineSeparator()))
        val expected = Some("Amazing")
        actual shouldEqual expected
      }
    }
  }
}
