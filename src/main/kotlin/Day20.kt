import java.lang.System.lineSeparator

class Day20(input: String) {

    val tiles = parseTiles(input)

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

    data class Tile(val id: Long, var data: List<List<Char>>) {
        fun flip() = apply { data = data.reversed() }

        fun rotate() = apply {
            var copy = data.map { row -> row.map { cell -> cell }.toMutableList() }.toMutableList()
            (0..9).forEach { x ->
                (0..9).forEach { y ->
                    copy[y][9 - x] = data[x][y]
                }
            }
            data = copy
        }

        fun print() = data.joinToString("\n") { it.joinToString("") }


    }
}

fun main() {
    val input = Utils.readText("day20.txt", true)
    val day = Day20(input)

    println(day.tiles[0].print())
    println()
    (1..4).forEach {
        println(day.tiles[0].rotate().print())
        println()
    }

//    println(day.tiles[0].print())
//    println()
//    println(day.tiles[0].flip().print())


}