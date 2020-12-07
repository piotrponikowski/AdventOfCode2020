import java.lang.System.lineSeparator

class Day6(val input: String) {

    fun solve1() = input.split(lineSeparator().repeat(2))
        .map { it.replace(lineSeparator(), "") }
        .map { it.toSet().count() }
        .sum()

    fun solve2() = input.split(lineSeparator().repeat(2))
        .map { it.split(lineSeparator()) }
        .map { group -> group.map { it.toSet() }.reduce { a, b -> a.intersect(b) }.count() }
        .sum()
}
