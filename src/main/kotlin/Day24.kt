class Day24(input: List<String>) {

    private val tileDirections = input.map { parseDirections(it) }

    private val neighbours = mapOf(
        "e" to Point(2, 0), "se" to Point(1, 1), "ne" to Point(1, -1),
        "w" to Point(-2, 0), "sw" to Point(-1, 1), "nw" to Point(-1, -1),
    )

    fun solve1() = solveInitialMaze().count()

    fun solve2(): Int {
        var maze = solveInitialMaze()
        repeat(100) {
            val nextMaze = mutableSetOf<Point>()
            val tilesToCheck = maze.flatMap { tile -> neighbours.values.map { neighbour -> tile + neighbour } + tile }

            tilesToCheck.forEach { tileToCheck ->
                val isBlack = maze.contains(tileToCheck)
                val blackNeighbours = neighbours.values.count { neighbour -> maze.contains(tileToCheck + neighbour) }

                if ((isBlack && blackNeighbours in 1..2) || (!isBlack && blackNeighbours == 2)) {
                    nextMaze.add(tileToCheck)
                }
            }

            maze = nextMaze
        }

        return maze.count()
    }

    private fun solveInitialMaze(): Set<Point> {
        val referenceTile = Point(0, 0)
        val blackTiles = mutableSetOf<Point>()

        tileDirections.forEach { directions ->
            val targetTile = directions.fold(referenceTile) { tile, dir -> tile + neighbours[dir]!! }
            val isBlack = blackTiles.contains(targetTile)

            if (isBlack) blackTiles -= targetTile
            else blackTiles += targetTile
        }
        return blackTiles
    }


    companion object {
        private val PATTERN_DIRECTION = Regex("e|se|sw|w|nw|ne")

        fun parseDirections(input: String) = PATTERN_DIRECTION.findAll(input).map { it.value }.toList()
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)
    }
}