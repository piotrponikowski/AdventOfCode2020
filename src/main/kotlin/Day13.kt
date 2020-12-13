class Day13(input: List<String>) {

    val timestamp = input[0].toLong()
    val buses = input[1].split(",").mapIndexed { index, data -> index to data }
        .filter { it.second != "x" }
        .map { (index, data) -> index to data.toLong() }

    fun solve1() = buses.map { (index, id) -> id to id - (timestamp % id) }
        .minByOrNull { it.second }!!
        .let { it.first * it.second }

    fun solve2(): Long  {
        var t = 0L
        var step = buses[0].second
        var next = 1

        while (next < buses.size) {
            t += step
            if ((t + buses[next].first) % buses[next].second == 0L) {
                step = lcm(step, buses[next++].second.toLong())
            }
        }

        return t
    }

}
