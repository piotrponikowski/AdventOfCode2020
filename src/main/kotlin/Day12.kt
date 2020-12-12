import kotlin.math.absoluteValue

class Day12(input: List<String>) {

    private val pattern = Regex("^([A-Z])([0-9]+)$")

    private val instructions = input.map { pattern.matchEntire(it)!!.destructured }
        .map { (action, value) -> Instruction(action, value.toInt()) }


    fun solve1() = instructions.fold(Ferry()) { ferry, instruction -> ferry.execute(instruction); ferry }.distance()

    fun solve2() :Int = TODO("part2")


    class Ferry {
        var position = Position(0, 0)
        var waypoint = Position(1, 0)

        fun execute(instruction: Instruction) {
            when (instruction.action) {
                "N" -> position = position.move(0, -instruction.value)
                "S" -> position = position.move(0, instruction.value)
                "W" -> position = position.move(-instruction.value, 0)
                "E" -> position = position.move(instruction.value, 0)
                "L" -> waypoint = waypoint.rotateLeft(instruction.value / 90)
                "R" -> waypoint = waypoint.rotateRight(instruction.value / 90)
                "F" -> position = position.move(waypoint.x * instruction.value, waypoint.y * instruction.value)
            }
        }

        fun distance() = with(position) { x.absoluteValue + y.absoluteValue }
    }

    data class Instruction(val action: String, val value: Int)

    data class Position(val x: Int, val y: Int) {
        fun rotateRight(steps: Int) = (1..steps).fold(this) { point, _ -> Position(-point.y, point.x) }
        fun rotateLeft(steps: Int) = (1..steps).fold(this) { point, _ -> Position(point.y, -point.x) }
        fun move(dx: Int, dy: Int) = Position(x + dx, y + dy)
    }
}