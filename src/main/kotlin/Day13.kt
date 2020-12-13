class Day13

fun main() {
//    val input = Utils.readLines("day13.txt")
//    val ts = input[0].toInt()
//    val ids = input[1].split(",").filter { it != "x" }.map { it.toInt() }
//
//    val result = ids.map {
//        val mod = (ts % it)
//        val result = if (mod == 0) 0 else it - mod
//        it to result
//    }
//        .sortedBy { it.second }
//        .first().let { it.first * it.second}
//
//
//    println(ts)
//    println(ids)
//    println(result)

    val input = Utils.readLines("day13.txt")

    val test = input[1].split(",").mapIndexed { index, id -> index to id }.filter { (index, id) -> id != "x" }
        .map { (index, id) -> index to id.toInt() }

    var t = 0L
    var step = test[0].second.toLong()
    var next = 1

    while(next < test.size) {
        t += step
        if((t + test[next].first) % test[next].second == 0L) {
            step = lcm(step, test[next++].second.toLong())
        }
    }

    println(test)
    println(t)


}
