class Day24(input: List<String>) {

    val tileDirections = input.map { parseDirections(it) }

    val neighbours = mapOf(
        "e" to Point(2, 0), "se" to Point(1, 1), "ne" to Point(1, -1),
        "w" to Point(-2, 0), "sw" to Point(-1, 1), "nw" to Point(-1, -1),
    )

    fun solve1(): Int {
        val referenceTile = Point(0, 0)
        val blackTiles = mutableSetOf<Point>()

        tileDirections.forEach { directions ->
            val targetTile = directions.fold(referenceTile) { tile, dir -> tile + neighbours[dir]!! }
            val isBlack = blackTiles.contains(targetTile)
            if (isBlack) {
                blackTiles.remove(targetTile)
            } else {
                blackTiles.add(targetTile)
            }
        }

        return blackTiles.count()
    }

    companion object {
        private val PATTERN_DIRECTION = Regex("e|se|sw|w|nw|ne")

        fun parseDirections(input: String) = PATTERN_DIRECTION.findAll(input).map { it.value }.toList()
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)
    }
}

fun main() {
    val input = Utils.readLines("day24.txt")
    val day = Day24(input)
    val test = Day24.parseDirections("nwwswee")
    println(test)
    println(day.solve1())

}