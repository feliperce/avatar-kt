package io.github.feliperce.avatar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.feliperce.avatar.variants.*
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class AvatarVariant {
    BEAM, MARBLE, SUNSET, BAUHAUS, RING, PIXEL
}

@Composable
fun BoringAvatar(
    name: String,
    colors: List<Color>,
    variant: AvatarVariant = AvatarVariant.BEAM,
    size: Dp = 40.dp,
    shape: Shape = CircleShape,
    modifier: Modifier = Modifier
) {
    when (variant) {
        AvatarVariant.BEAM -> AvatarBeam(name, colors, size, shape, modifier)
        AvatarVariant.MARBLE -> AvatarMarble(name, colors, size, shape, modifier)
        AvatarVariant.SUNSET -> AvatarSunset(name, colors, size, shape, modifier)
        AvatarVariant.BAUHAUS -> AvatarBauhaus(name, colors, size, shape, modifier)
        AvatarVariant.RING -> AvatarRing(name, colors, size, shape, modifier)
        AvatarVariant.PIXEL -> AvatarPixel(name, colors, size, shape, modifier)
    }
}

@Preview
@Composable
fun BoringAvatarPreview() {
    BoringAvatar(
        name = "Preview",
        colors = listOf(Color(0xFF92A1C6), Color(0xFF146A7C), Color(0xFFF0AB3D), Color(0xFFC271B4), Color(0xFFC20D90)),
        variant = AvatarVariant.BEAM
    )
}
