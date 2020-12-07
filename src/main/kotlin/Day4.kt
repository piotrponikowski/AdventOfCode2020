import java.lang.System.lineSeparator

class Day4(private val input: String) {

    fun parse() = input.split(lineSeparator().repeat(2))
        .map { batch ->
            batch.split(Regex("\\s"))
                .filter { it.isNotBlank() }
                .map { data -> data.split(":") }
                .associateBy({ it[0] }, { it[1] })
        }.map { Passport(it) }

    fun solve1() = parse().filter { it.hasMandatoryFields() }.count()

    fun solve2() = parse().filter { it.isValid() }.count()

    class Passport(data: Map<String, String>) {
        val fields = data.mapValues { (key, value) -> Field(key, value) }

        fun hasMandatoryFields() = mandatoryTypes.all { fields.containsKey(it) }
        fun isValid() = mandatoryTypes.all { fields[it]?.isValid() ?: false }

        companion object {
            val mandatoryTypes = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        }
    }

    class Field(private val key: String, private val value: String) {
        fun isValid() = when (key) {
            "byr" -> value.toInt() in 1920..2002
            "iyr" -> value.toInt() in 2010..2020
            "eyr" -> value.toInt() in 2020..2030
            "hgt" -> value.let {
                val (height, unit) = Regex("^(\\d+)(cm|in)$").matchEntire(it)?.destructured ?: return false
                when (unit) {
                    "cm" -> height.toInt() in 150..193
                    "in" -> height.toInt() in 59..76
                    else -> false
                }
            }
            "hcl" -> value.matches(Regex("^#[0-9a-f]{6}\$"))
            "ecl" -> value.matches(Regex("^amb|blu|brn|gry|grn|hzl|oth$"))
            "pid" -> value.matches(Regex("^[0-9]{9}$"))
            else -> true
        }
    }
}