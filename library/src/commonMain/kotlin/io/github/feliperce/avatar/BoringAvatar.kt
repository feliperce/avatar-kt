package io.github.feliperce.avatar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class AvatarVariant {
    BEAM, MARBLE, SUNSET, BAUHAUS, RING, PIXEL
}

@Composable
fun BoringAvatar(
    name: String,
    colors: List<Color>,
    variant: AvatarVariant = AvatarVariant.BEAM,
    size: Dp = 40.dp,
    square: Boolean = false,
    modifier: Modifier = Modifier
) {
    when (variant) {
        AvatarVariant.BEAM -> AvatarBeam(name, colors, size, square, modifier)
        AvatarVariant.MARBLE -> AvatarMarble(name, colors, size, square, modifier)
        AvatarVariant.SUNSET -> AvatarSunset(name, colors, size, square, modifier)
        AvatarVariant.BAUHAUS -> AvatarBauhaus(name, colors, size, square, modifier)
        AvatarVariant.RING -> AvatarRing(name, colors, size, square, modifier)
        AvatarVariant.PIXEL -> AvatarPixel(name, colors, size, square, modifier)
    }
}
