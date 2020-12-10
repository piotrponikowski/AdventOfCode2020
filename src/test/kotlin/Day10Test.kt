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


//        test("should solve example") {
//            val answer = Day10(exampleInput1).solve1()
//            answer shouldBe 35
//        }
//
//        test("should solve 2nd example") {
//            val answer = Day10(exampleInput2).solve1()
//            answer shouldBe 220
//        }
//
//        test("should solve real input") {
//            val answer = Day10(realInput).solve1()
//            answer shouldBe 2592
//        }
    }
    context("Part 2") {
        test("should solve example") {
            val answer = Day10(exampleInput1).solve2()
            answer shouldBe 8
        }

        test("should solve 2nd example") {
            val answer = Day10(exampleInput2).solve2()
            answer shouldBe 19208
        }

        test("should solve real input") {
            val answer = Day10(realInput).solve2()
            answer shouldBe 198428693313536
        }
    }
})