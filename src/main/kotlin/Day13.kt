class Day13(input: List<String>) {

    val timestamp = input.first().toLong()
    val buses = input.last().split(",")
        .mapIndexedNotNull { index, data -> data.takeIf { it != "x" }?.let { Bus(index, data.toLong()) } }

    fun solve1() = buses.map { (index, id) -> id to id - (timestamp % id) }
        .minByOrNull { it.second }!!
        .let { it.first * it.second }

    fun solve2(): Long {
        var time = 0L
        var step = buses.first().id

        buses.drop(1).forEach { bus ->
            while ((time + bus.index) % bus.id != 0L) {
                time += step
            }
            step = lcm(step, bus.id)
        }

        return time
    }

    data class Bus(val index: Int, val id: Long)
}