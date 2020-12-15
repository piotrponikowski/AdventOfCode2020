import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day15Test : FunSpec({

    context("Part 1") {
        context("should solve example") {
            forAll(
                row("0,3,6", 436),
                row("1,3,2", 1),
                row("2,1,3", 10),
                row("1,2,3", 27),
                row("2,3,1", 78),
                row("3,2,1", 438),
                row("3,1,2", 1836)
            ) { input, result ->
                test("$input") {
                    Day15(input).solve1() shouldBe result
                }
            }
        }

        test("should solve real input") {
            Day15("0,3,1,6,7,5").solve1() shouldBe 852

        }
    }

    context("Part 2") {
        context("should solve example") {
            forAll(
                row("0,3,6", 175594),
                row("1,3,2", 2578),
                row("2,1,3", 3544142),
                row("1,2,3", 261214),
                row("2,3,1", 6895259),
                row("3,2,1", 18),
                row("3,1,2", 362)
            ) { input, result ->
                test("$input") {
                    Day15(input).solve2() shouldBe result
                }
            }
        }

        test("should solve real input") {
            Day15("0,3,1,6,7,5").solve2() shouldBe 6007666
        }
    }
})