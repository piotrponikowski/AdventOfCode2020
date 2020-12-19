import java.lang.System.lineSeparator

class Day11(input: List<String>) {

    private val startingState = input
        .flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, cell -> Point(x, y) to cell }
        }.toMap()

    private val neighbours =
        (-1..1).flatMap { dx ->
            (-1..1).mapNotNull { dy ->
                Point(dx, dy).takeIf { !(dx == 0 && dy == 0) }
            }
        }.toSet()

    fun solve1() = solve(4, ::countNear)

    fun solve2() = solve(5, ::countFar)

    fun solve(min: Int, countMethod: (Map<Point, Char>, Point) -> Int): Int {
        val states = mutableSetOf<String>()
        var state = startingState

        while (!states.contains(print(state))) {
            states += print(state)
            state = solve(state, min, countMethod)
        }

        return state.count { it.value == '#' }
    }

    fun solve(state: Map<Point, Char>, min: Int, countMethod: (Map<Point, Char>, Point) -> Int): Map<Point, Char> {
        val nextState = mutableMapOf<Point, Char>()

        state.forEach { (point, type) ->
            val isSeat = type == 'L' || type == '#'
            val occupiedNeighbours = countMethod(state, point)

            nextState[point] = when {
                !isSeat -> type
                occupiedNeighbours == 0 -> '#'
                occupiedNeighbours in min..8 -> 'L'
                else -> type
            }
        }

        return nextState
    }

    private fun print(state: Map<Point, Char>): String {
        val maxX = state.keys.maxOf { it.x }
        val maxY = state.keys.maxOf { it.x }

        val result = StringBuilder()
        (0..maxX).forEach { x ->
            (0..maxY).forEach { y ->
                result.append(state[Point(x, y)])
            }
            result.append(lineSeparator())
        }
        return result.toString()
    }

    private fun countNear(state: Map<Point, Char>, point: Point) =
        neighbours.count { neighbour -> state[point + neighbour] == '#' }


    private fun countFar(state: Map<Point, Char>, point: Point) =
        neighbours.count { neighbour ->
            generateSequence(point + neighbour) { it + neighbour }
                .first { state[it] != '.' }
                .let { state[it] == '#' }
        }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)
    }
}
