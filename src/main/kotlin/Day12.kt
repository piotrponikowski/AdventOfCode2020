import kotlin.math.absoluteValue

class Day12(input: List<String>) {

    private val pattern = Regex("^([A-Z])([0-9]+)$")

    private val instructions = input.map { pattern.matchEntire(it)!!.destructured }
        .map { (action, value) -> Instruction(Action.valueOf(action), value.toInt()) }

    fun solve1() = instructions.fold(Ferry()) { ferry, instruction -> ferry.execute(instruction) }.distance()

    fun solve2() = instructions.fold(Ferry(waypoint = Point(10, -1))) { ferry, instruction ->
        ferry.execute(instruction, true)
    }.distance()

    data class Ferry(val position: Point = Point(0, 0), val waypoint: Point = Point(1, 0)) {
        fun execute(instruction: Instruction, moveWaypoint: Boolean = false): Ferry {
            return when (instruction.action) {
                Action.N -> move(0, -instruction.value, moveWaypoint)
                Action.S -> move(0, instruction.value, moveWaypoint)
                Action.W -> move(-instruction.value, 0, moveWaypoint)
                Action.E -> move(instruction.value, 0, moveWaypoint)
                Action.L -> rotate(instruction.value, false)
                Action.R -> rotate(instruction.value, true)
                Action.F -> move(waypoint.x * instruction.value, waypoint.y * instruction.value, false)
            }
        }

        fun distance() = with(position) { x.absoluteValue + y.absoluteValue }

        private fun rotate(angle: Int, clockwise: Boolean) = copy(waypoint = waypoint.rotate(angle / 90, clockwise))

        private fun move(dx: Int, dy: Int, moveWaypoint: Boolean) =
            if (moveWaypoint) copy(waypoint = this.waypoint.move(dx, dy)) else copy(position = position.move(dx, dy))
    }

    data class Point(val x: Int, val y: Int) {
        fun rotate(steps: Int, clockwise: Boolean) =
            (1..steps).fold(this) { p, _ -> if (clockwise) Point(-p.y, p.x) else Point(p.y, -p.x) }

        fun move(dx: Int, dy: Int) = Point(x + dx, y + dy)
    }

    data class Instruction(val action: Action, val value: Int)

    enum class Action { N, S, E, W, L, R, F }
}