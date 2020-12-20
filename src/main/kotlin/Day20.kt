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

    data class Tile(val id: Long, val data: List<List<Char>>) {
        val edges = run {
            val e1 = (0..9).map { x -> data[x][0] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e2 = (0..9).map { x -> data[x][9] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e3 = (0..9).map { y -> data[0][y] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e4 = (0..9).map { y -> data[9][y] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            listOf(e1, e2, e3, e4)
        }

        val edgesFlipped1 = run {
            val e1 =
                (0..9).reversed().map { x -> data[x][0] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e2 =
                (0..9).reversed().map { x -> data[x][9] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e3 = (0..9).map { y -> data[0][y] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e4 = (0..9).map { y -> data[9][y] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            listOf(e1, e2, e3, e4)
        }

        val edgesFlipped2 = run {
            val e1 = (0..9).map { x -> data[x][0] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e2 = (0..9).map { x -> data[x][9] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e3 =
                (0..9).reversed().map { y -> data[0][y] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            val e4 =
                (0..9).reversed().map { y -> data[9][y] }.map { if (it == '#') '1' else '0' }.joinToString("").toLong(2)
            listOf(e1, e2, e3, e4)
        }


    }
}

fun main() {
    val input = Utils.readText("day20.txt")
    val day = Day20(input)
    day.tiles.forEach { println("${it.id} ${it.edges} ${it.edgesFlipped1} ${it.edgesFlipped2}") }

    val ids = day.tiles.flatMap { it.edges }
    val corners = day.tiles.filter { tile ->
        tile.edges.count { edge ->
            edge in (day.tiles.filter { it.id != tile.id }.flatMap { it.edges }) ||
            edge in (day.tiles.filter { it.id != tile.id }.flatMap { it.edgesFlipped1 }) ||
            edge in (day.tiles.filter { it.id != tile.id }.flatMap { it.edgesFlipped2 })
        } == 2
    }
    corners.forEach { println(it.id) }

    corners.map { it.id }.reduce { a, b -> a * b }.let { println(it) }

}