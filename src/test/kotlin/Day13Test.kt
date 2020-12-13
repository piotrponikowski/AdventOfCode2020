import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day13Test: FunSpec ({

    val exampleInput = Utils.readLines("day13.txt", true)
    val realInput = Utils.readLines("day13.txt")

    context("Part 1") {
        test("should solve example") {
            Day13(exampleInput).solve1() shouldBe 295
        }

        test("should solve real input") {
            Day13(realInput).solve1() shouldBe 102
        }
    }

    context("Part 2") {
        test("should solve example") {
            Day13(exampleInput).solve2() shouldBe 1068781
        }

        test("should solve real input") {
            Day13(realInput).solve2() shouldBe 327300950120029
        }
    }

})