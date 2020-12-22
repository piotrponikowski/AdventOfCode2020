import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day22Test : FunSpec({

    val exampleInput = Utils.readText("day22.txt", true)
    val realInput = Utils.readText("day22.txt")

    context("Part 1") {

        test("should parse example") {
            val day = Day22(exampleInput)
            day.decks[0] shouldBe listOf(9, 2, 6, 3, 1)
            day.decks[1] shouldBe listOf(5, 8, 4, 7, 10)
        }

        test("should solve example") {
            Day22(exampleInput).solve1() shouldBe 306
        }

        test("should solve real input") {
            Day22(realInput).solve1() shouldBe 31269
        }
    }

    context("Part 2") {

        test("should solve example") {
            Day22(exampleInput).solve2() shouldBe 291
        }

        test("should solve real input") {
            Day22(realInput).solve2() shouldBe 31151
        }
    }
})