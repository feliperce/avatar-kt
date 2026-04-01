package io.github.feliperce

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform