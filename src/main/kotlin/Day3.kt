class Day3(input: List<String>) {

    private val slope = input.map { it.toCharArray() }.toTypedArray()

    fun solve1() = countTrees(3)

    fun solve2() = countTrees(1) * countTrees(3) * countTrees(5) * countTrees(7) * countTrees(1, 2)

    fun countTrees(right: Int, down: Int = 1) =
        slope.filterIndexed { index, _ -> index % down == 0 }
            .filterIndexed { index, trees -> trees[(index * right) % trees.size] == '#' }
            .count().toLong()
}