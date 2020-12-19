import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day11Test : FunSpec({

    val exampleInput = Utils.readLines("day11.txt", true)
    val realInput = Utils.readLines("day11.txt")

    context("Part 1") {
        test("should solve example") {
            Day11(exampleInput).solve1() shouldBe 37
        }

        test("should solve real input") {
            Day11(realInput).solve1() shouldBe 2354
        }
    }

    context("Part 2") {
        test("should solve example") {
            Day11(exampleInput).solve2() shouldBe 26
        }

        test("should solve real input") {
            Day11(realInput).solve2() shouldBe 2072
        }
    }
})