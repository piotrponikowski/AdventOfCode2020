import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day23Test :FunSpec ({

    val exampleInput = "389125467"
    val realInput = "496138527"

    context("Part 1") {
        test("should solve example") {
            Day23(exampleInput).solve1() shouldBe "67384529"
        }

        test("should solve real input") {
            Day23(realInput).solve1() shouldBe "69425837"
        }
    }

    context("Part 2") {
        test("should solve example") {
            Day23(exampleInput).solve2() shouldBe 149245887792L
        }

        test("should solve real input") {
            Day23(realInput).solve2() shouldBe 218882971435L
        }
    }

})