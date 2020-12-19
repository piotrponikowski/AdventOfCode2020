import java.lang.System.lineSeparator

class Day19(input: String) {

    val messages = parseMessages(input)
    val rules = parseRules(input)

    fun solve1() = messages.count { message -> matchRules(message, listOf(0), rules) }

    fun solve2() = rules.let { oldRules -> oldRules + listOf(8 to "42 | 42 8", 11 to "42 31 | 42 11 31") }
        .let { newRules -> messages.count { message -> matchRules(message, listOf(0), newRules) } }


    private fun matchRules(message: String, refs: List<Int>, rules: Map<Int, String>): Boolean {
        if (message.isEmpty()) {
            return refs.isEmpty()
        } else if (refs.isEmpty()) {
            return false
        }

        val logic = rules.getValue(refs.first())
        val remainingRefs = refs.drop(1)

        return if (logic[1] in listOf('a', 'b')) {
            if (message.startsWith(logic[1])) matchRules(message.drop(1), remainingRefs, rules)
            else false
        } else {
            logic.split(" | ").any { nextLogic ->
                nextLogic.split(" ").map { it.toInt() }
                    .let { nextRefs -> matchRules(message, nextRefs + remainingRefs, rules) }
            }
        }
    }

    companion object {

        fun parseRules(input: String) = splitInput(input, 0)
            .map { line -> line.split(":") }
            .map { (id, logic) -> id.toInt() to logic.trim() }
            .toMap()

        fun parseMessages(input: String) = splitInput(input, 1)

        private fun splitInput(input: String, group: Int) = input.split(lineSeparator().repeat(2))[group]
            .split(lineSeparator())
    }
}