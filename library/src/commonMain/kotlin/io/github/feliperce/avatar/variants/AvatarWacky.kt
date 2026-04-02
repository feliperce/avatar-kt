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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.withTransform

internal data class SvgPath(val d: String, val color: Color?, val fillType: PathFillType)

private val wackyEyesPaths = listOf(
        listOf(
            SvgPath(d = "M.25 8.12C1.66 11.86 12 16 12 16s5.17-9.58 3.76-13.32c0 0-1.41-3.74-5.3-2.38-3.87 1.36-2.7 4.48-2.7 4.48S6.6 1.66 2.73 3.02C-1.16 4.38.25 8.12.25 8.12ZM26.24 2.68C24.84 6.42 30 16 30 16s10.34-4.14 11.75-7.88c0 0 1.41-3.74-2.47-5.1-3.87-1.36-5.05 1.76-5.05 1.76s1.18-3.12-2.7-4.48c-3.88-1.36-5.29 2.38-5.29 2.38Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M-.75 8.12C.66 11.86 11 16 11 16s5.17-9.58 3.76-13.32c0 0-1.41-3.74-5.3-2.38-3.87 1.36-2.7 4.48-2.7 4.48S5.6 1.66 1.73 3.02c-3.88 1.36-2.47 5.1-2.47 5.1ZM27.24 2.68C25.84 6.42 31 16 31 16s10.34-4.14 11.75-7.88c0 0 1.41-3.74-2.47-5.1-3.87-1.36-5.05 1.76-5.05 1.76s1.18-3.12-2.7-4.48c-3.88-1.36-5.29 2.38-5.29 2.38Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M-1.75 8.12C-.34 11.86 10 16 10 16s5.17-9.58 3.76-13.32c0 0-1.41-3.74-5.3-2.38-3.87 1.36-2.7 4.48-2.7 4.48S4.6 1.66.73 3.02c-3.88 1.36-2.47 5.1-2.47 5.1ZM28.24 2.68C26.84 6.42 32 16 32 16s10.34-4.14 11.75-7.88c0 0 1.41-3.74-2.47-5.1-3.87-1.36-5.05 1.76-5.05 1.76s1.18-3.12-2.7-4.48c-3.88-1.36-5.29 2.38-5.29 2.38Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M-2.75 8.12C-1.34 11.86 9 16 9 16s5.17-9.58 3.76-13.32c0 0-1.41-3.74-5.3-2.38-3.87 1.36-2.7 4.48-2.7 4.48S3.6 1.66-.27 3.02c-3.88 1.36-2.47 5.1-2.47 5.1ZM29.24 2.68C27.84 6.42 33 16 33 16s10.34-4.14 11.75-7.88c0 0 1.41-3.74-2.47-5.1-3.87-1.36-5.05 1.76-5.05 1.76s1.18-3.12-2.7-4.48c-3.88-1.36-5.29 2.38-5.29 2.38Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M9.5 10c-3.88 0-7.11-4.23-6.4-4.85.71-.62 2.63 1.3 6.4 1.3 3.77 0 5.69-2 6.4-1.3S13.38 10 9.5 10ZM32.5 10c-3.88 0-7.11-4.23-6.4-4.85.71-.62 2.63 1.3 6.4 1.3 3.77 0 5.69-2 6.4-1.3S36.38 10 32.5 10Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M8.5 10c-3.88 0-7.11-4.23-6.4-4.85.71-.62 2.63 1.3 6.4 1.3 3.77 0 5.69-2 6.4-1.3S12.38 10 8.5 10ZM33.5 10c-3.88 0-7.11-4.23-6.4-4.85.71-.62 2.63 1.3 6.4 1.3 3.77 0 5.69-2 6.4-1.3S37.38 10 33.5 10Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7.5 10C3.62 10 .39 5.77 1.1 5.15c.71-.62 2.63 1.3 6.4 1.3 3.77 0 5.69-2 6.4-1.3S11.38 10 7.5 10ZM34.5 10c-3.88 0-7.11-4.23-6.4-4.85.71-.62 2.63 1.3 6.4 1.3 3.77 0 5.69-2 6.4-1.3S38.38 10 34.5 10Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6.5 10C2.62 10-.61 5.77.1 5.15c.71-.62 2.63 1.3 6.4 1.3 3.77 0 5.69-2 6.4-1.3S10.38 10 6.5 10ZM35.5 10c-3.88 0-7.11-4.23-6.4-4.85.71-.62 2.63 1.3 6.4 1.3 3.77 0 5.69-2 6.4-1.3S39.38 10 35.5 10Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M11.86 7.5c0-1.42-4.14-2.85-4.82-4.98C6.34.4 16 5.37 16 7.5c0 2.13-9.65 7.11-8.96 4.98.68-2.13 4.82-3.56 4.82-4.98ZM30.14 7.5c0-1.42 4.14-2.85 4.82-4.98C35.66.4 26 5.37 26 7.5c0 2.13 9.65 7.11 8.96 4.98-.68-2.13-4.82-3.56-4.82-4.98Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M10.86 7.5c0-1.42-4.14-2.85-4.82-4.98C5.34.4 15 5.37 15 7.5c0 2.13-9.65 7.11-8.96 4.98.68-2.13 4.82-3.56 4.82-4.98ZM31.14 7.5c0-1.42 4.14-2.85 4.82-4.98C36.66.4 27 5.37 27 7.5c0 2.13 9.65 7.11 8.96 4.98-.68-2.13-4.82-3.56-4.82-4.98Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M9.86 7.5c0-1.42-4.14-2.85-4.82-4.98C4.34.4 14 5.37 14 7.5c0 2.13-9.65 7.11-8.96 4.98.68-2.13 4.82-3.56 4.82-4.98ZM32.14 7.5c0-1.42 4.14-2.85 4.82-4.98C37.66.4 28 5.37 28 7.5c0 2.13 9.65 7.11 8.96 4.98-.68-2.13-4.82-3.56-4.82-4.98Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M8.86 7.5c0-1.42-4.14-2.85-4.82-4.98C3.34.4 13 5.37 13 7.5c0 2.13-9.65 7.11-8.96 4.98.68-2.13 4.82-3.56 4.82-4.98ZM33.14 7.5c0-1.42 4.14-2.85 4.82-4.98C38.66.4 29 5.37 29 7.5c0 2.13 9.65 7.11 8.96 4.98-.68-2.13-4.82-3.56-4.82-4.98Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M8 8.36S8 4 12 4s4 4.36 4 4.36v2.91s0 .73-.67.73c-.66 0-.66-2.9-3.33-2.9S9.33 12 8.67 12C8 12 8 11.27 8 11.27v-2.9ZM26 8.36S26 4 30 4s4 4.36 4 4.36v2.91s0 .73-.67.73c-.66 0-.66-2.9-3.33-2.9S27.33 12 26.67 12c-.67 0-.67-.73-.67-.73v-2.9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M7 8.36S7 4 11 4s4 4.36 4 4.36v2.91s0 .73-.67.73c-.66 0-.66-2.9-3.33-2.9S8.33 12 7.67 12C7 12 7 11.27 7 11.27v-2.9ZM27 8.36S27 4 31 4s4 4.36 4 4.36v2.91s0 .73-.67.73c-.66 0-.66-2.9-3.33-2.9S28.33 12 27.67 12c-.67 0-.67-.73-.67-.73v-2.9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M6 8.36S6 4 10 4s4 4.36 4 4.36v2.91s0 .73-.67.73c-.66 0-.66-2.9-3.33-2.9S7.33 12 6.67 12C6 12 6 11.27 6 11.27v-2.9ZM28 8.36S28 4 32 4s4 4.36 4 4.36v2.91s0 .73-.67.73c-.66 0-.66-2.9-3.33-2.9S29.33 12 28.67 12c-.67 0-.67-.73-.67-.73v-2.9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M5 8.36S5 4 9 4s4 4.36 4 4.36v2.91s0 .73-.67.73c-.66 0-.66-2.9-3.33-2.9S6.33 12 5.67 12C5 12 5 11.27 5 11.27v-2.9ZM29 8.36S29 4 33 4s4 4.36 4 4.36v2.91s0 .73-.67.73c-.66 0-.66-2.9-3.33-2.9S30.33 12 29.67 12c-.67 0-.67-.73-.67-.73v-2.9Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M16 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4ZM32 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M15 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4ZM33 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M14 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4ZM34 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M13 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4ZM35 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M16 8c0 3.31-1.34 6-3 6s-3-2.69-3-6 1.34-6 3-6 3 2.69 3 6ZM32 8c0 3.31-1.34 6-3 6s-3-2.69-3-6 1.34-6 3-6 3 2.69 3 6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M15 8c0 3.31-1.34 6-3 6s-3-2.69-3-6 1.34-6 3-6 3 2.69 3 6ZM33 8c0 3.31-1.34 6-3 6s-3-2.69-3-6 1.34-6 3-6 3 2.69 3 6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M14 8c0 3.31-1.34 6-3 6s-3-2.69-3-6 1.34-6 3-6 3 2.69 3 6ZM34 8c0 3.31-1.34 6-3 6s-3-2.69-3-6 1.34-6 3-6 3 2.69 3 6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M13 8c0 3.31-1.34 6-3 6s-3-2.69-3-6 1.34-6 3-6 3 2.69 3 6ZM35 8c0 3.31-1.34 6-3 6s-3-2.69-3-6 1.34-6 3-6 3 2.69 3 6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M11.5 6C8.29 6 7 7.36 7 8.04c0 3.4 1.29 1.35 4.5 1.35S16 11.43 16 8.04C16 7.36 14.71 6 11.5 6ZM30.5 6C27.29 6 26 7.36 26 8.04c0 3.4 1.29 1.35 4.5 1.35S35 11.43 35 8.04C35 7.36 33.71 6 30.5 6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M10.5 6C7.29 6 6 7.36 6 8.04c0 3.4 1.29 1.35 4.5 1.35S15 11.43 15 8.04C15 7.36 13.71 6 10.5 6ZM31.5 6C28.29 6 27 7.36 27 8.04c0 3.4 1.29 1.35 4.5 1.35S36 11.43 36 8.04C36 7.36 34.71 6 31.5 6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M9.5 6C6.29 6 5 7.36 5 8.04c0 3.4 1.29 1.35 4.5 1.35S14 11.43 14 8.04C14 7.36 12.71 6 9.5 6ZM32.5 6C29.29 6 28 7.36 28 8.04c0 3.4 1.29 1.35 4.5 1.35S37 11.43 37 8.04C37 7.36 35.71 6 32.5 6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M8.5 6C5.29 6 4 7.36 4 8.04c0 3.4 1.29 1.35 4.5 1.35S13 11.43 13 8.04C13 7.36 11.71 6 8.5 6ZM33.5 6C30.29 6 29 7.36 29 8.04c0 3.4 1.29 1.35 4.5 1.35S38 11.43 38 8.04C38 7.36 36.71 6 33.5 6Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M16 8c0 1.66-1.12 3-2.5 3S11 9.66 11 8s1.12-3 2.5-3S16 6.34 16 8ZM31 8c0 1.66-1.12 3-2.5 3S26 9.66 26 8s1.12-3 2.5-3S31 6.34 31 8Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M15 8c0 1.66-1.12 3-2.5 3S10 9.66 10 8s1.12-3 2.5-3S15 6.34 15 8ZM32 8c0 1.66-1.12 3-2.5 3S27 9.66 27 8s1.12-3 2.5-3S32 6.34 32 8Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M14 8c0 1.66-1.12 3-2.5 3S9 9.66 9 8s1.12-3 2.5-3S14 6.34 14 8ZM33 8c0 1.66-1.12 3-2.5 3S28 9.66 28 8s1.12-3 2.5-3S33 6.34 33 8Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M13 8c0 1.66-1.12 3-2.5 3S8 9.66 8 8s1.12-3 2.5-3S13 6.34 13 8ZM34 8c0 1.66-1.12 3-2.5 3S29 9.66 29 8s1.12-3 2.5-3S34 6.34 34 8Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M14 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4ZM28.5 5C25.29 5 24 6.36 24 7.04c0 3.4 1.29 1.35 4.5 1.35S33 10.43 33 7.04C33 6.36 31.71 5 28.5 5Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M13 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4ZM29.5 5C26.29 5 25 6.36 25 7.04c0 3.4 1.29 1.35 4.5 1.35S34 10.43 34 7.04C34 6.36 32.71 5 29.5 5Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M12 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4ZM30.5 5C27.29 5 26 6.36 26 7.04c0 3.4 1.29 1.35 4.5 1.35S35 10.43 35 7.04C35 6.36 33.71 5 30.5 5Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M11 8c0 2.2-1.34 4-3 4s-3-1.8-3-4 1.34-4 3-4 3 1.8 3 4ZM31.5 5C28.29 5 27 6.36 27 7.04c0 3.4 1.29 1.35 4.5 1.35S36 10.43 36 7.04C36 6.36 34.71 5 31.5 5Z", color = null, fillType = PathFillType.NonZero),
        ),
    )

private val wackyMouthPaths = listOf(
        listOf(
            SvgPath(d = "M15 14C1.9 14-.72 1.29.15.23 1.03-.83 6.27 2.11 15 2.11S28.97-.83 29.85.23C30.72 1.3 28.1 14 15 14Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M15 11C4.52 11 2.42 2.82 3.12 2.14 3.82 1.46 8.02 3.5 15 3.5c6.99 0 11.18-2.04 11.88-1.36.7.68-1.4 8.86-11.88 8.86Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M15.5 10c-5.07 0-9.3-5.23-8.37-5.88.93-.65 3.45 2.15 8.37 2.15 4.92 0 7.44-2.88 8.37-2.15.93.73-3.3 5.88-8.37 5.88Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M15 10C6.79 10 3.02 3.88 4.22 3.12 5.42 2.35 6.1 6.6 15 6.49c8.9-.12 9.58-4.23 10.78-3.37C26.98 3.98 23.21 10 15 10Z", color = null, fillType = PathFillType.NonZero),
        ),
        listOf(
            SvgPath(d = "M15.2 3.84c0-.67-4.2-2-4.2-2.67 0-.66 7 .67 7 2.67S13.8 6.5 13.8 6.5s4.2.67 4.2 2.66c0 2-7 3.33-7 2.67 0-.67 4.2-2 4.2-2.67 0-.66-3.5-1.33-3.5-2.66s3.5-2 3.5-2.66Z", color = null, fillType = PathFillType.NonZero),
        ),
    )

private val wackyShapePaths = listOf(
    listOf(
        SvgPath(d = "M95 53.33C95 29.4 74.85 10 50 10S5 29.4 5 53.33V140h90V53.33Z", color = null, fillType = PathFillType.NonZero),
    ),
)

@Composable
internal fun AvatarWacky(
    name: String,
    colors: List<Color>,
    size: Dp,
    shape: Shape,
    modifier: Modifier
) {
    val seed = AvatarUtils.hashCode(name)
    val random = kotlin.random.Random(seed.toLong())

    val eyeIndex = random.nextInt(wackyEyesPaths.size)
    val mouthIndex = random.nextInt(wackyMouthPaths.size)
    
    val skinColor = colors[random.nextInt(colors.size)]
    val backgroundColors = colors.filter { it != skinColor }
    val backgroundColor = if (backgroundColors.isNotEmpty()) backgroundColors[random.nextInt(backgroundColors.size)] else skinColor

    val r = skinColor.red * 255
    val g = skinColor.green * 255
    val b = skinColor.blue * 255
    val yiq = (r * 299 + g * 587 + b * 114) / 1000f
    val featuresColor = if (yiq >= 200f) Color.Black else Color.White

    val shapeOffsetX = random.nextInt(-5, 6)
    val shapeOffsetY = random.nextInt(-5, 6)
    val shapeRotation = random.nextInt(-20, 21)
    
    val faceOffsetX = random.nextInt(-15, 16)
    val faceOffsetY = random.nextInt(-15, 16)
    val faceRotation = random.nextInt(-20, 21)
    
    val faceVariant = random.nextInt(1, 6)
    val (eyesOffset, mouthOffset) = when (faceVariant) {
        1 -> 5f to 23f
        2 -> 4f to 24f
        3 -> 3f to 25f
        4 -> 2f to 26f
        else -> 1f to 27f
    }

    val eyesPaths = wackyEyesPaths[eyeIndex]
    val mouthPaths = wackyMouthPaths[mouthIndex]
    val facePaths = wackyShapePaths[0] 

    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scaleX = this.size.width / 100f
            val scaleY = this.size.height / 100f

            fun drawSvgPaths(paths: List<SvgPath>, dynamicColor: Color) {
                for (p in paths) {
                    val path = PathParser().parsePathString(p.d).toPath()
                    path.fillType = p.fillType
                    drawPath(path, color = p.color ?: dynamicColor)
                }
            }

            withTransform({
                scale(scaleX, scaleY)
            }) {
                drawRect(backgroundColor)

                withTransform({
                    translate(shapeOffsetX.toFloat(), shapeOffsetY.toFloat())
                    rotate(degrees = shapeRotation.toFloat(), pivot = Offset(50f, 70f))
                }) {
                    drawSvgPaths(facePaths, skinColor)

                    withTransform({
                        translate(29f, 33f)
                        translate(faceOffsetX.toFloat(), faceOffsetY.toFloat())
                        rotate(degrees = faceRotation.toFloat(), pivot = Offset(21f, 21f))
                    }) {
                        withTransform({
                            translate(0f, eyesOffset)
                        }) {
                            drawSvgPaths(eyesPaths, featuresColor)
                        }
                        
                        withTransform({
                            translate(6f, mouthOffset)
                        }) {
                            drawSvgPaths(mouthPaths, featuresColor)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AvatarWackyPreview() {
    AvatarWacky(
        name = "Felipe",
        colors = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90)),
        size = 120.dp,
        shape = androidx.compose.foundation.shape.CircleShape,
        modifier = Modifier
    )
}
