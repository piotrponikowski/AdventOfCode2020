import java.lang.System.lineSeparator

class Day14(input: String) {

    val pattern = Regex("([0-9]+).*?([0-9]+)")

    val groups = input.split("mask = ")
        .filter { it.isNotBlank() }
        .map { it.split(lineSeparator()) }
        .map { group ->
            Group(group.first(), group.drop(1).filter { it.isNotBlank() }.map { pattern.find(it)!!.destructured }
                .map { (memory, value) -> Instruction(memory.toLong(), value.toLong()) })
        }

    fun solve1(): Long {
        val memory = mutableMapOf<Long, Long>()

        groups.forEach { group ->
            group.instructions.forEach { instruction ->
                memory[instruction.address] = (instruction.value and group.maskOn()) or group.maskOff()
            }
        }

        println(memory)

        return memory.values.sum()
    }

    fun solve2(): Long {
        val memory = mutableMapOf<Long, Long>()

        groups.take(1).forEach { group ->
            val addresses = group.options().map { it.toLong(2) }

            group.instructions.forEach { instruction ->
                addresses.forEach { address ->
                    println(address.toString(2).padStart(32, '0'))
                    println(instruction.address.toString(2).padStart(32, '0'))
                    println(group.maskOn().toString(2).padStart(32, '0'))
                    println(group.maskOff().toString(2).padStart(32, '0'))
println()
                    address.toULong().inv()

                    val test = (instruction.value or group.maskOn())

                    memory[address or test] = instruction.value
                }
            }
        }

        println(memory)
        return memory.values.sum()
    }


    data class Group(val mask: String, val instructions: List<Instruction>) {
        fun maskOff() = mask.replace("X", "0").toLong(2)
        fun maskOn() = mask.replace("X", "1").toLong(2)
        fun options(next: String = mask): List<String> {
            return if (!next.contains("X")) listOf(next)
            else options(next.replaceFirst("X", "1")) + options(next.replaceFirst("X", "0"))
        }
    }

    data class Instruction(val address: Long, val value: Long)
}