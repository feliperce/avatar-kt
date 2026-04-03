package io.github.feliperce.avatar.variants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.Shape
import io.github.feliperce.avatar.util.AvatarUtils
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.Dp
import kotlin.math.abs

private const val PIXEL_ANIMALS_VIEWBOX = 16f

private val animalBases = listOf(
    listOf(
        SvgPath(d = "M3 2h2v2h-2z M11 2h2v2h-2z M2 4h12v10h-12z", color = Color(0xFFE0B687), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M1 4h2v5h-2z M13 4h2v5h-2z M3 4h10v10h-10z", color = Color(0xFFC8956A), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M2 3h3v2h-3z M11 3h3v2h-3z M3 5h10v9h-10z", color = Color(0xFF9C6A46), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M4 1h2v4h-2z M10 1h2v4h-2z M3 5h10v9h-10z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 2h1v3h-1z M10 2h1v3h-1z", color = Color(0xFFFFA0B4), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M2 4h2v2h-2z M12 4h2v2h-2z M3 5h10v9h-10z", color = Color(0xFFFFC0C0), fillType = PathFillType.NonZero)
    )
)

private val animalEyes = listOf(
    listOf(SvgPath(d = "M5 7h2v2h-2z M9 7h2v2h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero)),
    listOf(
        SvgPath(d = "M4 6h3v3h-3z M9 6h3v3h-3z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M5 7h1v1h-1z M10 7h1v1h-1z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M4 6h3v1h-3z M9 6h3v1h-3z M5 7h1v1h-1z M10 7h1v1h-1z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    listOf(SvgPath(d = "M4 7h1v1h-1z M5 6h1v1h-1z M6 7h1v1h-1z M9 7h1v1h-1z M10 6h1v1h-1z M11 7h1v1h-1z", color = Color(0xFF000000), fillType = PathFillType.NonZero)),
    listOf(SvgPath(d = "M4 7h2v2h-2z M8 7h2v2h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero))
)

private val animalSnouts = listOf(
    listOf(SvgPath(d = "M7 10h2v1h-2z M5 11h2v1h-2z M9 11h2v1h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero)),
    listOf(
        SvgPath(d = "M5 10h6v4h-6z", color = Color(0x33000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 10h2v1h-2z M7 11h1v2h-1z M6 13h4v1h-4z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M6 9h4v4h-4z", color = Color(0x33000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 10h2v2h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M7 10h2v1h-2z", color = Color(0xFFFFA0B4), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 11h1v2h-1z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M8 11h1v2h-1z", color = Color(0xDDFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 11h2v3h-2z", color = Color(0x55000000), fillType = PathFillType.EvenOdd)
    ),
    listOf(
        SvgPath(d = "M6 10h4v3h-4z", color = Color(0xFFFFA0B4), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 11h1v1h-1z M8 11h1v1h-1z", color = Color(0x88000000), fillType = PathFillType.NonZero)
    )
)

private val animalAccessories = listOf(
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M3 6h4v4h-4z M9 6h4v4h-4z", color = Color(0x88000000), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M3 6h4v1h-4z M3 9h4v1h-4z M3 6h1v4h-1z M6 6h1v4h-1z M9 6h4v1h-4z M9 9h4v1h-4z M9 6h1v4h-1z M12 6h1v4h-1z M7 7h2v1h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M4 1h8v1h-8z M3 2h10v1h-10z M2 3h12v1h-12z M1 4h14v1h-14z", color = Color(0xFF8B4513), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 3h8v1h-8z", color = Color(0x88000000), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M2 3h12v2h-12z", color = Color(0xFFE53935), fillType = PathFillType.NonZero),
        SvgPath(d = "M12 4h3v1h-3z M13 5h2v1h-2z", color = Color(0xFFD32F2F), fillType = PathFillType.NonZero)
    ),
    listOf(
        SvgPath(d = "M4 13h3v2h-3z M9 13h3v2h-3z", color = Color(0xFFFFC107), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 13h2v2h-2z", color = Color(0xFFFF9800), fillType = PathFillType.NonZero)
    )
)

/**
 * Renders the PixelAnimals variant of BoringAvatar.
 * Generates an 8-bit style animal portrait with variations in base, snout, eyes, and accessories.
 *
 * @param seed The generated hash base string.
 * @param modifier Additional compose modifiers.
 * @param size The size of the avatar.
 * @param shape The clipping shape for the canvas.
 * @param backgroundColors The color palette to pick from.
 */
@Composable
fun AvatarPixelAnimals(
    name: String,
    colors: List<Color>,
    size: Dp = AvatarUtils.DEFAULT_SIZE,
    shape: Shape = AvatarUtils.DEFAULT_SHAPE,
    modifier: Modifier = Modifier
) {
    val context = remember(name, colors) { AvatarUtils.createContext(name, colors) }
    
    val bgColor = context.getRandomColor()

    val baseIndex = abs(AvatarUtils.hashCode(name + "base")) % animalBases.size
    val eyesIndex = abs(AvatarUtils.hashCode(name + "eyes")) % animalEyes.size
    val snoutIndex = abs(AvatarUtils.hashCode(name + "snout")) % animalSnouts.size
    val accessoryIndex = abs(AvatarUtils.hashCode(name + "acc")) % animalAccessories.size

    val viewPortSize = 16f

    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scale = this.size.width / PIXEL_ANIMALS_VIEWBOX

            drawRect(color = bgColor)

            scale(scale, pivot = androidx.compose.ui.geometry.Offset.Zero) {
                fun drawList(paths: List<SvgPath>) {
                    for (p in paths) {
                        val pathColor = p.color ?: Color.Unspecified
                        if (pathColor != Color.Unspecified) {
                            val path = PathParser().parsePathString(p.d).toPath()
                            path.fillType = p.fillType
                            drawPath(path, color = pathColor)
                        }
                    }
                }

                drawList(animalBases[baseIndex])
                drawList(animalSnouts[snoutIndex])
                drawList(animalEyes[eyesIndex])
                drawList(animalAccessories[accessoryIndex])
            }
        }
    }
}
