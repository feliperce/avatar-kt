package io.github.feliperce.avatar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val RING_SIZE = 90f

fun generateRingColors(name: String, colors: List<Color>): List<Color> {
    val numFromName = AvatarUtils.hashCode(name)
    val range = colors.size
    val colorsShuffle = List(5) { i ->
        AvatarUtils.getRandomColor(numFromName + i, colors, range)
    }
    return listOf(
        colorsShuffle[0],
        colorsShuffle[1],
        colorsShuffle[1],
        colorsShuffle[2],
        colorsShuffle[2],
        colorsShuffle[3],
        colorsShuffle[3],
        colorsShuffle[0],
        colorsShuffle[4]
    )
}

@Composable
fun AvatarRing(
    name: String,
    colors: List<Color>,
    size: Dp = 40.dp,
    square: Boolean = false,
    modifier: Modifier = Modifier
) {
    val ringColors = remember(name, colors) { generateRingColors(name, colors) }
    val shape = if (square) RoundedCornerShape(0.dp) else CircleShape

    Canvas(modifier = modifier.size(size).clip(shape)) {
        val scaleFactor = this.size.width / RING_SIZE

        scale(scaleFactor, pivot = Offset.Zero) {
            drawRect(color = ringColors[0], topLeft = Offset(0f, 0f), size = Size(90f, 45f))
            drawRect(color = ringColors[1], topLeft = Offset(0f, 45f), size = Size(90f, 45f))
            
            val path2 = Path().apply {
                moveTo(83f, 45f)
                arcTo(Rect(Offset(45f, 45f), 38f), 0f, -180f, false)
                close()
            }
            drawPath(path = path2, color = ringColors[2])

            val path3 = Path().apply {
                moveTo(83f, 45f)
                arcTo(Rect(Offset(45f, 45f), 38f), 0f, 180f, false)
                close()
            }
            drawPath(path = path3, color = ringColors[3])

            val path4 = Path().apply {
                moveTo(77f, 45f)
                arcTo(Rect(Offset(45f, 45f), 32f), 0f, -180f, false)
                close()
            }
            drawPath(path = path4, color = ringColors[4])

            val path5 = Path().apply {
                moveTo(77f, 45f)
                arcTo(Rect(Offset(45f, 45f), 32f), 0f, 180f, false)
                close()
            }
            drawPath(path = path5, color = ringColors[5])

            val path6 = Path().apply {
                moveTo(71f, 45f)
                arcTo(Rect(Offset(45f, 45f), 26f), 0f, -180f, false)
                close()
            }
            drawPath(path = path6, color = ringColors[6])

            val path7 = Path().apply {
                moveTo(71f, 45f)
                arcTo(Rect(Offset(45f, 45f), 26f), 0f, 180f, false)
                close()
            }
            drawPath(path = path7, color = ringColors[7])

            drawCircle(color = ringColors[8], radius = 23f, center = Offset(45f, 45f))
        }
    }
}
