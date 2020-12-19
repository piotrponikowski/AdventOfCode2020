import java.lang.System.lineSeparator

class Day19(input: String) {

    val messages = parseMessages(input)
    val rules1 = parseRules(input)
    val rules2 = rules1 + listOf(8 to "42 | 42 8", 11 to "42 31 | 42 11 31")

    fun solve1() = messages.count { message -> matchRules(message, listOf(0)) }

    fun solve2() = messages.count { message -> matchRules(message, listOf(0)) }


    fun matchRules(message: String, messageRules: List<Int>): Boolean {
//        println("#### $message, rules: $messageRules")
        if (message.isEmpty()) {
            return messageRules.isEmpty()
        } else if (messageRules.isEmpty()) {
            return false
        }

        rules2.getValue(messageRules.first()).let { logic ->
            if (logic[1] in 'a'..'b') {
                return if (message.startsWith(logic[1])) {
//                    println("matching: $message rules: $rules")
                    matchRules(message.drop(1), messageRules.drop(1))
                } else {
                    false
                }
            } else {
                return logic.split(" | ").any { nextRules ->
                    matchRules(message, nextRules.split(" ").map { it.toInt() } + messageRules.drop(1))
                }
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

fun main() {
    val input = Utils.readText("day19.txt")
    val day = Day19(input).solve1()


    println(day)

}