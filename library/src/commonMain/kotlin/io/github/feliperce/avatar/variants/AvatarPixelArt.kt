package io.github.feliperce.avatar.variants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.abs
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.PathParser
import io.github.feliperce.avatar.util.AvatarContext
import io.github.feliperce.avatar.util.AvatarUtils
import androidx.compose.ui.graphics.drawscope.scale

private const val PIXEL_ART_VIEWBOX = 16f

private val pixelArtAccessoriesPaths = listOf(
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M13 7h1v2h-1zM2 7h1v2H2z", color = Color(0xFFFAFAD2), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M13 7h1v2h-1zM2 7h1v2H2z", color = Color(0xFFFAFAD2), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 7h1v1H2zM13 7h1v1h-1z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M13 7h2v2h-2zM1 7h2v2H1z", color = Color(0xFFFAFAD2), fillType = PathFillType.NonZero),
    ),
)

private val pixelArtBeardPaths = listOf(
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
)

private val pixelArtClothingPaths = listOf(
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M6 13H4v3h2v-3ZM12 13h-2v3h2v-3Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M5 13h1v3H5zM10 13h1v3h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 13H4v3h3v-1H6v-1H5v-1ZM12 13h-1v1h-1v1H9v1h3v-3Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 13h1v1H4zM5 14h1v1H5zM6 15h1v1H6zM9 15h1v1H9zM10 14h1v1h-1z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M11 14h1v1h-1z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M11 13h1v1h-1z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M10 15h1v1h-1zM5 15h1v1H5zM4 14h1v1H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 14h8v2H4z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 14h1v2H4zM11 14h1v2h-1z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 13h1v1h6v-1h1v3H4v-3Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M9 15h2v1H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 13h2v1h1v1h2v-1h1v-1h2v3H4v-3Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M10 14h1v1h-1zM11 13h1v1h-1zM9 15h1v1H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M9 14h1v1H9zM10 13h1v1h-1zM8 15h1v1H8z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M6 14H5v1h1zM5 13H4v1h1zM7 15H6v1h1z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 14H6v1h1zM6 13H5v1h1zM8 15H7v1h1z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 13h2v1h1v1h2v-1h1v-1h2v1h1v2H3v-2h1v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 13H3v1H2v2h5v-1H6v-1H5v-1ZM13 13h-2v1h-1v1H9v1h5v-2h-1v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M11 13h1v1h-1zM10 14h1v1h-1zM9 15h1v1H9zM6 15h1v1H6zM5 14h1v1H5zM4 13h1v1H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 14H4v1H3v1h4v-1H6v-1ZM12 15h1v1H9v-1h1v-1h2v1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 13H3v1H2v2h12v-2h-1v-1h-2v1h-1v1H6v-1H5v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 13H3v1H2v2h12v-2h-1v-1h-2v1H5v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 14h1v2H2zM6 14h1v2H6zM8 14h1v2H8zM10 14h1v2h-1zM4 13h1v3H4zM12 13h1v3h-1z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 12h2v1h4v1h1v2H2v-2h1v-1h4v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 12h2v1H7z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M10 13h3v1h1v2H2v-2h1v-1h3v1h1v1h2v-1h1v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 13h1v1h6v-1h1v1h1v2H3v-2h1v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M9 15h2v1H9z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 13h1v2h6v-2h1v2h1v1H3v-1h1v-2Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 13h1v2H4zM11 13h1v2h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M10 13h3v1h1v2H2v-2h1v-1h3v2h4v-2Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 13H4v3h3v-1H6v-1H5v-1ZM12 13h-1v1h-1v1H9v1h3v-3Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M3 13h1v1H3zM2 14h1v1H2zM3 15h1v1H3zM4 14h1v1H4zM5 13h1v1H5zM5 15h1v1H5zM6 14h1v1H6zM7 15h1v1H7zM8 14h1v1H8zM9 15h1v1H9zM10 14h1v1h-1zM11 15h1v1h-1zM11 13h1v1h-1zM12 14h1v1h-1zM13 15h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 13H3v1H2v2h12v-2h-1v-1h-2v1h-1v1H6v-1H5v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 13H3v1H2v2h12v-2h-1v-1h-2v1H5v-1Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M10 13h3v1h1v2H2v-2h1v-1h3v2h4v-2Z", color = Color(0xFFFFD969), fillType = PathFillType.NonZero),
        SvgPath(d = "M5 14h1v1H5zM4 15h1v1H4zM7 15h1v1H7zM3 13h1v1H3zM2 14h1v1H2zM12 13h1v1h-1zM11 14h1v1h-1zM10 15h1v1h-1zM13 15h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M11 14h1v2h-1zM4 14h1v2H4z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
)

private val pixelArtEyesPaths = listOf(
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 6h2v1H4zM9 6h2v1H9z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 6h1v1H4zM9 6h1v1H9z", color = Color(0xB3FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M10 5h2v2h-2zM5 5h2v2H5z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
        SvgPath(d = "M5 5h1v1H5zM6 6h1v1H6zM10 5h1v1h-1zM11 6h1v1h-1z", color = Color(0x66FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M11 5h1v1h-1zM6 5h1v1H6z", color = Color(0xB3FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M10 6h1v1h-1zM5 6h1v1H5z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M9 6h1v1H9zM4 6h1v1H4z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M4 5h3v2H4z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M5 6h1v1H5z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
        SvgPath(d = "M9 5h3v2H9z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M10 6h1v1h-1z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M11 5h-1v1H9v1h3V6h-1V5Z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
        SvgPath(d = "M9 6h1v1H9zM10 5h1v1h-1zM11 6h1v1h-1z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M6 5H5v1H4v1h3V6H6V5Z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 6h1v1H4zM5 5h1v1H5zM6 6h1v1H6z", color = Color(0x80FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 5H5v2h2V6H6V5ZM11 5h-1v2h2V6h-1V5Z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M5 6h1v1H5zM10 6h1v1h-1z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M10 5h1v2H9V6h1V5ZM5 5h1v2H4V6h1V5Z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M11 6h-1v1h1zM6 6H5v1h1z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M12 5H9v3h3zM7 5H4v3h3z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M12 6h-2v1h2zM7 6H5v1h2z", color = Color(0xFF588387), fillType = PathFillType.NonZero),
        SvgPath(d = "M12 6h-1v1h1zM7 6H6v1h1z", color = Color(0xB3FFFFFF), fillType = PathFillType.NonZero),
    ),
)

private val pixelArtGlassesPaths = listOf(
    listOf(
        SvgPath(d = "M12 4H9v1H7V4H4v1H2v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5h-2V4Zm0 1v3H9V5h3ZM7 8H4V5h3v3Z", color = Color(0xFF4B4B4B), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M9 5h3v3H9zM4 5h3v3H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 5h1v1H2zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 5v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5H2Zm10 3H9V6h3v2ZM7 8H4V6h3v2Z", color = Color(0xFF4B4B4B), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M9 6h3v2H9zM4 6h3v2H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 5h2v1H7zM2 5h2v1H2zM12 5h2v1h-2z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 5v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5H9v1H7V5H2Zm5 1v2H4V6h3Zm2 0v2h3V6H9Z", color = Color(0xFF4B4B4B), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M4 6h3v2H4zM9 6h3v2H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 5h2v1H2zM12 5h2v1h-2z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M3 5h1v1H3zM7 5h2v1H7zM12 5h1v1h-1z", color = Color(0xFF4B4B4B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 5h12v1H2zM3 7h10v1H3zM3 6h1v1H3zM12 6h1v1h-1z", color = Color(0xFF4B4B4B), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 6h8v1H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 5h1v1H2zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 6h2v1H7z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M14 5H2v1h1v2h4V7h2v1h4V6h1V5Zm-2 1H9v1h3V6ZM7 7H4V6h3v1Z", color = Color(0xFF4B4B4B), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M4 6h3v1H4z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 5h1v1H2zM7 5h2v1H7zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M9 6h3v1H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 5h5v1H4v1H3V6H2V5ZM7 7v1H4V7h3ZM9 7H7V6h2v1ZM12 7H9v1h3V7ZM12 6H9V5h5v1h-1v1h-1V6Z", color = Color(0xFF4B4B4B), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 6h3v1H4zM9 6h3v1H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M12 5h2v1h-2zM2 5h2v1H2zM7 6h2v1H7z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M12 4H9v1H7V4H4v1H2v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5h-2V4Zm0 1v3H9V5h3ZM7 8H4V5h3v3Z", color = Color(0xFF4B4B4B), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M9 5h3v3H9zM4 5h3v3H4z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 5h1v1H2zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 5v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5H2Zm10 3H9V6h3v2ZM7 8H4V6h3v2Z", color = Color(0xFF4B4B4B), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M9 6h3v2H9zM4 6h3v2H4z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 5h2v1H7zM2 5h2v1H2zM12 5h2v1h-2z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 5v1h1v2h1v1h3V8h2v1h3V8h1V6h1V5H9v1H7V5H2Zm5 1v2H4V6h3Zm2 0v2h3V6H9Z", color = Color(0xFF4B4B4B), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M4 6h3v2H4zM9 6h3v2H9z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 5h2v1H2zM12 5h2v1h-2z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 5h3v3H4zM9 5h3v3H9z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M3 5h1v1H3zM7 5h2v1H7zM12 5h1v1h-1z", color = Color(0xFF4B4B4B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 5h12v1H2zM3 7h10v1H3zM3 6h1v1H3zM12 6h1v1h-1z", color = Color(0xFF4B4B4B), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 6h8v1H4z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 5h1v1H2zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 6h2v1H7z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M14 5H2v1h1v2h4V7h2v1h4V6h1V5Zm-2 1H9v1h3V6ZM7 7H4V6h3v1Z", color = Color(0xFF4B4B4B), fillType = PathFillType.EvenOdd),
        SvgPath(d = "M4 6h3v1H4z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M2 5h1v1H2zM7 5h2v1H7zM13 5h1v1h-1z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
        SvgPath(d = "M9 6h3v1H9z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 5h5v1H4v1H3V6H2V5ZM7 7v1H4V7h3ZM9 7H7V6h2v1ZM12 7H9v1h3V7ZM12 6H9V5h5v1h-1v1h-1V6Z", color = Color(0xFF4B4B4B), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 6h3v1H4zM9 6h3v1H9z", color = Color(0x4D000000), fillType = PathFillType.NonZero),
        SvgPath(d = "M12 5h2v1h-2zM2 5h2v1H2zM7 6h2v1H7z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
)

private val pixelArtHairPaths = listOf(
    listOf(
        SvgPath(d = "M11 2H5v1h1v1h4V3h1V2ZM3 3h1v2H3V3ZM13 3h-1v2h1V3Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 2h4v1H6z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 2h8v1h1v2h-2V4H5v1H3V3h1V2Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 1h2v2H7z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 2h4v1H6zM7 3h2v1H7z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M3 3h10v1H3zM4 2h8v1H4zM2 4h2v2H2zM12 4h2v2h-2z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M3 4h1v3H3zM12 4h1v3h-1zM3 2h10v2H3z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M4 2h1v3H4zM6 2h1v2H6zM8 2h1v2H8zM10 2h1v2h-1z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M3 2h10v1H3zM3 3h2v1H3zM7 1h5v1H7zM13 3h-1v1h1z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 1h5v1H7zM4 2h7v1H4zM3 3h5v1H3z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 1h5v1H5zM4 2h8v1H4zM9 3h1v1H9zM11 3h2v1h-2zM3 3h2v1H3z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M3 3h1v1H3zM5 2H4v1h1zM12 2h-1v1h1zM13 3h-1v1h1z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M9 1H8v3h1zM8 2H7v2h1zM10 0H9v3h1zM11 1h-1v1h1z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 3H3v2h1zM13 3h-1v2h1zM12 2H4v1h8z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M12 2H4v1h8zM13 3h-1v1h1zM6 3H3v1h3zM5 4H3v1h2z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M12 2H4v1h8zM13 3h-2v1h2zM13 4h-1v1h1zM6 3H3v1h3zM5 4H3v1h2zM4 5H3v1h1z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M12 2H4v1h8zM13 3H7v1h6zM13 4h-2v1h2zM13 5h-1v1h1zM6 4H3v1h3zM7 3H3v1h4zM5 5H3v1h2zM4 6H3v1h1z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M13 1H3v1H2v4h1V3h10v3h1V2h-1V1ZM2 8h1v3h1v1H2V8ZM12 11h1V8h1v4h-2v-1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 1h8v1h1v1h1v3h-1V4h-1V3H6v1H3v2H2V3h1V2h1V1ZM2 8h1v3h1v1h3v1H2V8ZM12 12H9v1h5V8h-1v3h-1v1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M13 2H3v1H2v3h1V4h10v2h1V3h-1V2ZM3 8H2v1H1v2H0v1h1v-1h1v-1h1V8Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M13 2H3v1H2v3h1V4h10v2h1V3h-1V2ZM2 8h1v2H2v1H1V9h1V8ZM1 11v1H0v-1h1ZM16 11h-1V9h-1V8h-1v2h1v1h1v1h1v-1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 1h7v1h1v1h1v3h-1V4h-2V3H5v1H4v1H3v1H2v2h1v3h1v1H1V4h1V3h1V2h2V1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M3 1h8v1h2v1h1v3h-1V4h-2V3H6v1H5V3H4v1H3v2H2v2h1v2H1V3h1V2h1V1ZM14 8h-1v2h1V8Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M5 1H3v1H2v2h1v1h1V4h8v1h1V4h1V2h-1V1h-2v1H5V1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M13 1H3v1H2v1H1v11h2V4h10v10h2V3h-1V2h-1V1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M5 4V3h7v1h1v2h1v2h-1v3h-1v1H9v1h4v1h1v3h1V3h-1V2h-1V1H3v1H2v1H1v14h1v-3h1v-1h4v-1H4v-1H3V8H2V6h1V5h1V4h1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 1v1H3v2H2v2h1V5h4V4h3V3h2v1h1v2h1V2h-2V1H6ZM2 8h1v2H2V8ZM14 8h-1v2h1V8Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 1v1H3v1H2v1H1v6h2V8H2V6h1V4h1V3h2v1h1V3h1V2H7V1H4Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 1h12v1h1v11h-1v-1h-1V8h1V6h-1V3H3v3H2v2h1v4H2v1H1V2h1V1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M2 1h12v1h1v11h-1v-1h-1V8h1V6h-1V3h-1v1h-1V3H8v1H7v1H6v1H5v1H4v1H3v4H2v1H1V2h1V1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M3 1h10v1h1v1h1v10h-1v-1h-1V8h1V6h-1V4h-1V3H5v1H4v1H3v1H2v2h1v4H2v1H1V3h1V2h1V1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M3 2h10v1h1v3h-1V4H3v2H2V3h1V2ZM13 8h1v1h1v2h-1v-1h-1V8ZM15 11h1v1h-1v-1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 0H1v3h2v1H2v2h1V5h2V4h1V3h5v1h1v1h1v1h1V3h-1V2h-1V1H6v1H4V0ZM2 8h1v1H2V8ZM14 8h-1v1h1V8Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M12 1H5v1H4v1H3v1H2v2h2V5h1V4h1V3h5v1h1v1h1v1h1V3h-1V2h-1V1ZM13 8h1v4h-2v-1h1V8ZM2 8h1v3h1v1H2V8Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 2h8v1h1v2h1v6h1v2h-2v-1h-1V5h-1V4H5v1H4v7H3v1H1v-2h1V5h1V3h1V2Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 2h8v1h1v2h1v7h-1v-1h-1V5h-1V4H5v1H4v6H3v1H2V5h1V3h1V2ZM2 12v1H1v-1h1ZM14 12h1v1h-1v-1Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M12 1H5v1H4v1H3v1H2v2h2V5h1V4h1V3h5v1h1v1h1v1h1V3h-1V2h-1V1ZM13 8h1v6h-1v-1H9v-1h3v-1h1V8ZM2 8h1v3h1v1h3v1H3v1H2V8Z", color = Color(0xFF83623B), fillType = PathFillType.NonZero),
    ),
)

private val pixelArtHatPaths = listOf(
    listOf(
        SvgPath(d = "M12 0H4v2H2v3h12V2h-2V0Z", color = Color(0xFF614F8A), fillType = PathFillType.NonZero),
        SvgPath(d = "M10 2h1v3h-1zM8 3h1v2H8z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M11 2h1v3h-1zM9 2h1v3H9z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 0h8v1h1v1h1v2H2V2h1V1h1V0Z", color = Color(0xFF614F8A), fillType = PathFillType.NonZero),
        SvgPath(d = "M12 2v1H9V2z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M12 1H4v1H3v2h12V3h-2V2h-1V1Z", color = Color(0xFF614F8A), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M13 0H3v3H1v1h14V3h-2V0Z", color = Color(0xFF614F8A), fillType = PathFillType.NonZero),
        SvgPath(d = "M11 1h1v3h-1z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 0h8v1h1v1h1v2H2V2h1V1h1V0Z", color = Color(0xFF614F8A), fillType = PathFillType.NonZero),
        SvgPath(d = "M12 1h1v1h-1zM11 2h1v1h-1zM10 3h1v1h-1zM10 0h1v1h-1zM9 1h1v1H9zM8 2h1v1H8zM7 3h1v1H7z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M13 1v1H3V1zM14 3v1H2V3z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M4 0h8v1h1v1h1v2H2V2h1V1h1V0Z", color = Color(0xFF614F8A), fillType = PathFillType.NonZero),
        SvgPath(d = "M13 1v1H3V1zM14 3v1H2V3z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M12 1H4v1H3v2h12V3h-2V2h-1V1Z", color = Color(0xFF614F8A), fillType = PathFillType.NonZero),
        SvgPath(d = "M11 4h-1V1h1z", color = Color(0x4DFFFFFF), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M4 1h8v1h-8z M3 2h10v1h-10z M2 3h12v1h-12z M1 4h14v1h-14z", color = Color(0xFF8B4513), fillType = PathFillType.NonZero),
        SvgPath(d = "M4 3h8v1h-8z", color = Color(0x80000000), fillType = PathFillType.NonZero),
    ),
)

private val pixelArtMouthPaths = listOf(
    listOf(
        SvgPath(d = "M7 9v1h1v1h1V9H7Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    emptyList<SvgPath>(),
    listOf(
        SvgPath(d = "M6 9v1h3v1h1v-1H9V9H6Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 9h2v1H7V9Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 9h4v1H6V9Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 9h3v1H7V9Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 9h3v1H6V9Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M9 9v1H7v1H6v-1h1V9h2Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 9v1h2v1h1v-1H9V9H7Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 9h2v1H7V9ZM7 10v1H6v-1h1ZM9 10v1h1v-1H9Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 10v1h1v1h2v-1h1v-1H9V9H7v1H6Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 10v1h2v-1H7Z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 10v1h1v1h2v-1h1v-1H9V9H7v1H6Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
        SvgPath(d = "M7 10v1h2v-1H7Z", color = Color(0xFFFFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 9v1h1v1h2v-1H7V9H6Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M10 9v1H9v1H7v-1h2V9h1Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 11h2v-1H7v1ZM7 10V9H6v1h1ZM9 10V9h1v1H9Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 11v-1h3V9h1v1H9v1H6Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M10 11v-1H7V9H6v1h1v1h3Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M6 9v1h1v1h2v-1h1V9H6Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 9v2h2v-1H8V9H7Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
        SvgPath(d = "M9 11v-1H8V9H7v1h1v1h1Z", color = Color(0x33FFFFFF), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M9 9v1H8v1h2V9H9Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 9v1h1v1H6V9h1Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M8 10v1h1v-1H8Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
    listOf(
        SvgPath(d = "M7 9v2h2V9H7Z", color = Color(0xFFC98276), fillType = PathFillType.NonZero),
    ),
)


private val pixelArtFaceBgPaths = listOf(
    SvgPath(d = "M4 2h8v1h1v3h1v2h-1v3h-1v1H9v1h4v1h1v2H2v-2h1v-1h4v-1H4v-1H3V8H2V6h1V3h1V2Z", color = Color(0xFFE0B687), fillType = PathFillType.NonZero),
    SvgPath(d = "M4 2h8v1h1v3h1v2h-1v3h-1v1H4v-1H3V8H2V6h1V3h1V2Z", color = Color(0x19FFFFFF), fillType = PathFillType.NonZero)
)

/**
 * Renders the PixelArt variant of BoringAvatar.
 * Generates an 8-bit style portrait composed of multiple layers like face, mouth, eyes, clothing, hair, etc.
 *
 * @param seed The generated hash base string.
 * @param modifier Additional compose modifiers.
 * @param size The size of the avatar.
 * @param shape The clipping shape for the canvas.
 * @param backgroundColors The color palette to pick from.
 */
@Composable
fun AvatarPixelArt(
    name: String,
    colors: List<Color>,
    size: Dp = AvatarUtils.DEFAULT_SIZE,
    shape: Shape = AvatarUtils.DEFAULT_SHAPE,
    modifier: Modifier = Modifier
) {
    val context = remember(name, colors) { AvatarUtils.createContext(name, colors) }
    val seed = context.numFromName
    
    val bgColor = context.getRandomColor()
    val wrapperColors = listOf(Color(0xFFE0B687), Color(0xFFFCCC9D), Color(0xFFC8956A), Color(0xFF9C6A46))
    val skinHash = AvatarUtils.hashCode(name + "skin")
    val wrapperColor = AvatarUtils.getRandomColor(skinHash, wrapperColors, wrapperColors.size)

    val accIndex = abs(AvatarUtils.hashCode(name + "acc")) % pixelArtAccessoriesPaths.size
    val beardIndex = abs(AvatarUtils.hashCode(name + "beard")) % pixelArtBeardPaths.size
    val clothIndex = abs(AvatarUtils.hashCode(name + "cloth")) % pixelArtClothingPaths.size
    val eyeIndex = abs(AvatarUtils.hashCode(name + "eye")) % pixelArtEyesPaths.size
    val glassIndex = abs(AvatarUtils.hashCode(name + "glass")) % pixelArtGlassesPaths.size
    val hairIndex = abs(AvatarUtils.hashCode(name + "hair")) % pixelArtHairPaths.size
    val hatIndex = abs(AvatarUtils.hashCode(name + "hat")) % pixelArtHatPaths.size
    val mouthIndex = abs(AvatarUtils.hashCode(name + "mouth")) % pixelArtMouthPaths.size

    val viewPortSize = 16f

    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scale = this.size.width / PIXEL_ART_VIEWBOX

            drawRect(color = bgColor)

            scale(scale, pivot = androidx.compose.ui.geometry.Offset.Zero) {
                fun drawList(paths: List<SvgPath>, useSkin: Boolean) {
                    for (p in paths) {
                        val pathColor = if (useSkin && p.color == Color(0xFFE0B687)) wrapperColor else (p.color ?: Color.Unspecified)
                        if (pathColor != Color.Unspecified) {
                            val path = PathParser().parsePathString(p.d).toPath()
                            path.fillType = p.fillType
                            drawPath(path, color = pathColor)
                        }
                    }
                }

                drawList(pixelArtFaceBgPaths, true)
                drawList(pixelArtMouthPaths[mouthIndex], false)
                drawList(pixelArtEyesPaths[eyeIndex], false)
                drawList(pixelArtClothingPaths[clothIndex], false)
                drawList(pixelArtBeardPaths[beardIndex], false)
                drawList(pixelArtHairPaths[hairIndex], false)
                drawList(pixelArtGlassesPaths[glassIndex], false)
                drawList(pixelArtHatPaths[hatIndex], false)
                drawList(pixelArtAccessoriesPaths[accIndex], false)
            }
        }
    }
}
