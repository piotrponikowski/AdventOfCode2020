import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day12Test : FunSpec({

    val exampleInput = Utils.readLines("day12.txt", true)
    val realInput = Utils.readLines("day12.txt")

    context("Part 1") {
        test("should solve example") {
            Day12(exampleInput).solve1() shouldBe 25
        }

        test("should solve real input") {
            Day12(realInput).solve1() shouldBe 1177
        }
    }

    context("Part 2") {
        test("should solve example") {
            Day12(exampleInput).solve2() shouldBe 286
        }

        test("should solve real input") {
            Day12(realInput).solve2() shouldBe 46530
        }
    }
})