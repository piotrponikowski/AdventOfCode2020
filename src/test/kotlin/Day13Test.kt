import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day13Test : FunSpec({

    val exampleInput = Utils.readLines("day13.txt", true)
    val realInput = Utils.readLines("day13.txt")

    context("Part 1") {
        test("should parse example") {
            val day = Day13(exampleInput)

            day.timestamp shouldBe 939
            day.buses.size shouldBe 5

            day.buses[0] shouldBe Day13.Bus(0, 7L)
            day.buses[1] shouldBe Day13.Bus(1, 13L)
            day.buses[2] shouldBe Day13.Bus(4, 59L)
            day.buses[3] shouldBe Day13.Bus(6, 31L)
            day.buses[4] shouldBe Day13.Bus(7, 19L)
        }

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