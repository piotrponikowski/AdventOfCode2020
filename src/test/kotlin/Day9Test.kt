import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day9Test : FunSpec({

    val exampleInput = Utils.readLines("day9.txt", true)
    val realInput = Utils.readLines("day9.txt")


    context("Part 1") {
        test("should solve example") {
            val answer = Day9(exampleInput).solve1(5)
            answer shouldBe 127
        }

        test("should solve real input") {
            val answer = Day9(realInput).solve1(25)
            answer shouldBe 1309761972
        }
    }

    context("Part 2") {
        test("should solve example") {
            val answer = Day9(exampleInput).solve2(127)
            answer shouldBe 62
        }

        test("should solve real input") {
            val answer = Day9(realInput).solve2(1309761972)
            answer shouldBe 177989832
        }
    }
})