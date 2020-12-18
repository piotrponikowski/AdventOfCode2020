import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day18Test : FunSpec({

    val realInput = Utils.readLines("day18.txt")

    context("Part 1") {

        context("should evaluate expression") {
            forAll(
                row("2 * 3", "6"),
                row("5 + 6", "11"),
                row("1 + 6", "7"),
                row("4 * 11", "44"),
                row("7 + 44", "51"),
                row("1 + 2 * 3 + 4 * 5 + 6", "71"),
            ) { expression, result ->
                test(expression) {
                    Day18.evalByOrder(expression) shouldBe result
                }
            }
        }

        context("should reduce expression") {
            forAll(
                row("1 + (2 * 3) + (4 * (5 + 6))", "51"),
                row("2 * 3 + (4 * 5)", "26"),
                row("5 + (8 * 3 + 9 + 3 * 4 * 3)", "437"),
                row("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", "12240"),
                row("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", "13632"),
            ) { expression, result ->
                test(expression) {
                    Day18.reduceByOrder(expression) shouldBe result
                }
            }
        }

        test("should solve real input") {
            Day18(realInput).solve1() shouldBe 3885386961962
        }
    }

    context("Part 2") {

        context("should evaluate expression") {
            forAll(
                row("2 * 3", "6"),
                row("5 + 6", "11"),
                row("1 + 6", "7"),
                row("4 * 11", "44"),
                row("7 + 44", "51"),
                row("1 + 2 * 3 + 4 * 5 + 6", "231"),
            ) { expression, result ->
                test(expression) {
                    Day18.evalByPriority(expression) shouldBe result
                }
            }
        }

        context("should reduce expression") {
            forAll(
                row("1 + (2 * 3) + (4 * (5 + 6))", "51"),
                row("2 * 3 + (4 * 5)", "46"),
                row("5 + (8 * 3 + 9 + 3 * 4 * 3)", "1445"),
                row("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", "669060"),
                row("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", "23340"),
            ) { expression, result ->
                test(expression) {
                    Day18.reduceByPriority(expression) shouldBe result
                }
            }
        }

        test("should solve real input") {
            Day18(realInput).solve2() shouldBe 112899558798666
        }
    }
})