import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day14Test : FunSpec({

    val exampleInput1 = Utils.readText("day14-1.txt", true)
    val exampleInput2 = Utils.readText("day14-2.txt", true)
    val realInput = Utils.readText("day14.txt")

    context("Part 1") {
        test("should solve example") {
            Day14(exampleInput1).solve1() shouldBe 165
        }

        test("should solve real input") {
            Day14(realInput).solve1() shouldBe 6559449933360
        }
    }

    context("Part 2") {
        test("should solve example") {
            Day14(exampleInput2).solve2() shouldBe 208
        }

        test("should solve real input") {
            Day14(realInput).solve2() shouldBe 3369767240513
        }
    }
})