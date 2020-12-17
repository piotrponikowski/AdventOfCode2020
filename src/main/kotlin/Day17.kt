class Day17(input: List<String>) {

     val startingPoints = input
        .flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, cell ->
                if (cell == '#') Point(x, y, 0, 0) else null
            }
        }.toSet()

     val neighbours =
        (-1..1).flatMap { dx ->
            (-1..1).flatMap { dy ->
                (-1..1).flatMap { dz ->
                    (-1..1).mapNotNull { dw ->
                        if (!(dx == 0 && dy == 0 && dz == 0 && dw == 0)) Point(dx, dy, dz, dw) else null

                    }
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

        val wMin = prevPoints.minOf { it.w } - 1
        val wMax = prevPoints.maxOf { it.w } + 1

        (xMin..xMax).forEach { x ->
            (yMin..yMax).forEach { y ->
                (zMin..zMax).forEach { z ->
                    (wMin..wMax).forEach { w ->
                        val nextPoint = Point(x, y, z, w)
                        val isActive = prevPoints.contains(nextPoint)
                        val activeNeighbours =
                            neighbours.count { neighbour -> prevPoints.contains(nextPoint + neighbour) }

                        if (isActive && activeNeighbours in 2..3) {
                            nextPoints += nextPoint
                        }

                        if (!isActive && activeNeighbours == 3) {
                            nextPoints += nextPoint
                        }
                    }
                }
            }
        }

        return nextPoints
    }


    data class Point(val x: Int, val y: Int, val z: Int, val w: Int) {
        operator fun plus(p: Point) = Point(this.x + p.x, this.y + p.y, this.z + p.z, this.w + p.w)
    }
}

fun main() {
    val input = Utils.readLines("day17.txt")
    val day = Day17(input)
    day.solve1()

}