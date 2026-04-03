package io.github.feliperce.avatarkt.variants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale

/**
 * A shared component for rendering a grid of colored pixels.
 * Used by variants that don't rely on SVG paths but rather on a pixelated grid.
 */
@Composable
internal fun SharedGridAvatar(
    pixelColors: List<Color>,
    gridSize: Int,
    pixelUnit: Float,
    modifier: Modifier = Modifier
) {
    val totalViewBox = gridSize * pixelUnit
    
    Canvas(modifier = modifier.fillMaxSize()) {
        val scaleFactor = size.width / totalViewBox
        
        scale(scaleFactor, pivot = Offset.Zero) {
            pixelColors.forEachIndexed { index, color ->
                val row = index / gridSize
                val col = index % gridSize
                
                drawRect(
                    color = color,
                    topLeft = Offset(col * pixelUnit, row * pixelUnit),
                    size = Size(pixelUnit, pixelUnit)
                )
            }
        }
    }
}
