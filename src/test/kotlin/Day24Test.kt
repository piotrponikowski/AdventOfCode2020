import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day24Test :FunSpec ({

    val exampleInput = Utils.readLines("day24.txt", true)
    val realInput = Utils.readLines("day24.txt")

    context("Part 1") {
        test("should solve example") {
            Day24(exampleInput).solve1() shouldBe 10
        }

        test("should solve real input") {
            Day24(realInput).solve1() shouldBe 497
        }
    }

    context("Part 2") {
        test("should solve example") {
            Day24(exampleInput).solve2() shouldBe 2208
        }

        test("should solve real input") {
            Day24(realInput).solve2() shouldBe 4156
        }
    }

})