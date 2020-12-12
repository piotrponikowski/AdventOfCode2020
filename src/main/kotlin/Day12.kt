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
        var waypoint = Position(10, -1)

        fun execute(instruction: Instruction) {
            when (instruction.action) {
                Action.N -> waypoint.y -= instruction.value
                Action.S -> waypoint.y += instruction.value
                Action.W -> waypoint.x -= instruction.value
                Action.E -> waypoint.x += instruction.value
                Action.L -> waypoint.rotateLeft(instruction.value / 90)
                Action.R -> waypoint.rotateRight(instruction.value / 90)
                Action.F -> {
                    position.x += waypoint.x * instruction.value
                    position.y += waypoint.y * instruction.value
                }
            }
        }
    }

    data class Instruction(val action: Action, val value: Int)
    data class Position(var x: Int, var y: Int) {

        fun rotateRight(steps: Int) {
            (1..steps).forEach {
                val tx = x
                val ty = y

                x = -ty
                y = tx
            }
        }

        fun rotateLeft(steps: Int) {
            (1..steps).forEach {
                val tx = x
                val ty = y

                x = ty
                y = -tx
            }
        }


    }
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