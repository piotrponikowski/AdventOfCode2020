class Day13(input: List<String>) {

    val timestamp = input.first().toLong()
    val buses = input.last().split(",")
        .mapIndexedNotNull { index, data -> data.takeIf { it != "x" }?.let { Bus(index, data.toLong()) } }

    fun solve1() = buses.map { (_, id) -> id to id - (timestamp % id) }
        .minByOrNull { (_, diff) -> diff }!!
        .let { (index, diff) -> index * diff }

    fun solve2() = buses.drop(1)
        .fold(0L to buses.first().id) { (time, step), bus ->
            generateSequence(time) { it + step }
                .first { (it + bus.index) % bus.id == 0L }
                .let { it to step * bus.id }
        }.let { (time, step) -> time }

    data class Bus(val index: Int, val id: Long)
}