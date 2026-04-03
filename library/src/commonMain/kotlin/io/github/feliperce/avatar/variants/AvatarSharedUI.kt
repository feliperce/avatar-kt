package io.github.feliperce.avatar.variants

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
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
