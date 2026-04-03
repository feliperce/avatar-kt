package io.github.feliperce.avatar.variants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser

/**
 * Represents a single SVG path element.
 */
internal data class SvgPath(
    val d: String, 
    val color: Color?, 
    val fillType: PathFillType = PathFillType.NonZero
)

/**
 * A shared component for rendering a collection of [SvgPath] elements onto a Canvas.
 * This unifies the logic used in PixelArt, PixelNeutral, and Wacky variants.
 */
@Composable
internal fun SharedSvgAvatar(
    paths: List<SvgPath>,
    baseSize: Float,
    viewBoxSize: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawSvgPaths(paths, Color.Black)
        }
        content()
    }
}

/**
 * Extension for drawing a list of [SvgPath] directly on a [DrawScope].
 */
internal fun androidx.compose.ui.graphics.drawscope.DrawScope.drawSvgPaths(
    paths: List<SvgPath>,
    defaultColor: Color
) {
    for (p in paths) {
        val path = PathParser().parsePathString(p.d).toPath()
        path.fillType = p.fillType
        drawPath(path, color = p.color ?: defaultColor)
    }
}
