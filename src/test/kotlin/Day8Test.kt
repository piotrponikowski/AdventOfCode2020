import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day8Test : FunSpec({

    val exampleInput = Utils.readLines("day8.txt", true)
    val realInput = Utils.readLines("day8.txt")

    context("Part 1") {
        test("should parse instructions") {
            val instructions = Day8(exampleInput).instructions
            instructions.size shouldBe 9

            instructions[0] shouldBe Day8.Instruction("nop", 0)
            instructions[1] shouldBe Day8.Instruction("acc", 1)
            instructions[2] shouldBe Day8.Instruction("jmp", 4)
            instructions[3] shouldBe Day8.Instruction("acc", 3)
            instructions[4] shouldBe Day8.Instruction("jmp", -3)
            instructions[5] shouldBe Day8.Instruction("acc", -99)
            instructions[6] shouldBe Day8.Instruction("acc", +1)
            instructions[7] shouldBe Day8.Instruction("jmp", -4)
            instructions[8] shouldBe Day8.Instruction("acc", +6)
        }

        test("should test instructions") {
            val game = Day8.Game(-1)

            game.execute(Day8.Instruction("nop", 0))
            game.index shouldBe 1
            game.acc shouldBe 0

            game.execute(Day8.Instruction("acc", 1))
            game.index shouldBe 2
            game.acc shouldBe 1

            game.execute(Day8.Instruction("jmp", 4))
            game.index shouldBe 6
            game.acc shouldBe 1
        }

        test("should solve example") {
            val answer = Day8(exampleInput).solve1()
            answer shouldBe 5
        }

        test("should solve real input") {
            val answer = Day8(realInput).solve1()
            answer shouldBe 1521
        }
    }

    context("Part 2") {
        test("should solve example") {
            val answer = Day8(exampleInput).solve2()
            answer shouldBe 8
        }

        test("should solve real input") {
            val answer = Day8(realInput).solve2()
            answer shouldBe 1016
        }
    }

})