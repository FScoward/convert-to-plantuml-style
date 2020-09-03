package com.github.fscoward

import org.scalatest.funspec.AnyFunSpec

class CaseClassConverterSpec extends AnyFunSpec {
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
      assert(actual === expected)
    }
    it("#readPackageName") {
      val sample =
        """
                  |package com.github.fscoward
                  |
                  |case class SampleClass(name: String)
                  |""".stripMargin

      val s = sample.split(System.lineSeparator())
      val actual = CaseClassConverter.readPackageName(sample.split(System.lineSeparator()))
      val expected = Some("com.github.fscoward")
      assert(actual === expected)
    }
  }
}
