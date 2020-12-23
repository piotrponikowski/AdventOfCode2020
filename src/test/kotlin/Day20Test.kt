import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day20Test : FunSpec({

    val exampleInput = Utils.readText("day20.txt", true)
    val realInput = Utils.readText("day20.txt")

    context("Part 1") {

        test("should solve example") {
            Day20(exampleInput).solve1() shouldBe 20899048083289L
        }

        test("should solve real input") {
            Day20(realInput).solve1() shouldBe 16192267830719L
        }
    }

    context("Part 2") {

        test("should solve example") {
            Day20(exampleInput).solve1() shouldBe 20899048083289L
        }

        test("should solve real input") {
            Day20(realInput).solve1() shouldBe 16192267830719L
        }
    }
})