class Day17(input: List<String>) {

    val startingPoints = input
        .flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, cell ->
                if (cell == '#') Point(x, y, 0) else null
            }
        }.toSet()

    val neighbours =
        (-1..1).flatMap { dx ->
            (-1..1).flatMap { dy ->
                (-1..1).mapNotNull { dz ->
                    if (!(dx == 0 && dy == 0 && dz == 0)) Point(dx, dy, dz) else null
                }
            }
        }.toSet()


    fun solve1() {
        var points = startingPoints
        repeat(6) {
            points = solve(points)
            println(points.count())
        }
    }

    fun solve(prevPoints: Set<Point>): Set<Point> {
        val nextPoints = mutableSetOf<Point>()

        val xMin = prevPoints.minOf { it.x } - 1
        val xMax = prevPoints.maxOf { it.x } + 1

        val yMin = prevPoints.minOf { it.y } - 1
        val yMax = prevPoints.maxOf { it.y } + 1

        val zMin = prevPoints.minOf { it.z } - 1
        val zMax = prevPoints.maxOf { it.z } + 1

        (xMin..xMax).forEach { x ->
            (yMin..yMax).forEach { y ->
                (zMin..zMax).forEach { z ->
                    val nextPoint = Point(x, y, z)
                    val isActive = prevPoints.contains(nextPoint)
                    val activeNeighbours = neighbours.count { neighbour -> prevPoints.contains(nextPoint + neighbour) }

                    if (isActive && activeNeighbours in 2..3) {
                        nextPoints += nextPoint
                    }

                    if (!isActive && activeNeighbours == 3) {
                        nextPoints += nextPoint
                    }
                }
            }
        }

        return nextPoints
    }


    data class Point(val x: Int, val y: Int, val z: Int) {
        operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y, this.z + other.z)
    }
}

fun main() {
    val input = Utils.readLines("day17.txt", true)
    val day = Day17(input)
    day.solve1()

}