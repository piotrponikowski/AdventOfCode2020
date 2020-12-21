import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe

class Day21Test : FunSpec({

    val exampleInput = Utils.readLines("day21.txt", true)
    val realInput = Utils.readLines("day21.txt")

    context("Part 1") {

        test("should parse example") {
            val day = Day21(exampleInput)
            assertSoftly(day) {
                meals shouldContain Day21.Meal(setOf("mxmxvkd", "kfcds", "sqjhc", "nhms"), setOf("dairy", "fish"))
                meals shouldContain Day21.Meal(setOf("trh", "fvjkl", "sbzzf", "mxmxvkd"), setOf("dairy"))
                meals shouldContain Day21.Meal(setOf("sqjhc", "fvjkl"), setOf("soy"))
                meals shouldContain Day21.Meal(setOf("sqjhc", "mxmxvkd", "sbzzf"), setOf("fish"))

                allergens shouldBe setOf("dairy", "fish", "soy")
                ingredients shouldBe setOf("mxmxvkd", "kfcds", "sqjhc", "nhms", "trh", "fvjkl", "sbzzf")
            }
        }

        test("should match allergens for example") {
            val matchedAllergens = Day21(exampleInput).matchAllergens()
            assertSoftly(matchedAllergens) {
                shouldContain("dairy" to "mxmxvkd")
                shouldContain("fish" to "sqjhc")
                shouldContain("soy", "fvjkl")
            }
        }

        test("should solve example") {
            Day21(exampleInput).solve1() shouldBe 5
        }

        test("should solve real input") {
            Day21(realInput).solve1() shouldBe 2584
        }
    }

    context("Part 2") {

        test("should solve example") {
            Day21(exampleInput).solve2() shouldBe "mxmxvkd,sqjhc,fvjkl"
        }

        test("should solve real input") {
            Day21(realInput).solve2() shouldBe "fqhpsl,zxncg,clzpsl,zbbnj,jkgbvlxh,dzqc,ppj,glzb"
        }
    }
})