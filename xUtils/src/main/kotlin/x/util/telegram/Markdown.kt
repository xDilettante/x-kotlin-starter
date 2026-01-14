package x.util.telegram

private val markdownV2Chars = Regex("""[_*\[\]()~`>#+\-=|{}.!]""")

fun String.md(): String = replace(markdownV2Chars) { "\\${it.value}" }
