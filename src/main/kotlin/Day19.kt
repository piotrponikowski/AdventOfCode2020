class Day19(input: List<String>) {

    val messages = parseMessages(input)
    val rules = parseRules(input)
    val refs = parseRefs(input)

    fun solve1(): Int {
        solveRules()
        return messages.count { it in rules[0]!!.values }
    }

    fun solveRules() {

        while (refs.isNotEmpty()) {
            val canBeSolved = refs.values.filter { ref -> ref.logics.all { logic -> rules.keys.containsAll(logic) } }
            rules += canBeSolved.map { ref ->
                val values = ref.logics.flatMap { logic ->
                    logic.fold(listOf("")) { result, id -> combine(result, rules[id]!!.values) }
                }
                ref.id to Rule(ref.id, values)
            }

            refs -= canBeSolved.map { it.id }
//            println(canBeSolved)
        }
    }

//    fun refToValues(logic: List<Int>, result: List<String> = listOf("")): List<String> {
//        if (logic.isEmpty()) return result
//        else refToValues(logic.drop(1), result.map { })
//    }

    fun combine(list1: List<String>, list2: List<String>): List<String> {
        val result = mutableListOf<String>()
        list1.forEach { element1 ->
            list2.forEach { element2 ->
                result += element1 + element2
            }
        }
        return result
    }

    companion object {
        private val PATTERN_MESSAGE = Regex("[ab]+")
        private val PATTERN_RULE = Regex("([0-9]+): \"([ab]+)\"")
        private val PATTERN_REF = Regex("([0-9]+): ([0-9 |]+)")

        fun parseMessages(input: List<String>) = input.filter { it.matches(PATTERN_MESSAGE) }

        fun parseRules(input: List<String>) = input.filter { it.matches(PATTERN_RULE) }
            .map { PATTERN_RULE.matchEntire(it)!!.destructured }
            .map { (id, value) -> id.toInt() to Rule(id.toInt(), listOf(value)) }
            .toMap().toMutableMap()

        fun parseRefs(input: List<String>) = input.filter { it.matches(PATTERN_REF) }
            .map { PATTERN_REF.matchEntire(it)!!.destructured }
            .map { (id, logic) -> id.toInt() to logic.split(" | ").map { it.split(" ").map { it.toInt() } } }
            .map { (id, refs) -> id to Ref(id, refs) }
            .toMap().toMutableMap()
    }

    data class Rule(val id: Int, val values: List<String>)
    data class Ref(val id: Int, val logics: List<List<Int>>)
}

fun main() {
    val input = Utils.readLines("day19.txt")
    val day = Day19(input).solve1()


//    val result = Day19(input).combine(listOf("a", "b"), listOf("c", "d", "e"))

    println(day)

}