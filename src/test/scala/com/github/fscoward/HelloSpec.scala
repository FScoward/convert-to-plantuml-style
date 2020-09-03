package com.github.fscoward

import org.scalatest.funspec.AnyFunSpec

class HelloSpec extends AnyFunSpec {
  describe("Hello#greeting") {
    it("return hello") {
      assert(Hello.greeting == "hello")
    }
  }
}
