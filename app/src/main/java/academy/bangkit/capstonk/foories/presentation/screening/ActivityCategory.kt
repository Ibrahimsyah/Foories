package academy.bangkit.capstonk.foories.presentation.screening

data class ActivityCategory(
    val id: Int,
    val title: String,
    val description: String
) {
    companion object {
        fun generateData(): List<ActivityCategory> {
            return listOf(
                ActivityCategory(
                    1,
                    "Sedentary",
                    "Little or no exercise"
                ),
                ActivityCategory(
                    2,
                    "Lightly active light",
                    "Exercise/sports 1-3 days/week"
                ),
                ActivityCategory(
                    3,
                    "Moderately active",
                    "Moderate exercise/sports 3-5 days/week"
                ),
                ActivityCategory(
                    4,
                    "Very active",
                    "Hard exercise/sports 6-7 days a week"
                ),
                ActivityCategory(
                    5,
                    "Extra active",
                    "Very hard exercise/sports & a physical job"
                ),
            )
        }
    }
}