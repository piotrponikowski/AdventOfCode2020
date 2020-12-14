import java.lang.System.lineSeparator

class Day14(input: List<String>) {


    data class Group(val mask: String, val instructions: List<Instruction>) {
        fun constantPart() = mask.replace("X", "0").toLong(2)
        fun dynamicPart() = mask.replace("1", "0").replace("X", "1").toLong(2)
        fun addressPart() = mask.replace("0", "1").replace("X", "0").toLong(2)


        fun combinations() = sequence<Long> {

            val x = mask.toCharArray().mapIndexed { index, value -> index to value }.filter { it.second == 'X' }
            val max = 1L shl x.size
            for (c in 0L until max) {
                var result = "0".repeat(36).toCharArray()
                val digits = c.toString(2).padStart(x.size, '0')
                x.forEachIndexed { index, (position) -> result[position] = digits[index] }

                yield(result.joinToString("").toLong(2))
            }
        }
    }

    data class Instruction(val address: Long, val value: Long)

}

fun main() {
    val input = Utils.readText("day14.txt")


    val pattern = Regex("([0-9]+).*?([0-9]+)")

//    val (memory, value) = pattern.matchEntire("mem[5201] = 1838761")!!.destructured
//    println(memory)
//    println(value)


    val groups = input.split("mask = ")
        .filter { it.isNotBlank() }
        .map { it.split(lineSeparator()) }
        .map { group ->
            Day14.Group(group.first(), group.drop(1).filter { it.isNotBlank() }.map { pattern.find(it)!!.destructured }
                .map { (memory, value) -> Day14.Instruction(memory.toLong(), value.toLong()) })
        }

//    val ram = mutableMapOf<Long, Long>()
//
//    groups.forEach { group ->
//        group.instructions.forEach { instruction ->
//            ram[instruction.memory] = (instruction.value and group.dynamicPart()) or group.constantPart()
//        }
//    }
//
//    println(ram.values.sum())

//    println(groups[0])
//
//    println(groups[0].combinations().toList())

    val ram = mutableMapOf<Long, Long>()
    groups.forEach { group ->
        group.instructions.forEach { instruction ->
            run {
//                println(instruction.address.toString(2))
//                println(group.addressPart().toString(2))

                val constantPart = (instruction.address and group.addressPart()) or group.constantPart()
                group.combinations().forEach { c ->
                    val addr = c or constantPart
                    ram[addr] = instruction.value
                }
            }
        }
    }

    println(ram)
    println(ram.values.sum())


}