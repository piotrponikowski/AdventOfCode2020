import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day17Test : FunSpec({

    val exampleInput = Utils.readLines("day17.txt", true)
    val realInput = Utils.readLines("day17.txt")

    context("Part 1") {

        test("should initialize starting points") {
            Day17(exampleInput).startingPoints.count() shouldBe 5
        }

        test("should solve example") {
            Day17(exampleInput).solve1() shouldBe 112
        }

        test("should solve real input") {
            Day17(realInput).solve1() shouldBe 359
        }
    }

    context("Part 2") {

        test("should initialize neighbours") {
            Day17(exampleInput).neighbours.count() shouldBe 80
        }

        test("should solve example") {
            Day17(exampleInput).solve2() shouldBe 848
        }

        test("should solve real input") {
            Day17(realInput).solve2() shouldBe 2228
        }
    }
})