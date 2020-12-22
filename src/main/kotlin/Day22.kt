import java.lang.System.lineSeparator
import java.util.*

class Day22(input: String) {

    val decks = parseDecks(input)

    fun play() :Int {
        val (deck1, deck2) = decks

        while(!deck1.isEmpty() && !deck2.isEmpty()) {
            val card1 = deck1.pop()
            val card2 = deck2.pop()

            if (card1 > card2) {
                deck1.addLast(card1)
                deck1.addLast(card2)
            } else {
                deck2.addLast(card2)
                deck2.addLast(card1)
            }
        }

        return deck1.reversed().mapIndexed { index, card ->  card * (index + 1)}.reduce(Int::plus)
    }

    companion object {
        fun parseDecks(input: String) = input.split(lineSeparator().repeat(2))
            .map { data -> data.split(lineSeparator()).drop(1).map { it.toInt() } }
            .map { cards -> ArrayDeque<Int>().apply { addAll(cards) } }
    }
}

fun main() {

    val input = Utils.readText("day22.txt")
    val day = Day22(input)
    println(day.decks)
    val answer = day.play()
    println(day.decks)
    println(answer)



}