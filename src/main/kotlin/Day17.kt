class Day17(input: List<String>) {

    val startingPoints = input
        .flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, cell ->
                Point(x, y, 0, 0).takeIf { cell == '#' }
            }
        }.toSet()

    val neighbours =
        (-1..1).flatMap { dx ->
            (-1..1).flatMap { dy ->
                (-1..1).flatMap { dz ->
                    (-1..1).mapNotNull { dw ->
                        Point(dx, dy, dz, dw).takeIf { !(dx == 0 && dy == 0 && dz == 0 && dw == 0) }
                    }
                }
            }
        }.toSet()

    fun solve1() = (1..6).fold(startingPoints) { prev, _ -> solve(prev) }.count()

    fun solve2() = (1..6).fold(startingPoints) { prev, _ -> solve(prev, true) }.count()

    fun solve(prev: Set<Point>, fourthDimension: Boolean = false): Set<Point> {
        val nextPoints = mutableSetOf<Point>()

        val xMin = prev.minOf { it.x } - 1
        val xMax = prev.maxOf { it.x } + 1

        val yMin = prev.minOf { it.y } - 1
        val yMax = prev.maxOf { it.y } + 1

        val zMin = prev.minOf { it.z } - 1
        val zMax = prev.maxOf { it.z } + 1

        val wMin = if (fourthDimension) prev.minOf { it.w } - 1 else 0
        val wMax = if (fourthDimension) prev.maxOf { it.w } + 1 else 0

        (xMin..xMax).forEach { x ->
            (yMin..yMax).forEach { y ->
                (zMin..zMax).forEach { z ->
                    (wMin..wMax).forEach { w ->
                        val nextPoint = Point(x, y, z, w)
                        val isActive = prev.contains(nextPoint)
                        val activeNeighbours = neighbours.count { neighbour -> prev.contains(nextPoint + neighbour) }

                        if ((isActive && activeNeighbours in 2..3) || (!isActive && activeNeighbours == 3)) {
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