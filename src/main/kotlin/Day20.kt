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

    fun solve() {
        val solvedTiles = mutableMapOf<Point, Tile>()
        solvedTiles[Point(0, 0)] = tiles.first()

        while (solvedTiles.size < tiles.size) {

            val newSolved = mutableMapOf<Point, Tile>()
            val unsolvedTiles = tiles.filter { tile -> tile.id !in solvedTiles.values.map { it.id } }

            solvedTiles.forEach { (point, solvedTile) ->
                neighbours.forEach { neighbour ->
                    val neighbourPoint = point + neighbour.direction
                    if (!solvedTiles.containsKey(neighbourPoint)) {

                        unsolvedTiles.forEach { unsolvedTile ->

                            if (newSolved.isEmpty()) {
                                outer@ for (flip in 1..3) {
                                    for (rotation in 1..5) {
                                        val edge = neighbour.edge(solvedTile.data)
                                        val otherEdge = neighbour.other(unsolvedTile.data)
                                        if (edge == otherEdge) {
                                            newSolved[neighbourPoint] = unsolvedTile
                                            break@outer
                                        }

                                        unsolvedTile.rotate()
                                    }
                                    unsolvedTile.flip()
                                }
                            }
                        }
                    }
                }
            }


            solvedTiles += newSolved
        }

        val minX = solvedTiles.keys.minOf { it.x }
        val maxX = solvedTiles.keys.maxOf { it.x }

        val minY = solvedTiles.keys.minOf { it.y }
        val maxY = solvedTiles.keys.maxOf { it.y }

        val result = listOf(minX, maxX).flatMap { x ->
            listOf(minY, maxY).map { y ->
                solvedTiles[Point(x, y)]!!.id
            }
        }.reduce(Long::times)

        println(result)
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

    data class Tile(val id: Long, var data: TileData) {
        fun flip() = apply { data = data.reversed() }
        fun rotate() = apply { data = (0..9).map { x -> (0..9).map { y -> data[9 - y][x] } } }
        fun print() = data.joinToString("\n") { it.joinToString("") }

        companion object {
            fun edgeTop(data: TileData) = data[0].joinToString("")
            fun edgeBottom(data: TileData) = data[9].joinToString("")
            fun edgeLeft(data: TileData) = (0..9).map { y -> data[y][0] }.joinToString("")
            fun edgeRight(data: TileData) = (0..9).map { y -> data[y][9] }.joinToString("")
        }
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)
    }

    data class Neighbour(val direction: Point, val edge: (TileData) -> String, val other: (TileData) -> String)
}

fun main() {
    val input = Utils.readText("day20.txt")
    val day = Day20(input)
//    println(day.tiles[0].print())
//    println()

//    println(Day20.Tile.edgeLeft(day.tiles[0].data))
//    println(Day20.Tile.edgeRight(day.tiles[0].data))
//    println(Day20.Tile.edgeTop(day.tiles[0].data))
//    println(Day20.Tile.edgeBottom(day.tiles[0].data))


    day.solve()

}