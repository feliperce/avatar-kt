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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import io.github.feliperce.avatar.util.AvatarContext
import io.github.feliperce.avatar.util.AvatarUtils
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val SUNSET_SIZE = 80f
private const val SUNSET_ELEMENTS = 4

@Composable
fun AvatarSunset(
    name: String,
    colors: List<Color>,
    size: Dp = AvatarUtils.DEFAULT_SIZE,
    shape: Shape = AvatarUtils.DEFAULT_SHAPE,
    modifier: Modifier = Modifier
) {
    val context = remember(name, colors) { AvatarUtils.createContext(name, colors) }
    
    val sunsetColors = remember(context) {
        List(SUNSET_ELEMENTS) { i ->
            AvatarUtils.getRandomColor(context.numFromName + i, colors, context.range)
        }
    }

    Canvas(modifier = modifier.size(size).clip(shape)) {
        val scaleFactor = this.size.width / SUNSET_SIZE

        val topBrush = Brush.linearGradient(
            colors = listOf(sunsetColors[0], sunsetColors[1]),
            start = Offset(this.size.width / 2, 0f),
            end = Offset(this.size.width / 2, this.size.height / 2)
        )
        drawRect(
            brush = topBrush,
            topLeft = Offset.Zero,
            size = Size(this.size.width, this.size.height / 2)
        )

        val bottomBrush = Brush.linearGradient(
            colors = listOf(sunsetColors[2], sunsetColors[3]),
            start = Offset(this.size.width / 2, this.size.height / 2),
            end = Offset(this.size.width / 2, this.size.height)
        )
        drawRect(
            brush = bottomBrush,
            topLeft = Offset(0f, this.size.height / 2),
            size = Size(this.size.width, this.size.height / 2)
        )
    }
}

@Preview
@Composable
private fun AvatarSunsetPreview() {
    AvatarSunset(
        name = "Preview",
        colors = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90))
    )
}
