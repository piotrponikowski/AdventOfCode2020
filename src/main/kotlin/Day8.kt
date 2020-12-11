class Day8(input: List<String>) {

    val instructions = input.map { Instruction.parse(it) }

    fun solve1() = Game().run(instructions).acc

    fun solve2() = instructions.indices
        .map { corruptedIndex -> Game(corruptedIndex).run(instructions) }
        .first { result -> !result.loop }.acc

    data class Instruction(val operation: String, val argument: Int) {
        fun fix() = when (operation) {
            "jmp" -> copy(operation = "nop")
            "nop" -> copy(operation = "jmp")
            else -> copy()
        }

        companion object {
            private val PATTERN = Regex("^([a-z]+) ([+-][0-9]+)\$")

            fun parse(input: String) = PATTERN.matchEntire(input)!!.destructured
                .let { (operation, argument) -> Instruction(operation, argument.toInt()) }
        }
    }

    class Game(private val corruptedIndex: Int = -1) {
        var acc = 0
        var index = 0

        fun execute(instruction: Instruction) = with(instruction) {
            index += if (operation == "jmp") argument else 1
            acc += if (operation == "acc") argument else 0
        }

        fun run(instructions: List<Instruction>): GameResult {
            val visited = mutableSetOf<Int>()

            while (index !in visited && index < instructions.size) {
                visited += index
                execute(if (index == corruptedIndex) instructions[index].fix() else instructions[index])
            }

            return GameResult(index in visited, acc)
        }
    }

    data class GameResult(val loop: Boolean, val acc: Int)
}