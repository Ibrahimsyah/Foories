package academy.bangkit.capstonk.foories.core.util

fun String.capitalizeWords(delimiter: String): String =
    split(delimiter).joinToString(" ") { it.replaceFirstChar { first -> first.uppercase() } }
