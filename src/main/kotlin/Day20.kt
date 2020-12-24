import java.lang.System.lineSeparator

class Day20(input: String) {

    private val tiles = parseTiles(input)

    private val neighbours = listOf(
        Neighbour(Point(-1, 0), Tile::edgeLeft, Tile::edgeRight),
        Neighbour(Point(1, 0), Tile::edgeRight, Tile::edgeLeft),
        Neighbour(Point(0, -1), Tile::edgeTop, Tile::edgeBottom),
        Neighbour(Point(0, 1), Tile::edgeBottom, Tile::edgeTop)
    )

    private val monsterPoints = arrayOf(
        "                  # ",
        "#    ##    ##    ###",
        " #  #  #  #  #  #   "
    ).flatMapIndexed { dy, line ->
        line.mapIndexedNotNull { dx, cell ->
            Point(dx, dy).takeIf { cell == '#' }
        }
    }

    fun solve1(): Long {
        val solvedTiles = solveTiles()
        val corners = solvedTiles.keys.corners()

        return solvedTiles.filter { (point) -> point in corners }.map { (_, tile) -> tile.id }.reduce(Long::times)
    }

    fun solve2(): Int {
        val solvedTiles = solveTiles()
        val croppedTiles = solvedTiles.mapValues { (_, tile) -> tile.crop() }
        val (sizeX, sizeY) = solvedTiles.keys.size()
        val (min, _) = solvedTiles.keys.minMax()

        val mergedData = MutableList(sizeY * 8) { MutableList(sizeX * 8) { '0' } }
        croppedTiles.forEach { (point, tile) ->
            tile.data.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    val fx = point.x - min.x
                    val fy = point.y - min.y
                    mergedData[fy * 8 + y][fx * 8 + x] = cell
                }
            }
        }

        val mergedTile = Tile(0, mergedData)
        val mergedTileSize = mergedTile.data.size

        val (monsterSizeX, monsterSizeY) = monsterPoints.size()
        val offsetX = mergedTileSize - monsterSizeX
        val offsetY = mergedTileSize - monsterSizeY

        val monsters = Tile.combinations(mergedTile).map { rotatedTile ->
            rotatedTile.data.mapIndexed { y, line ->
                line.withIndex()
                    .filter { (x, _) -> x <= offsetX && y <= offsetY }
                    .count { (x, _) -> monsterPoints.all { point -> rotatedTile.data[y + point.y][x + point.x] == '#' } }
            }.sum()
        }.first { sum -> sum > 0 }

        val waterCount = mergedTile.data.sumBy { line -> line.count { cell -> cell == '#' } }
        return waterCount - (monsterPoints.size * monsters)
    }

    private fun solveTiles(): Map<Point, Tile> {
        val solvedTiles = mutableMapOf<Point, Tile>()
        solvedTiles[Point(0, 0)] = tiles.first()

        while (solvedTiles.size < tiles.size) {

            solvedTiles.toList().forEach { (point, solvedTile) ->
                val emptyNeighbours = neighbours.filter { neighbour -> point + neighbour.direction !in solvedTiles }
                val unsolvedTiles = tiles.filter { tile -> tile.id !in solvedTiles.values.map { it.id } }

                emptyNeighbours.forEach { neighbour ->
                    unsolvedTiles.forEach { unsolvedTile ->

                        Tile.combinations(unsolvedTile).find { rotatedTile ->
                            val edge = neighbour.edge(solvedTile)
                            val otherEdge = neighbour.otherEdge(rotatedTile)
                            edge == otherEdge
                        }?.let { matchedTile -> solvedTiles[point + neighbour.direction] = matchedTile }
                    }
                }
            }
        }

        return solvedTiles
    }

    companion object {

        private val PATTERN_ID = Regex("([0-9]+)")

        fun parseTiles(input: String) = input
            .split(lineSeparator().repeat(2))
            .map { it.split(lineSeparator()) }
            .map { chunk ->
                val (id) = PATTERN_ID.find(chunk.first())!!.destructured
                val data = chunk.drop(1).map { line -> line.toList() }.toList()
                Tile(id.toLong(), data)
            }
    }

    fun Collection<Point>.minMax() = Pair(Point(minOf { it.x }, minOf { it.y }), Point(maxOf { it.x }, maxOf { it.y }))

    fun Collection<Point>.corners() = minMax().let { (min, max) ->
        listOf(Point(min.x, min.y), Point(min.x, max.y), Point(max.x, min.y), Point(max.x, max.y))
    }

    fun Collection<Point>.size() = minMax().let { (min, max) -> (max.x - min.x) + 1 to (max.y - min.y) + 1 }

    data class Tile(val id: Long, val data: List<List<Char>>) {
        fun flip() = copy(data = data.reversed())
        fun rotate() = copy(data = (data.indices).map { x -> (data.indices).map { y -> data[data.size - y - 1][x] } })
        fun crop() = copy(data = data.drop(1).dropLast(1).map { line -> line.drop(1).dropLast(1) })

        companion object {
            fun edgeTop(tile: Tile) = tile.data.first().joinToString("")
            fun edgeBottom(tile: Tile) = tile.data.last().joinToString("")
            fun edgeLeft(tile: Tile) = tile.data.map { row -> row.first() }.joinToString("")
            fun edgeRight(tile: Tile) = tile.data.map { row -> row.last() }.joinToString("")

            fun combinations(tile: Tile) = sequence {
                var state = tile
                (1..8).forEach { step ->
                    yield(state)
                    state = if (step == 4) state.rotate().flip() else state.rotate()
                }
            }
        }
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)
    }

    data class Neighbour(val direction: Point, val edge: (Tile) -> String, val otherEdge: (Tile) -> String)
}
