import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day10Test : FunSpec({

    val exampleInput1 = Utils.readLines("day10-1.txt", true)
    val exampleInput2 = Utils.readLines("day10-2.txt", true)
    val realInput = Utils.readLines("day10.txt")

    context("Part 1") {

        forAll(
            row("1st example", exampleInput1, 35),
            row("2nd example", exampleInput2, 220),
            row("real input", realInput, 2592)
        ) { name, data, result ->
            test("should solve $name") {
                Day10(data).solve1() shouldBe result
            }
        }
    }
    context("Part 2") {

        forAll(
            row("1st example", exampleInput1, 8),
            row("2nd example", exampleInput2, 19208),
            row("real input", realInput, 198428693313536)
        ) { name, data, result ->
            test("should solve $name") {
                Day10(data).solve2() shouldBe result
            }
        }
    }
})