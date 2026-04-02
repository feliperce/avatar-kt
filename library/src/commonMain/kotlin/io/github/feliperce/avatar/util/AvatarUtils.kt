package io.github.feliperce.avatar.util

import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.floor
import kotlin.math.pow

internal object AvatarUtils {
    fun hashCode(name: String): Int {
        var hash = 0
        for (character in name) {
            hash = ((hash shl 5) - hash) + character.code
        }
        return abs(hash)
    }

    fun getModulus(num: Int, max: Int): Int {
        return if (max == 0) 0 else num % max
    }

    fun getDigit(number: Int, ntn: Int): Int {
        return floor((number / 10.0.pow(ntn)) % 10).toInt()
    }

    fun getBoolean(number: Int, ntn: Int): Boolean {
        return (getDigit(number, ntn) % 2) == 0
    }

    fun getAngle(x: Float, y: Float): Float {
        return (atan2(y.toDouble(), x.toDouble()) * 180 / PI).toFloat()
    }

    fun getUnit(number: Int, range: Int, index: Int? = null): Int {
        val value = number % range
        return if (index != null && (getDigit(number, index) % 2) == 0) {
            -value
        } else {
            value
        }
    }

    fun getRandomColor(number: Int, colors: List<Color>, range: Int): Color {
        if (colors.isEmpty()) return Color.Black
        return colors[number % range]
    }

    fun getContrast(color: Color): Color {
        val r = color.red * 255
        val g = color.green * 255
        val b = color.blue * 255
        val yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000
        return if (yiq >= 128) Color.Black else Color.White
    }
}
