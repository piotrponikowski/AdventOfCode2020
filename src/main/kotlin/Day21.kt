class Day21(input: List<String>) {

    val meals = parseMeals(input)
    val ingredients = meals.flatMap { meal -> meal.ingredients }.toSet()
    val allergens = meals.flatMap { meal -> meal.allergens }.toSet()

    fun solve1() = matchAllergens().let { solvedAllergens -> ingredients - solvedAllergens.values }
        .let { safeIngredients -> meals.sumBy { meal -> meal.ingredients.count { ingredient -> ingredient in safeIngredients } } }

    fun solve2() = matchAllergens().toSortedMap().values.joinToString(",")

    fun matchAllergens(): Map<String, String> {
        val solvedAllergens = mutableMapOf<String, String>()

        while (solvedAllergens.size < allergens.size) {
            allergens.forEach { allergen ->
                val matchedMeals = meals.filter { meal -> allergen in meal.allergens }

                val matchedIngredients = matchedMeals
                    .map { it.ingredients.filter { ingredient -> ingredient !in solvedAllergens.values }.toSet() }
                    .reduce { a, b -> a.intersect(b) }

                if (matchedIngredients.size == 1) {
                    solvedAllergens[allergen] = matchedIngredients.first()
                }
            }
        }

        return solvedAllergens
    }

    companion object {
        private val PATTERN_MEAL = Regex("""([a-z ]+) \(contains ([a-z ,]+)\)""")

        fun parseMeals(input: List<String>) = input.map { PATTERN_MEAL.matchEntire(it)!!.destructured }
            .map { (ingredients, allergens) -> ingredients.split(" ").toSet() to allergens.split(", ").toSet() }
            .map { (ingredients, allergens) -> Meal(ingredients, allergens) }
    }

    data class Meal(val ingredients: Set<String>, val allergens: Set<String>)
}
