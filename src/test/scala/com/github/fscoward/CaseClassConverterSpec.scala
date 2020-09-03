package com.github.fscoward

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers._

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
  }
}
