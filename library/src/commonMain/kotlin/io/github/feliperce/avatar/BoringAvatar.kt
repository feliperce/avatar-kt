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

/**
 * Defines the available visual variants for the BoringAvatar.
 */
enum class AvatarVariant {
    BEAM, MARBLE, SUNSET, BAUHAUS, RING, PIXEL, EMOJI, WACKY, PIXEL_NEUTRAL, PIXEL_ART, PIXEL_ANIMALS
}

/**
 * A highly customizable Composable to generate visually appealing avatars based on a name hash or direct values.
 *
 * @param name The input string used to generate the deterministic avatar output.
 * @param colors The list of colors to be used in the avatar rendering.
 * @param variant The type of avatar pattern to generate. Default is [AvatarVariant.BEAM].
 * @param size The uniform width and height of the avatar. Default is 40.dp.
 * @param shape The clipping shape of the avatar bounding box. Default is [CircleShape].
 * @param modifier Additional compose modifiers for the avatar container.
 */
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
        AvatarVariant.EMOJI -> AvatarEmoji(name, colors, size, shape, modifier)
        AvatarVariant.WACKY -> AvatarWacky(name, colors, size, shape, modifier)
        AvatarVariant.PIXEL_NEUTRAL -> AvatarPixelNeutral(name, colors, size, shape, modifier)
        AvatarVariant.PIXEL_ART -> AvatarPixelArt(name, modifier, size, shape, colors)
        AvatarVariant.PIXEL_ANIMALS -> AvatarPixelAnimals(name, modifier, size, shape, colors)
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
