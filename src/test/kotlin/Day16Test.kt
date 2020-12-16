import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe

class Day16Test : FunSpec({

    val exampleInput1 = Utils.readText("day16-1.txt", true)
    val exampleInput2 = Utils.readText("day16-2.txt", true)
    val realInput = Utils.readText("day16.txt")

    context("Part 1") {
        test("should solve example") {
            Day16(exampleInput1).solve1() shouldBe 71
        }

        test("should solve real input") {
            Day16(realInput).solve1() shouldBe 22000
        }
    }

    context("Part 2") {

        test("should match fields for 1st example") {
            val fields = Day16(exampleInput1).solveFields()
            assertSoftly {
                fields shouldContain Pair("row", 0)
                fields shouldContain Pair("class", 1)
                fields shouldContain Pair("seat", 2)
            }
        }

        test("should match fields for 2snd example") {
            val fields = Day16(exampleInput2).solveFields()
            assertSoftly {
                fields shouldContain Pair("row", 0)
                fields shouldContain Pair("class", 1)
                fields shouldContain Pair("seat", 2)
            }
        }

        test("should solve real input") {
            Day16(realInput).solve2() shouldBe 410460648673
        }
    }
})