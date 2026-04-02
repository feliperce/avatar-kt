package io.github.feliperce.avatar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

private const val SIZE = 36f

data class BeamData(
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

fun generateBeamData(name: String, colors: List<Color>): BeamData {
    val numFromName = AvatarUtils.hashCode(name)
    val range = colors.size
    val wrapperColor = AvatarUtils.getRandomColor(numFromName, colors, range)
    val preTranslateX = AvatarUtils.getUnit(numFromName, 10, 1).toFloat()
    val wrapperTranslateX = if (preTranslateX < 5) preTranslateX + SIZE / 9f else preTranslateX
    val preTranslateY = AvatarUtils.getUnit(numFromName, 10, 2).toFloat()
    val wrapperTranslateY = if (preTranslateY < 5) preTranslateY + SIZE / 9f else preTranslateY

    return BeamData(
        wrapperColor = wrapperColor,
        faceColor = AvatarUtils.getContrast(wrapperColor),
        backgroundColor = AvatarUtils.getRandomColor(numFromName + 13, colors, range),
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

@Composable
fun AvatarBeam(
    name: String,
    colors: List<Color>,
    size: Dp = 40.dp,
    square: Boolean = false,
    modifier: Modifier = Modifier
) {
    val data = remember(name, colors) { generateBeamData(name, colors) }
    
    val shape = if (square) RoundedCornerShape(0.dp) else CircleShape

    Canvas(modifier = modifier.size(size).clip(shape)) {
        val scaleFactor = size.toPx() / SIZE
        
        scale(scaleFactor, pivot = Offset.Zero) {
            drawRect(color = data.backgroundColor, size = Size(SIZE, SIZE))

            translate(data.wrapperTranslateX, data.wrapperTranslateY) {
                rotate(data.wrapperRotate, pivot = Offset(SIZE / 2f, SIZE / 2f)) {
                    scale(data.wrapperScale, pivot = Offset(SIZE / 2f, SIZE / 2f)) {
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
                            quadraticTo(18f, 20f + data.mouthSpread, 21f, 19f + data.mouthSpread)
                        }
                        drawPath(
                            path = path,
                            color = data.faceColor,
                            style = Stroke(width = 1f)
                        )
                    } else {
                        val path = Path().apply {
                            moveTo(13f, 19f + data.mouthSpread)
                            arcTo(
                                rect = androidx.compose.ui.geometry.Rect(
                                    left = 13f,
                                    top = 18.25f + data.mouthSpread,
                                    right = 23f,
                                    bottom = 19.75f + data.mouthSpread
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
