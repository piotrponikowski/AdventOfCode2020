import java.lang.System.lineSeparator

typealias TileData = List<List<Char>>

class Day20(input: String) {

    val tiles = parseTiles(input)

    val neighbours = listOf(
        Neighbour(Point(-1, 0), Tile::edgeLeft, Tile::edgeRight),
        Neighbour(Point(1, 0), Tile::edgeRight, Tile::edgeLeft),
        Neighbour(Point(0, -1), Tile::edgeTop, Tile::edgeBottom),
        Neighbour(Point(0, 1), Tile::edgeBottom, Tile::edgeTop)
    )

    val monsterPoints = arrayOf(
        "                  # ",
        "#    ##    ##    ###",
        " #  #  #  #  #  #   "
    ).flatMapIndexed { dy, line ->
        line.mapIndexedNotNull { dx, cell ->
            Point(dx, dy).takeIf { cell == '#' }
        }
    }

    fun solve(): Map<Point, Tile> {
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

    data class Tile(val id: Long, val data: TileData) {
        fun flip() = copy(data = data.reversed())
        fun rotate() = copy(data = (data.indices).map { x -> (data.indices).map { y -> data[data.size - y - 1][x] } })
        fun print() = data.joinToString("\n") { it.joinToString("") }

        companion object {
            fun edgeTop(tile: Tile) = tile.data.first().joinToString("")
            fun edgeBottom(tile: Tile) = tile.data.last().joinToString("")
            fun edgeLeft(tile: Tile) = tile.data.map { row -> row.first() }.joinToString("")
            fun edgeRight(tile: Tile) = tile.data.map { row -> row.last() }.joinToString("")

            fun combinations(tile: Tile) = sequence {
                var state = tile
                (0..7).forEach { step ->
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

fun main() {
    val input = Utils.readText("day20.txt", true)
    val day = Day20(input)
    println(day.solve().map { (a, b) -> a to b.id })
//    println()

//    println(Day20.Tile.edgeLeft(day.tiles[0].data))
//    println(Day20.Tile.edgeRight(day.tiles[0].data))
//    println(Day20.Tile.edgeTop(day.tiles[0].data))
//    println(Day20.Tile.edgeBottom(day.tiles[0].data))


    day.solve()

}