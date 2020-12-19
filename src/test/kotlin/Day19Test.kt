import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day19Test : FunSpec({

    val exampleInput1 = Utils.readText("day19-1.txt", true)
    val exampleInput2 = Utils.readText("day19-2.txt", true)

    val realInput = Utils.readText("day19.txt")

    context("Part 1") {

        test("should parse example 1") {
            val day = Day19(exampleInput1)
            day.messages.size shouldBe 5
            day.rules.size shouldBe 6
        }

        test("should solve example 1") {
            Day19(exampleInput1).solve1() shouldBe 2
        }

        test("should solve example 2") {
            Day19(exampleInput2).solve1() shouldBe 3
        }

        test("should solve real input") {
            Day19(realInput).solve1() shouldBe 139
        }
    }

    context("Part 2") {

        test("should solve example 2") {
            Day19(exampleInput2).solve2() shouldBe 12
        }

        test("should solve real input") {
            Day19(realInput).solve2() shouldBe 289
        }
    }
})