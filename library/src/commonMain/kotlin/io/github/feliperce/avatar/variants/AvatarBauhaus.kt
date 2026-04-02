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
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val BAUHAUS_SIZE = 80f
private const val BAUHAUS_ELEMENTS = 4

internal data class BauhausElement(
    val color: Color,
    val translateX: Float,
    val translateY: Float,
    val rotate: Float,
    val isSquare: Boolean
)

internal fun generateBauhausData(name: String, colors: List<Color>): List<BauhausElement> {
    val numFromName = AvatarUtils.hashCode(name)
    val range = colors.size
    return List(BAUHAUS_ELEMENTS) { i ->
        BauhausElement(
            color = AvatarUtils.getRandomColor(numFromName + i, colors, range),
            translateX = AvatarUtils.getUnit(numFromName * (i + 1), (BAUHAUS_SIZE / 2 - (i + 17)).toInt(), 1).toFloat(),
            translateY = AvatarUtils.getUnit(numFromName * (i + 1), (BAUHAUS_SIZE / 2 - (i + 17)).toInt(), 2).toFloat(),
            rotate = AvatarUtils.getUnit(numFromName * (i + 1), 360).toFloat(),
            isSquare = AvatarUtils.getBoolean(numFromName, 2)
        )
    }
}

/**
 * Renders the Bauhaus variant of BoringAvatar.
 * It uses abstract geometric shapes in bold translated/rotated configurations inspired by Bauhaus art.
 *
 * @param name The generated hash base string.
 * @param colors The color palette to pick from.
 * @param size The size of the avatar.
 * @param shape The clipping shape for the canvas.
 * @param modifier Additional compose modifiers.
 */
@Composable
fun AvatarBauhaus(
    name: String,
    colors: List<Color>,
    size: Dp = 40.dp,
    shape: Shape = CircleShape,
    modifier: Modifier = Modifier
) {
    val properties = remember(name, colors) { generateBauhausData(name, colors) }

    Canvas(modifier = modifier.size(size).clip(shape)) {
        val scaleFactor = this.size.width / BAUHAUS_SIZE

        scale(scaleFactor, pivot = Offset.Zero) {
            drawRect(color = properties[0].color, size = Size(BAUHAUS_SIZE, BAUHAUS_SIZE))

            val p1 = properties[1]
            translate(p1.translateX, p1.translateY) {
                rotate(p1.rotate, pivot = Offset(BAUHAUS_SIZE / 2, BAUHAUS_SIZE / 2)) {
                    drawRect(
                        color = p1.color,
                        topLeft = Offset((BAUHAUS_SIZE - 60) / 2, (BAUHAUS_SIZE - 20) / 2),
                        size = Size(BAUHAUS_SIZE, if (p1.isSquare) BAUHAUS_SIZE else BAUHAUS_SIZE / 8)
                    )
                }
            }

            val p2 = properties[2]
            translate(p2.translateX, p2.translateY) {
                drawCircle(
                    color = p2.color,
                    radius = BAUHAUS_SIZE / 5,
                    center = Offset(BAUHAUS_SIZE / 2, BAUHAUS_SIZE / 2)
                )
            }

            val p3 = properties[3]
            translate(p3.translateX, p3.translateY) {
                rotate(p3.rotate, pivot = Offset(BAUHAUS_SIZE / 2, BAUHAUS_SIZE / 2)) {
                    drawLine(
                        color = p3.color,
                        start = Offset(0f, BAUHAUS_SIZE / 2),
                        end = Offset(BAUHAUS_SIZE, BAUHAUS_SIZE / 2),
                        strokeWidth = 2f
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AvatarBauhausPreview() {
    AvatarBauhaus(
        name = "Preview",
        colors = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90))
    )
}
