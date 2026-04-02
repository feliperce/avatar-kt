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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import io.github.feliperce.avatar.util.AvatarUtils
import kotlin.math.abs

private val pixelEyesPaths = listOf(
        listOf(
            SvgPath(d = "M5 6h1v2H4V7h1V6Zm5 0h1v2H9V7h1V6Z", color = Color(0xFFFFFFFF), fillType = PathFillType.EvenOdd),
            SvgPath(d = "M6 6v1H5v1h2V6H6Zm5 0v1h-1v1h2V6h-1Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M6 6v1h1V6H6ZM5 7v1h1V7H5Zm6-1v1h1V6h-1Zm-1 1v1h1V7h-1Z", color = Color(0x80FFFFFF), fillType = PathFillType.EvenOdd),
        ),
        listOf(
            SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M4 6h2v1H4zM9 6h2v1H9z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M4 6h1v1H4zM9 6h1v1H9z", color = Color(0xB3FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M10 5h2v2h-2zM5 5h2v2H5z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M5 5h1v1H5zM6 6h1v1H6zM10 5h1v1h-1zM11 6h1v1h-1z", color = Color(0x66FFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M11 5h1v1h-1zM6 5h1v1H6z", color = Color(0xB3FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M10 6h1v1h-1zM5 6h1v1H5z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M4 5h3v2H4zM9 5h3v2H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M9 6h1v1H9zM4 6h1v1H4z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M4 5h3v2H4zM9 5h3v2H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M11 6h1v1h-1zM6 6h1v1H6z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M4 5h3v2H4z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M5 6h1v1H5z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M9 5h3v2H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M10 6h1v1h-1z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M4 5h3v2H4zM9 5h3v2H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M11 5h-1v1H9v1h3V6h-1V5Z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M9 6h1v1H9zM10 5h1v1h-1zM11 6h1v1h-1z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M6 5H5v1H4v1h3V6H6V5Z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M4 6h1v1H4zM5 5h1v1H5zM6 6h1v1H6z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 5H5v2h2V6H6V5ZM11 5h-1v2h2V6h-1V5Z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M5 6h1v1H5zM10 6h1v1h-1z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M10 5h1v2H9V6h1V5ZM5 5h1v2H4V6h1V5Z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M11 6h-1v1h1zM6 6H5v1h1z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M11 5h-1v2h2V6h-1V5ZM6 5H5v2h2V6H6V5Z", color = Color(0xFFFFFFFF), fillType = PathFillType.EvenOdd),
            SvgPath(d = "M10 5v1h1v1H9V5h1ZM5 5v1h1v1H4V5h1Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M10 5v1H9V5h1Zm1 1v1h-1V6h1ZM5 5v1H4V5h1Zm1 1v1H5V6h1Z", color = Color(0x80FFFFFF), fillType = PathFillType.EvenOdd),
        ),
        listOf(
            SvgPath(d = "M12 5H9v3h3zM7 5H4v3h3z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M12 6h-2v1h2zM7 6H5v1h2z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M12 6h-1v1h1zM7 6H6v1h1z", color = Color(0xB3FFFFFF), fillType = PathFillType.NonZero),
        ),
    )

private val pixelMouthPaths = listOf(
        listOf(
            SvgPath(d = "M7 9v1h1v1h1V9H7Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 10v1h1v-1h3V9H7v1H6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 9v1h3v1h1v-1H9V9H6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 9h2v1H7V9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 9h4v1H6V9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 9h3v1H7V9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 9h3v1H6V9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M9 9v1H7v1H6v-1h1V9h2Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 9v1h2v1h1v-1H9V9H7Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 9h2v1H7V9ZM7 10v1H6v-1h1ZM9 10v1h1v-1H9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 10v1h1v1h2v-1h1v-1H9V9H7v1H6Z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M7 10v1h2v-1H7Z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 10v1h1v1h2v-1h1v-1H9V9H7v1H6Z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M7 10v1h2v-1H7Z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 9v1h1v1h2v-1H7V9H6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M10 9v1H9v1H7v-1h2V9h1Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 11h2v-1H7v1ZM7 10V9H6v1h1ZM9 10V9h1v1H9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 11v-1h3V9h1v1H9v1H6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M10 11v-1H7V9H6v1h1v1h3Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 9v1h1v1h2v-1h1V9H6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 9v2h2v-1H8V9H7Z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M9 11v-1H8V9H7v1h1v1h1Z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M9 9v1H8v1h2V9H9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 9v1h1v1H6V9h1Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M8 10v1h1v-1H8Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 9v2h2V9H7Z", color = null, fillType = PathFillType.NonZero),
        ),
    )

private val pixelGlassesPaths = listOf(
        listOf(
            SvgPath(d = "M12 4H9v1H7V4H4v1H2v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5h-2V4Zm0 1v3H9V5h3ZM7 8H4V5h3v3Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M9 5h3v3H9zM4 5h3v3H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M2 5h1v1H2zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M2 5v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5H2Zm10 3H9V6h3v2ZM7 8H4V6h3v2Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M9 6h3v2H9zM4 6h3v2H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M7 5h2v1H7zM2 5h2v1H2zM12 5h2v1h-2z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M2 5v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5H9v1H7V5H2Zm5 1v2H4V6h3Zm2 0v2h3V6H9Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M4 6h3v2H4zM9 6h3v2H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M2 5h2v1H2zM12 5h2v1h-2z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M3 5h1v1H3zM7 5h2v1H7zM12 5h1v1h-1z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M2 5h12v1H2zM3 7h10v1H3zM3 6h1v1H3zM12 6h1v1h-1z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M4 6h8v1H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M2 5h1v1H2zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M7 6h2v1H7z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M14 5H2v1h1v2h4V7h2v1h4V6h1V5Zm-2 1H9v1h3V6ZM7 7H4V6h3v1Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M4 6h3v1H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M2 5h1v1H2zM7 5h2v1H7zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M9 6h3v1H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M2 5h5v1H4v1H3V6H2V5ZM7 7v1H4V7h3ZM9 7H7V6h2v1ZM12 7H9v1h3V7ZM12 6H9V5h5v1h-1v1h-1V6Z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M4 6h3v1H4zM9 6h3v1H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M12 5h2v1h-2zM2 5h2v1H2zM7 6h2v1H7z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M12 4H9v1H7V4H4v1H2v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5h-2V4Zm0 1v3H9V5h3ZM7 8H4V5h3v3Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M9 5h3v3H9zM4 5h3v3H4z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
            SvgPath(d = "M2 5h1v1H2zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M2 5v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5H2Zm10 3H9V6h3v2ZM7 8H4V6h3v2Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M9 6h3v2H9zM4 6h3v2H4z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
            SvgPath(d = "M7 5h2v1H7zM2 5h2v1H2zM12 5h2v1h-2z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M2 5v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5H9v1H7V5H2Zm5 1v2H4V6h3Zm2 0v2h3V6H9Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M4 6h3v2H4zM9 6h3v2H9z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
            SvgPath(d = "M2 5h2v1H2zM12 5h2v1h-2z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
            SvgPath(d = "M3 5h1v1H3zM7 5h2v1H7zM12 5h1v1h-1z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M2 5h12v1H2zM3 7h10v1H3zM3 6h1v1H3zM12 6h1v1h-1z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M4 6h8v1H4z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
            SvgPath(d = "M2 5h1v1H2zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M7 6h2v1H7z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M14 5H2v1h1v2h4V7h2v1h4V6h1V5Zm-2 1H9v1h3V6ZM7 7H4V6h3v1Z", color = null, fillType = PathFillType.EvenOdd),
            SvgPath(d = "M4 6h3v1H4z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
            SvgPath(d = "M2 5h1v1H2zM7 5h2v1H7zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
            SvgPath(d = "M9 6h3v1H9z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M2 5h5v1H4v1H3V6H2V5ZM7 7v1H4V7h3ZM9 7H7V6h2v1ZM12 7H9v1h3V7ZM12 6H9V5h5v1h-1v1h-1V6Z", color = null, fillType = PathFillType.NonZero),
            SvgPath(d = "M4 6h3v1H4zM9 6h3v1H9z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
            SvgPath(d = "M12 5h2v1h-2zM2 5h2v1H2zM7 6h2v1H7z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        ),
    )


@Composable
internal fun AvatarPixelNeutral(
    name: String,
    colors: List<Color>,
    size: Dp,
    shape: Shape,
    modifier: Modifier
) {
    val seed = AvatarUtils.hashCode(name)
    val eyeIndex = seed % pixelEyesPaths.size
    val mouthIndex = (seed / 10) % pixelMouthPaths.size
    val hasGlasses = (seed % 100) > 70
    val skinColor = colors[seed % colors.size]
    
    val eyesPaths = pixelEyesPaths[eyeIndex]
    val mouthPaths = pixelMouthPaths[mouthIndex]
    val glassesPaths = if (hasGlasses) pixelGlassesPaths[seed % pixelGlassesPaths.size] else emptyList()
    
    val glassColor = if (colors.size > 1) colors[(seed + 1) % colors.size] else Color.Black
    val eyesColor = Color.Black
    val mouthColor = Color.Black

    // viewBox="0 0 14 14"
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scaleX = this.size.width / 14f
            val scaleY = this.size.height / 14f

            // Background skin color filling 14x14
            drawRect(color = skinColor)

            fun drawSvgPaths(paths: List<SvgPath>, dynamicColor: Color) {
                for (p in paths) {
                    val path = PathParser().parsePathString(p.d).toPath()
                    path.fillType = p.fillType
                    path.transform(androidx.compose.ui.graphics.Matrix().apply { 
                        scale(scaleX, scaleY)
                    })
                    drawPath(path, color = p.color ?: dynamicColor)
                }
            }

            translate(left = -1f * scaleX, top = -2f * scaleY) {
                drawSvgPaths(eyesPaths, eyesColor)
                drawSvgPaths(glassesPaths, glassColor)
            }
            translate(left = -1f * scaleX, top = 0f * scaleY) {
                drawSvgPaths(mouthPaths, mouthColor)
            }
        }
    }
}

@Preview
@Composable
private fun AvatarPixelNeutralPreview() {
    AvatarPixelNeutral(
        name = "Felipe",
        colors = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90)),
        size = 120.dp,
        shape = androidx.compose.foundation.shape.CircleShape,
        modifier = Modifier
    )
}
