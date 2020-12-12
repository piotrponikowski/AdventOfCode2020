import kotlin.math.absoluteValue

class Day12(input: List<String>) {

    val pattern = Regex("^([A-Z])([0-9]+)$")

    val instructions = input.map { pattern.matchEntire(it)!!.destructured }
        .map { (action, value) -> Instruction(Action.valueOf(action), value.toInt()) }


    fun solve(): Int {
        val ferry = Ferry()
        instructions.forEach { ferry.execute(it) }

        return with(ferry.position) { x.absoluteValue + y.absoluteValue }
    }

    class Ferry {
        var position = Position(0, 0)
        var direction = Direction.E

        fun execute(instruction: Instruction) {
            when (instruction.action) {
                Action.N -> position.y -= instruction.value
                Action.S -> position.y += instruction.value
                Action.W -> position.x -= instruction.value
                Action.E -> position.x += instruction.value
                Action.L -> direction = direction.rotateLeft(instruction.value / 90)
                Action.R -> direction = direction.rotateRight(instruction.value / 90)
                Action.F -> execute(Instruction(Action.valueOf(direction.name), instruction.value))
            }
        }
    }

    data class Instruction(val action: Action, val value: Int)
    data class Position(var x: Int, var y: Int)
    enum class Action { N, S, E, W, L, R, F }
    enum class Direction {
        N, S, E, W;

        fun rotateRight(steps: Int): Direction {
            val right = arrayOf(N, E, S, W)
            val index = right.indexOf(this)
            val nextIndex = (index + steps) % right.size
            return right[nextIndex]
        }

        fun rotateLeft(steps: Int): Direction {
            val left = arrayOf(N, W, S, E)
            val index = left.indexOf(this)
            val nextIndex = (index + steps) % left.size
            return left[nextIndex]
        }
    }
}