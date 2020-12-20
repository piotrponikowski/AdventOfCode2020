import java.lang.Math.*
import java.lang.System.lineSeparator
import kotlin.math.abs

typealias TileData = List<List<Char>>

class Day20(input: String) {

    val tiles = parseTiles(input)

    val neighbours = listOf(
        Neighbour(Point(-1, 0), Tile::edgeLeft, Tile::edgeRight),
        Neighbour(Point(1, 0), Tile::edgeRight, Tile::edgeLeft),
        Neighbour(Point(0, -1), Tile::edgeTop, Tile::edgeBottom),
        Neighbour(Point(0, 1), Tile::edgeBottom, Tile::edgeTop)
    )

    val monster = arrayOf(
        "                  # ",
        "#    ##    ##    ###",
        " #  #  #  #  #  #   "
    )

    val monsterPoints = monster.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, cell -> Point(x, y).takeIf { cell == '#' } }
    }

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
                                outer@ for (flip in 1..2) {
                                    for (rotation in 1..4) {
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

        val sizeX = abs(minX - maxX) + 1
        val sizeY = abs(minY - maxY) + 1

        val board = MutableList(sizeY * 10) { MutableList<Char>(sizeX * 10) { '0' } }

        solvedTiles.forEach { (point, tile) ->
            tile.data.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    if (x in 1..8 && y in 1..8) {
                        val fx = point.x - minX
                        val fy = point.y - minY
                        board[fy * 10 + y][fx * 10 + x] = cell
                    }
                }
            }
        }

        val trimmedBoard = board.map { line -> line.filter { it != '0' } }.filter { it.isNotEmpty() }

        val final = Tile(0, trimmedBoard)


        println(result)
        println(final.print())

        val monsterSizeX = monsterPoints.maxOf { it.x }
        val monsterSizeY = monsterPoints.maxOf { it.y }


        for (flip in 1..2) {
            for (rotation in 1..4) {
                println(flip)
                println(rotation)
                println(final.print())
                println()

//                if(flip == 1 && rotation == 2) {
//                    println()
//                }

                var monsters = 0
                final.data.forEachIndexed { y, line ->
                    line.forEachIndexed { x, cell ->
//                        if(y == 3 && x == 2) {
//                            println()
//                        }

                        if (x <= final.data[0].size - monsterSizeX && y <= final.data.size - monsterSizeY) {
                            val hasMonster = monsterPoints.all { point -> final.data[y + point.y][x + point.x] == '#' }
                            if (hasMonster) {
                                monsters++
                            }
                        }
                    }
                }

                if (monsters > 0) {
                    val water = final.data.sumBy { it.count { it == '#' } }
                    val occupied = monsters * monsterPoints.size
                    println("monsters: $monsters, water: $water, occupied: $occupied, result: ${water - occupied}")

                }
                final.rotate()
            }
            final.flip()
        }

//        println(monsterPoints)
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
        fun rotate() =
            apply { data = (data.indices).map { x -> (data.indices).map { y -> data[data.size - y - 1][x] } } }

        fun print() = data.joinToString("\n") { it.joinToString("") }

        companion object {
            fun edgeTop(data: TileData) = data[0].joinToString("")
            fun edgeBottom(data: TileData) = data[data.size - 1].joinToString("")
            fun edgeLeft(data: TileData) = (data.indices).map { y -> data[y][0] }.joinToString("")
            fun edgeRight(data: TileData) = (data.indices).map { y -> data[y][data.size - 1] }.joinToString("")
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