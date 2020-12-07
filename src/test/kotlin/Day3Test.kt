import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day3Test : FunSpec({

    val exampleInput = Utils.readLines("day3.txt", true)
    val realInput = Utils.readLines("day3.txt")

    context("Part 1") {

        test("should solve example") {
            val answer = Day3(exampleInput).solve1()
            answer shouldBe 7
        }

        test("should solve real input") {
            val answer = Day3(realInput).solve1()
            answer shouldBe 218
        }
    }

    context("Part 2") {

        context("should count trees for slope") {
            forAll(
                row(1, 1, 2),
                row(3, 1, 7),
                row(5, 1, 3),
                row(7, 1, 4),
                row(1, 2, 2)
            ) { right, down, count ->
                test("right: $right, down: $down") {
                    Day3(exampleInput).countTrees(right, down) shouldBe count
                }
            }
        }

        test("should solve example") {
            val answer = Day3(exampleInput).solve2()
            answer shouldBe 336
        }

        test("should solve real input") {
            val answer = Day3(realInput).solve2()
            answer shouldBe 3847183340L
        }

    }
})