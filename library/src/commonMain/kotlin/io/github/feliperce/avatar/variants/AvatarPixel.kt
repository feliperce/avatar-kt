package io.github.feliperce.avatar.variants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import io.github.feliperce.avatar.util.AvatarUtils
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.PreviewLightDark

private const val PIXEL_SIZE = 80f
private const val PIXEL_ELEMENTS = 64

internal fun generatePixelColors(name: String, colors: List<Color>): List<Color> {
    val numFromName = AvatarUtils.hashCode(name)
    val range = colors.size
    return List(PIXEL_ELEMENTS) { i ->
        AvatarUtils.getRandomColor(numFromName % (i + 1), colors, range)
    }
}

/**
 * Renders the Pixel variant of BoringAvatar.
 */
@Composable
fun AvatarPixel(
    name: String,
    colors: List<Color>,
    size: Dp = AvatarUtils.DEFAULT_SIZE,
    shape: Shape = AvatarUtils.DEFAULT_SHAPE,
    modifier: Modifier = Modifier
) {
    val pixelColors = remember(name, colors) { generatePixelColors(name, colors) }

    Canvas(modifier = modifier.size(size).clip(shape)) {
        val scaleFactor = this.size.width / PIXEL_SIZE
        
        scale(scaleFactor, pivot = Offset.Zero) {
            val coords = listOf(
                0 to 0, 20 to 0, 40 to 0, 60 to 0, 10 to 0, 30 to 0, 50 to 0, 70 to 0,
                0 to 10, 0 to 20, 0 to 30, 0 to 40, 0 to 50, 0 to 60, 0 to 70,
                20 to 10, 20 to 20, 20 to 30, 20 to 40, 20 to 50, 20 to 60, 20 to 70,
                40 to 10, 40 to 20, 40 to 30, 40 to 40, 40 to 50, 40 to 60, 40 to 70,
                60 to 10, 60 to 20, 60 to 30, 60 to 40, 60 to 50, 60 to 60, 60 to 70,
                10 to 10, 10 to 20, 10 to 30, 10 to 40, 10 to 50, 10 to 60, 10 to 70,
                30 to 10, 30 to 20, 30 to 30, 30 to 40, 30 to 50, 30 to 60, 30 to 70,
                50 to 10, 50 to 20, 50 to 30, 50 to 40, 50 to 50, 50 to 60, 50 to 70,
                70 to 10, 70 to 20, 70 to 30, 70 to 40, 70 to 50, 70 to 60, 70 to 70
            )

            coords.forEachIndexed { index, (x, y) ->
                drawRect(
                    color = pixelColors[index],
                    topLeft = Offset(x.toFloat(), y.toFloat()),
                    size = Size(10f, 10f)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun AvatarPixelPreview() {
    AvatarPixel(
        name = "Preview",
        colors = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90))
    )
}
