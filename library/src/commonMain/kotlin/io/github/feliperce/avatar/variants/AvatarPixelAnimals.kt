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
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.feliperce.avatar.util.AvatarUtils
import kotlin.math.abs

// ==========================================
// ANIMAL BASES
// ==========================================
private val animalBases = listOf(
    // 0: Cat
    listOf(
        SvgPath(d = "M3 2h2v2h-2z M11 2h2v2h-2z M2 4h12v10h-12z", color = Color(0xFFE0B687), fillType = PathFillType.NonZero)
    ),
    // 1: Dog (Floppy ears)
    listOf(
        SvgPath(d = "M1 4h2v5h-2z M13 4h2v5h-2z M3 4h10v10h-10z", color = Color(0xFFC8956A), fillType = PathFillType.NonZero)
    ),
    // 2: Bear
    listOf(
        SvgPath(d = "M2 3h3v2h-3z M11 3h3v2h-3z M3 5h10v9h-10z", color = Color(0xFF9C6A46), fillType = PathFillType.NonZero)
    ),
    // 3: Rabbit
    listOf(
        SvgPath(d = "M4 1h2v4h-2z M10 1h2v4h-2z M3 5h10v9h-10z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 2h1v3h-1z M10 2h1v3h-1z", color = Color(0xFFFFA0B4), fillType = PathFillType.NonZero) // Pink ear inside
    ),
    // 4: Pig
    listOf(
        SvgPath(d = "M2 4h2v2h-2z M12 4h2v2h-2z M3 5h10v9h-10z", color = Color(0xFFFFC0C0), fillType = PathFillType.NonZero)
    )
)

// ==========================================
// EYES
// ==========================================
private val animalEyes = listOf(
    // 0: Normal Dots
    listOf(SvgPath(d = "M5 7h2v2h-2z M9 7h2v2h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero)),
    // 1: Wide Eyes
    listOf(
        SvgPath(d = "M4 6h3v3h-3z M9 6h3v3h-3z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M5 7h1v1h-1z M10 7h1v1h-1z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    // 2: Angry / Serious
    listOf(
        SvgPath(d = "M4 6h3v1h-3z M9 6h3v1h-3z M5 7h1v1h-1z M10 7h1v1h-1z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    // 3: Happy (Closed U)
    listOf(SvgPath(d = "M4 7h1v1h-1z M5 6h1v1h-1z M6 7h1v1h-1z M9 7h1v1h-1z M10 6h1v1h-1z M11 7h1v1h-1z", color = Color(0xFF000000), fillType = PathFillType.NonZero)),
    // 4: Looking Left
    listOf(SvgPath(d = "M4 7h2v2h-2z M8 7h2v2h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero))
)

// ==========================================
// SNOUTS / MOUTHS
// ==========================================
private val animalSnouts = listOf(
    // 0: Cat Mouth (:w)
    listOf(SvgPath(d = "M7 10h2v1h-2z M5 11h2v1h-2z M9 11h2v1h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero)),
    // 1: Dog Snout
    listOf(
        SvgPath(d = "M5 10h6v4h-6z", color = Color(0x33000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 10h2v1h-2z M7 11h1v2h-1z M6 13h4v1h-4z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    // 2: Bear Round Snout
    listOf(
        SvgPath(d = "M6 9h4v4h-4z", color = Color(0x33000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 10h2v2h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    // 3: Rabbit Buckteeth
    listOf(
        SvgPath(d = "M7 10h2v1h-2z", color = Color(0xFFFFA0B4), fillType = PathFillType.NonZero), // Pink nose
        SvgPath(d = "M7 11h1v2h-1z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero), // Tooth 1
        SvgPath(d = "M8 11h1v2h-1z", color = Color(0xDDFFFFFF), fillType = PathFillType.NonZero), // Tooth 2
        SvgPath(d = "M7 11h2v3h-2z", color = Color(0x55000000), fillType = PathFillType.EvenOdd) // Tooth shadow/divider
    ),
    // 4: Pig Snout
    listOf(
        SvgPath(d = "M6 10h4v3h-4z", color = Color(0xFFFFA0B4), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 11h1v1h-1z M8 11h1v1h-1z", color = Color(0x88000000), fillType = PathFillType.NonZero)
    )
)

// ==========================================
// ACCESSORIES
// ==========================================
private val animalAccessories = listOf(
    // 0: None
    emptyList<SvgPath>(),
    // 1: Glasses
    listOf(
        SvgPath(d = "M3 6h4v4h-4z M9 6h4v4h-4z", color = Color(0x88000000), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M3 6h4v1h-4z M3 9h4v1h-4z M3 6h1v4h-1z M6 6h1v4h-1z M9 6h4v1h-4z M9 9h4v1h-4z M9 6h1v4h-1z M12 6h1v4h-1z M7 7h2v1h-2z", color = Color(0xFF000000), fillType = PathFillType.NonZero)
    ),
    // 2: Cowboy Hat
    listOf(
        SvgPath(d = "M4 1h8v1h-8z M3 2h10v1h-10z M2 3h12v1h-12z M1 4h14v1h-14z", color = Color(0xFF8B4513), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 3h8v1h-8z", color = Color(0x88000000), fillType = PathFillType.NonZero)
    ),
    // 3: Headband
    listOf(
        SvgPath(d = "M2 3h12v2h-12z", color = Color(0xFFE53935), fillType = PathFillType.NonZero),
        SvgPath(d = "M12 4h3v1h-3z M13 5h2v1h-2z", color = Color(0xFFD32F2F), fillType = PathFillType.NonZero)
    ),
    // 4: Fancy Bowtie
    listOf(
        SvgPath(d = "M4 13h3v2h-3z M9 13h3v2h-3z", color = Color(0xFFFFC107), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 13h2v2h-2z", color = Color(0xFFFF9800), fillType = PathFillType.NonZero)
    )
)

@Composable
fun AvatarPixelAnimals(
    seed: String,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    shape: Shape,
    backgroundColors: List<Color> = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90))
) {
    val bgColor = remember(seed) { AvatarUtils.getRandomColor(AvatarUtils.hashCode(seed), backgroundColors, backgroundColors.size) }

    val baseIndex = remember(seed) { abs(AvatarUtils.hashCode(seed + "base")) % animalBases.size }
    val eyesIndex = remember(seed) { abs(AvatarUtils.hashCode(seed + "eyes")) % animalEyes.size }
    val snoutIndex = remember(seed) { abs(AvatarUtils.hashCode(seed + "snout")) % animalSnouts.size }
    val accessoryIndex = remember(seed) { abs(AvatarUtils.hashCode(seed + "acc")) % animalAccessories.size }

    // Render configuration
    val viewPortSize = 16f

    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scaleX = this.size.width / viewPortSize
            val scaleY = this.size.height / viewPortSize

            drawRect(color = bgColor, size = Size(this.size.width, this.size.height))

            val drawList = { paths: List<SvgPath> ->
                for (p in paths) {
                    val pathColor = p.color ?: Color.Unspecified
                    if (pathColor != Color.Unspecified) {
                        val path = PathParser().parsePathString(p.d).toPath()
                        path.fillType = p.fillType
                        path.transform(androidx.compose.ui.graphics.Matrix().apply { 
                            scale(scaleX, scaleY)
                        })
                        drawPath(path, color = pathColor)
                    }
                }
            }

            // Draw Order
            drawList(animalBases[baseIndex])
            drawList(animalSnouts[snoutIndex])
            drawList(animalEyes[eyesIndex])
            drawList(animalAccessories[accessoryIndex])
        }
    }
}
