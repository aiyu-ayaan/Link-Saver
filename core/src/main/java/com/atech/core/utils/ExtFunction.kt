package com.atech.core.utils

fun String.isLink(): Boolean {
    val linkRegex = "^((https?|ftp)://|(www|ftp)\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+([/?].*)?$"
    return Regex(linkRegex).matches(this)
}
