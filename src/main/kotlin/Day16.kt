import java.lang.System.lineSeparator

class Day16(input: String) {

    private val fields = parseFields(input)
    private val myTicket = parseMyTicket(input)
    private val nearbyTickets = parseNearbyTickets(input)

    fun solve1(): Int {
        val validRanges = fields.map { it.validValues }.reduce { a, b -> a + b }
        return nearbyTickets.map { ticket -> ticket.values.filter { value -> value !in validRanges }.sum() }.sum()
    }

    fun solve2() = solveFields().filter { it.key.startsWith("departure") }
        .map { myTicket.values[it.value].toLong() }
        .reduce { a, b -> a * b }

    fun solveFields(): Map<String, Int> {
        val validRanges = fields.map { it.validValues }.reduce { a, b -> a + b }
        val validTickets = nearbyTickets.filter { ticket -> ticket.values.all { value -> value in validRanges } }

        val solvedFields = mutableMapOf<String, Int>()
        while (solvedFields.size < fields.size) {

            for (fieldIndex in fields.indices) {
                val values = validTickets.map { ticket -> ticket.values[fieldIndex] }
                val matchedFields = fields
                    .filter { field -> values.all { value -> value in field.validValues } }
                    .filter { field -> !solvedFields.containsKey(field.name) }

                if (matchedFields.size == 1) {
                    solvedFields[matchedFields[0].name] = fieldIndex
                }
            }
        }
        return solvedFields
    }

    companion object {

        private val PATTERN_FIELD = Regex("""^(.*): (\d+)-(\d+) or (\d+)-(\d+)$""")

        fun parseFields(input: String): List<Field> = splitInput(input, 0)
            .map { PATTERN_FIELD.matchEntire(it)!!.destructured }
            .map { (name, a, b, c, d) -> Field(name, (a.toInt()..b.toInt()) + (c.toInt()..d.toInt())) }

        fun parseMyTicket(input: String) = splitInput(input, 1).last()
            .let { line -> line.split(",").map { number -> number.toInt() } }.let { values -> Ticket(values) }

        fun parseNearbyTickets(input: String) = splitInput(input, 2).drop(1)
            .map { line -> line.split(",").map { number -> number.toInt() } }.map { values -> Ticket(values) }

        private fun splitInput(input: String, group: Int) = input.split(lineSeparator().repeat(2))[group]
            .split(lineSeparator())
    }

    data class Field(val name: String, val validValues: List<Int>)
    data class Ticket(val values: List<Int>)
}
