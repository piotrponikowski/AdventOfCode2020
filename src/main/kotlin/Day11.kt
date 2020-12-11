class Day11(input: List<String>) {

    val board = Board(input)

    fun solve1(): Int {

        val states = mutableSetOf<String>()

        while (!states.contains(board.hash())) {
            states.add(board.hash())
            board.step()
            println(board.print())
        }

        return board.count()
    }

    class Board(input: List<String>) {
        var data = input.map { row -> row.toCharArray() }.toTypedArray()

        fun hash() = data.joinToString { it.joinToString() }

        fun print() = data.joinToString("\n") { it.joinToString("") }

        fun count() = data.sumOf { row -> row.count { it == '#' } }

        fun countOccupied(y: Int, x: Int) =
            (-1..1).sumBy { dy ->
                (-1..1).count { dx ->

                    var occupied = false
                    if(!(dx == 0 && dy == 0)) {
                        var i = 1
                        while (true) {
                            val newx = x + dx * i
                            val newy = y + dy * i


                            if (newx < 0 || newy < 0 || newy >= data.size || newx >= data[0].size) {
                                break
                            } else if (data[newy][newx] == 'L') {
                                break
                            } else if (data[newy][newx] == '#') {
                                occupied = true
                                break
                            }
                            i++
                        }
                    }
                    occupied

//                    when {
//                        dx == 0 && dy == 0 -> false
//                        y + dy < 0 -> false
//                        x + dx < 0 -> false
//                        y + dy >= data.size -> false
//                        x + dx >= data[0].size -> false
//                        data[y + dy][x + dx] == '#' -> true
//                        else -> false
//                    }
                }
            }


        fun step() {
            val next = data.map { it.clone() }.toTypedArray()
            next.forEachIndexed { y, row ->
                row.forEachIndexed { x, col ->
                    if (data[y][x] != '.') {
                        val occupied = countOccupied(y, x)
                        next[y][x] = when (occupied) {
                            0 -> '#'
                            in 5..8 -> 'L'
                            else -> data[y][x]
                        }
                    }
                }
            }

            data = next
        }
    }

}


fun main() {

    val data = Utils.readLines("day11.txt")

    val answer = Day11(data).solve1()
    println(answer)

}