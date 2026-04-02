package io.github.feliperce.avatar.util

import androidx.compose.ui.graphics.Color
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.pow

/**
 * Utility object for generating predictable pseudorandom data based on a given string name.
 * It is primarily used to ensure that a given name consistently generates the same avatar colors and shapes.
 */
internal object AvatarUtils {

    /**
     * Generates a positive hash code for a given string name.
     * @param name The input string to hash.
     * @return A positive corresponding integer hash.
     */
    fun hashCode(name: String): Int {
        var hash = 0
        for (character in name) {
            hash = ((hash shl 5) - hash) + character.code
        }
        return abs(hash)
    }

    /**
     * Extracts a specific digit from a number based on an exponential scale.
     * @param number The number to extract the digit from.
     * @param ntn The exponent (power of 10) deciding the specific position.
     * @return The extracted digit as an integer.
     */
    fun getDigit(number: Int, ntn: Int): Int {
        return floor((number / 10.0.pow(ntn)) % 10).toInt()
    }

    /**
     * Determines a boolean value algorithmically from the number.
     * @param number The base number.
     * @param ntn Exponent location to base the boolean decision on.
     * @return True if the digit at the specified position is even, false otherwise.
     */
    fun getBoolean(number: Int, ntn: Int): Boolean {
        return (getDigit(number, ntn) % 2) == 0
    }

    /**
     * Returns a constrained pseudorandom unit based on a number and maximum range.
     * @param number The base hash number.
     * @param range The maximum bound for the calculated value.
     * @param index Optional exponential index to determine the sign.
     * @return The adjusted unit integer.
     */
    fun getUnit(number: Int, range: Int, index: Int? = null): Int {
        val value = number % range
        return if (index != null && (getDigit(number, index) % 2) == 0) {
            -value
        } else {
            value
        }
    }

    /**
     * Randomly picks a color from a provided list based on the generated number.
     * @param number The base integer for pseudorandom selection.
     * @param colors The list of available colors.
     * @param range The modulus range (typically the size of the color list).
     * @return The randomly selected Color.
     */
    fun getRandomColor(number: Int, colors: List<Color>, range: Int): Color {
        if (colors.isEmpty()) return Color.Black
        return colors[number % range]
    }

    /**
     * Determines the optimal contrast color (either Black or White) for a given color.
     * @param color The background color.
     * @return [Color.Black] or [Color.White] depending on the lightness of the background.
     */
    fun getContrast(color: Color): Color {
        val r = color.red * 255
        val g = color.green * 255
        val b = color.blue * 255
        val yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000
        return if (yiq >= 128) Color.Black else Color.White
    }
}
