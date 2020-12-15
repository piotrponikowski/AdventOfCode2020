class Day10(input: List<String>) {

    private val adapters = input.map { it.toInt() }

    fun solve1() = adapters.sorted().zipWithNext()
        .count { (curr, next) -> next - curr == 1 }
        .let { (it + 1) * (adapters.size - it) }

    fun solve2(): Long = adapters.associateWith { 0L }
        .toSortedMap().apply { put(0, 1) }
        .also { map ->
            map.forEach { (index, count) ->
                adapters.filter { adapter -> adapter > index && adapter - 3 <= index }
                    .forEach { adapter -> map.merge(adapter, count, Long::plus) }
            }
        }.values.last()

}
