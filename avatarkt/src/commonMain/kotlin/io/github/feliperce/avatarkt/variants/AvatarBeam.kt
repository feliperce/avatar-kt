package io.github.feliperce.avatarkt.variants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.feliperce.avatarkt.util.AvatarUtils
import androidx.compose.ui.tooling.preview.Preview

private data class BeamData(
    val wrapperColor: Color,
    val faceColor: Color,
    val backgroundColor: Color,
    val wrapperTranslateX: Float,
    val wrapperTranslateY: Float,
    val wrapperRotate: Float,
    val wrapperScale: Float,
    val isMouthOpen: Boolean,
    val isCircle: Boolean,
    val eyeSpread: Int,
    val mouthSpread: Int,
    val faceRotate: Float,
    val faceTranslateX: Float,
    val faceTranslateY: Float
)

private const val SIZE = 36f

/**
 * Renders the Beam variant of BoringAvatar.
 * It uses geometric shapes and specific transformations to create a unique minimalist face based on the name hash.
 */
@Composable
internal fun AvatarBeam(
    name: String,
    colors: List<Color>,
    size: Dp = 40.dp,
    shape: Shape = CircleShape,
    modifier: Modifier = Modifier
) {
    val context = remember(name, colors) { AvatarUtils.createContext(name, colors) }
    
    val data = remember(context) {
        val numFromName = context.numFromName
        val wrapperColor = context.getRandomColor()
        val preTranslateX = AvatarUtils.getUnit(numFromName, 10, 1).toFloat()
        val wrapperTranslateX = if (preTranslateX < 5) preTranslateX + SIZE / 9f else preTranslateX
        val preTranslateY = AvatarUtils.getUnit(numFromName, 10, 2).toFloat()
        val wrapperTranslateY = if (preTranslateY < 5) preTranslateY + SIZE / 9f else preTranslateY

        BeamData(
            wrapperColor = wrapperColor,
            faceColor = AvatarUtils.getContrast(wrapperColor),
            backgroundColor = AvatarUtils.getRandomColor(numFromName + 13, colors, context.range),
            wrapperTranslateX = wrapperTranslateX,
            wrapperTranslateY = wrapperTranslateY,
            wrapperRotate = AvatarUtils.getUnit(numFromName, 360).toFloat(),
            wrapperScale = 1f + AvatarUtils.getUnit(numFromName, (SIZE / 12f).toInt()) / 10f,
            isMouthOpen = AvatarUtils.getBoolean(numFromName, 2),
            isCircle = AvatarUtils.getBoolean(numFromName, 1),
            eyeSpread = AvatarUtils.getUnit(numFromName, 5),
            mouthSpread = AvatarUtils.getUnit(numFromName, 3),
            faceRotate = AvatarUtils.getUnit(numFromName, 10, 3).toFloat(),
            faceTranslateX = if (wrapperTranslateX > SIZE / 6f) wrapperTranslateX / 2f else AvatarUtils.getUnit(numFromName, 8, 1).toFloat(),
            faceTranslateY = if (wrapperTranslateY > SIZE / 6f) wrapperTranslateY / 2f else AvatarUtils.getUnit(numFromName, 7, 2).toFloat()
        )
    }

    Canvas(modifier = modifier.size(size).clip(shape)) {
        val scaleFactor = size.toPx() / SIZE
        
        scale(scaleFactor, pivot = Offset.Zero) {
            drawRect(color = data.backgroundColor, size = Size(SIZE, SIZE))

            translate(data.wrapperTranslateX, data.wrapperTranslateY) {
                rotate(data.wrapperRotate, pivot = Offset(SIZE / 2f, SIZE / 2f)) {
                    scale(data.wrapperScale, pivot = Offset.Zero) {
                        val rx = if (data.isCircle) SIZE else SIZE / 6f
                        drawRoundRect(
                            color = data.wrapperColor,
                            size = Size(SIZE, SIZE),
                            cornerRadius = CornerRadius(rx, rx)
                        )
                    }
                }
            }

            translate(data.faceTranslateX, data.faceTranslateY) {
                rotate(data.faceRotate, pivot = Offset(SIZE / 2f, SIZE / 2f)) {
                    if (data.isMouthOpen) {
                        val path = Path().apply {
                            moveTo(15f, 19f + data.mouthSpread)
                            cubicTo(
                                17f, 20f + data.mouthSpread,
                                19f, 20f + data.mouthSpread,
                                21f, 19f + data.mouthSpread
                            )
                        }
                        drawPath(
                            path = path,
                            color = data.faceColor,
                            style = Stroke(width = 1f, cap = StrokeCap.Round)
                        )
                    } else {
                        val path = Path().apply {
                            moveTo(13f, 19f + data.mouthSpread)
                            arcTo(
                                rect = Rect(
                                    left = 13f,
                                    top = 15.25f + data.mouthSpread,
                                    right = 23f,
                                    bottom = 22.75f + data.mouthSpread
                                ),
                                startAngleDegrees = 0f,
                                sweepAngleDegrees = 180f,
                                forceMoveTo = false
                            )
                        }
                        drawPath(path = path, color = data.faceColor)
                    }

                    drawRoundRect(
                        color = data.faceColor,
                        topLeft = Offset(14f - data.eyeSpread, 14f),
                        size = Size(1.5f, 2f),
                        cornerRadius = CornerRadius(1f, 1f)
                    )
                    drawRoundRect(
                        color = data.faceColor,
                        topLeft = Offset(20f + data.eyeSpread, 14f),
                        size = Size(1.5f, 2f),
                        cornerRadius = CornerRadius(1f, 1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AvatarBeamPreview() {
    AvatarBeam(
        name = "Preview",
        colors = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90))
    )
}
