class Day10(input: List<String>) {

    private val adapters = input.map { it.toInt() }

    fun solve1() = adapters.sorted().zipWithNext()
        .count { (curr, next) -> next - curr == 1 }
        .let { (it + 1) * (adapters.size - it) }

    fun solve2() : Long {
        val starting = GroupData(0, 1)
        val groups = mutableListOf(starting)
        var result = 0L

        while (groups.isNotEmpty()) {
            println("##### ${groups.map { it.curr }}")
            val group = groups.first().also { groups.remove(it) }
            println("##### ${group.curr} ${group.joined}")

            result = group.joined

            val nextAdapters = adapters.sorted().filter { it > group.curr && it - 3 <= group.curr }

            nextAdapters.forEach { next ->
                val groupToJoin = groups.find { it.curr == next }
                if (groupToJoin != null) {
                    groupToJoin.joined += group.joined
                } else {
                    groups += GroupData(next, group.joined)
                }
            }
        }

        return result
    }


    class GroupData(val curr: Int, var joined: Long)

}




