import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day6Test : FunSpec({

    val exampleInput = Utils.readText("day6.txt", true)
    val realInput = Utils.readText("day6.txt")

    context("Part 1") {

        test("should solve example") {
            val answer = Day6(exampleInput).solve1();
            answer shouldBe 11
        }

        test("should solve real input") {
            val answer = Day6(realInput).solve1();
            answer shouldBe 6630
        }
    }

    context("Part 2") {

        test("should solve example") {
            val answer = Day6(exampleInput).solve2();
            answer shouldBe 6
        }

        test("should solve real input") {
            val answer = Day6(realInput).solve2();
            answer shouldBe 3437
        }
    }
})