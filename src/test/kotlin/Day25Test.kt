import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day25Test :FunSpec ({

    val exampleInput = Utils.readLines("day25.txt", true)
    val realInput = Utils.readLines("day25.txt")

    context("Part 1") {
        test("should calculate loop size") {
            Day25.loopSize(5764801) shouldBe 8
            Day25.loopSize(17807724) shouldBe 11
        }

        test("should calculate encryption key") {
            Day25.encryptionKey(5764801, 11) shouldBe 14897079
            Day25.encryptionKey(17807724, 8) shouldBe 14897079
        }

        test("should solve example") {
            Day25(exampleInput).solve1() shouldBe 14897079
        }

        test("should solve real input") {
            Day25(realInput).solve1() shouldBe 290487
        }
    }
})