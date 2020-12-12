import kotlin.math.absoluteValue

class Day12(input: List<String>) {

    private val pattern = Regex("^([A-Z])([0-9]+)$")

    private val instructions = input.map { pattern.matchEntire(it)!!.destructured }
        .map { (action, value) -> Instruction(Action.valueOf(action), value.toInt()) }

    fun solve1() = instructions.fold(Ferry()) { ferry, instruction -> ferry.execute(instruction) }.distance()

    fun solve2() = instructions
        .fold(Ferry(waypoint = Position(10, -1))) { ferry, instruction -> ferry.execute(instruction, true) }
        .distance()

    data class Ferry(val position: Position = Position(0, 0), val waypoint: Position = Position(1, 0)) {
        fun execute(instruction: Instruction, moveWaypoint: Boolean = false): Ferry {
            return when (instruction.action) {
                Action.N -> move(0, -instruction.value, moveWaypoint)
                Action.S -> move(0, instruction.value, moveWaypoint)
                Action.W -> move(-instruction.value, 0, moveWaypoint)
                Action.E -> move(instruction.value, 0, moveWaypoint)
                Action.L -> rotate(instruction.value / 90, false)
                Action.R -> rotate(instruction.value / 90, true)
                Action.F -> move(waypoint.x * instruction.value, waypoint.y * instruction.value, false)
            }
        }

        fun distance() = with(position) { x.absoluteValue + y.absoluteValue }

        private fun rotate(steps: Int, clockwise: Boolean) = copy(waypoint = (1..steps)
            .fold(waypoint) { p, _ -> if (clockwise) Position(-p.y, p.x) else Position(p.y, -p.x) })

        private fun move(dx: Int, dy: Int, moveWaypoint: Boolean) =
            if (moveWaypoint) copy(waypoint = Position(waypoint.x + dx, waypoint.y + dy))
            else copy(position = Position(position.x + dx, position.y + dy))
    }

    data class Position(val x: Int, val y: Int)

    data class Instruction(val action: Action, val value: Int)

    enum class Action { N, S, E, W, L, R, F }
}