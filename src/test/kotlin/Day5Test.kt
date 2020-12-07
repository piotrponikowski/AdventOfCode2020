import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day5Test : FunSpec({

    val realInput = Utils.readLines("day5.txt")

    context("Part 1") {

        context("should calculate seat id") {
            forAll(
                row("FBFBBFFRLR", 357),
                row("BFFFBBFRRR", 567),
                row("FFFBBBFRRR", 119),
                row("BBFFBBFRLL", 820)
            ) { input, seatId ->
                test("for input: $input") {
                    Day5.decodeSeatId(input) shouldBe seatId
                }
            }
        }

        test("should solve real input") {
            val answer = Day5(realInput).solve1()
            answer shouldBe 874
        }
    }

    context("Part 2") {
        test("should solve real input") {
            val answer = Day5(realInput).solve2()
            answer shouldBe 594
        }
    }

})