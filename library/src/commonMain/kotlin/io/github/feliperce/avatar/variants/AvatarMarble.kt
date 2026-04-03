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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import io.github.feliperce.avatar.util.AvatarUtils
import androidx.compose.ui.tooling.preview.Preview

private const val MARBLE_SIZE = 80f
private const val ELEMENTS = 3

internal data class MarbleElement(
    val color: Color,
    val translateX: Float,
    val translateY: Float,
    val scale: Float,
    val rotate: Float
)

@Composable
fun AvatarMarble(
    name: String,
    colors: List<Color>,
    size: Dp = AvatarUtils.DEFAULT_SIZE,
    shape: Shape = AvatarUtils.DEFAULT_SHAPE,
    modifier: Modifier = Modifier
) {
    val context = remember(name, colors) { AvatarUtils.createContext(name, colors) }

    val properties = remember(context) {
        val numFromName = context.numFromName
        List(ELEMENTS) { i ->
            MarbleElement(
                color = AvatarUtils.getRandomColor(numFromName + i, colors, context.range),
                translateX = AvatarUtils.getUnit(numFromName * (i + 1), (MARBLE_SIZE / 10).toInt(), 1).toFloat(),
                translateY = AvatarUtils.getUnit(numFromName * (i + 1), (MARBLE_SIZE / 10).toInt(), 2).toFloat(),
                scale = 1.2f + AvatarUtils.getUnit(numFromName * (i + 1), (MARBLE_SIZE / 20).toInt()) / 10f,
                rotate = AvatarUtils.getUnit(numFromName * (i + 1), 360, 1).toFloat()
            )
        }
    }

    val path1 = remember {
        PathParser().parsePathString("M32.414 59.35L50.376 70.5H72.5v-71H33.728L26.5 13.381l19.057 27.08L32.414 59.35z").toPath()
    }
    val path2 = remember {
        PathParser().parsePathString("M22.216 24L0 46.75l14.108 38.129L78 86l-3.081-59.276-22.378 4.005 12.972 20.186-23.35 27.395L22.215 24z").toPath()
    }

    Canvas(
        modifier = modifier
            .size(size)
            .clip(shape)
            .graphicsLayer(renderEffect = BlurEffect(7f, 7f))
    ) {
        val scaleFactor = this.size.width / MARBLE_SIZE

        scale(scaleFactor, pivot = Offset.Zero) {
            drawRect(color = properties[0].color, size = Size(MARBLE_SIZE, MARBLE_SIZE))

            val p1 = properties[1]
            translate(p1.translateX, p1.translateY) {
                rotate(p1.rotate, pivot = Offset(MARBLE_SIZE / 2, MARBLE_SIZE / 2)) {
                    val p2_scale = properties[2].scale
                    scale(p2_scale, pivot = Offset(MARBLE_SIZE / 2, MARBLE_SIZE / 2)) {
                        drawPath(path = path1, color = p1.color)
                    }
                }
            }

            val p2 = properties[2]
            translate(p2.translateX, p2.translateY) {
                rotate(p2.rotate, pivot = Offset(MARBLE_SIZE / 2, MARBLE_SIZE / 2)) {
                    scale(p2.scale, pivot = Offset(MARBLE_SIZE / 2, MARBLE_SIZE / 2)) {
                        drawPath(
                            path = path2,
                            color = p2.color,
                            blendMode = BlendMode.Overlay
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AvatarMarblePreview() {
    AvatarMarble(
        name = "Preview",
        colors = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90))
    )
}
