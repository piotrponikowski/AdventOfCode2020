import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day1Test : FunSpec({

    val exampleInput = Utils.readLines("day1.txt", true)
    val realInput = Utils.readLines("day1.txt");

    context("Part 1") {
        test("should solve example") {
            val answer = Day1(exampleInput).solve1()
            answer shouldBe 514579
        }

        test("should solve real input") {
            val answer = Day1(realInput).solve1()
            answer shouldBe 996075
        }
    }

    context("Part 2") {
        test("should solve example") {
            val answer = Day1(exampleInput).solve2()
            answer shouldBe 241861950
        }

        test("should solve real input") {
            val answer = Day1(realInput).solve2()
            answer shouldBe 51810360
        }
    }

})
