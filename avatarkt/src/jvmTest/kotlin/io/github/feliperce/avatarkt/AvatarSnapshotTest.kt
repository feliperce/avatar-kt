package io.github.feliperce.avatarkt

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import org.junit.Test

/**
 * Snapshot tests for all [AvatarVariant] types.
 *
 * On the first run, baseline PNG images are generated in `library/src/jvmTest/snapshots/`.
 * On subsequent runs, the rendered output is compared pixel-by-pixel against the baselines.
 * If a difference is detected, the test fails and saves `_actual.png` and `_diff.png`
 * images in the `snapshots/failures/` folder for debugging.
 */
@OptIn(ExperimentalTestApi::class)
class AvatarSnapshotTest {

    companion object {
        private val DEFAULT_COLORS = listOf(
            Color(0xFF92A1C6),
            Color(0xFF146A7C),
            Color(0xFFF0AB3D),
            Color(0xFFC271B4),
            Color(0xFFC20D90)
        )
        private val TEST_SIZE = 120.dp
        private val TEST_SHAPE = CircleShape
        private val TEST_NAMES = listOf("Felipe", "Maria", "Steve")
    }

    // ──────────────────────────────────────────
    // BEAM
    // ──────────────────────────────────────────

    @Test
    fun snapshotBeam_Felipe() = assertVariantSnapshot(AvatarVariant.BEAM, "Felipe")

    @Test
    fun snapshotBeam_Maria() = assertVariantSnapshot(AvatarVariant.BEAM, "Maria")

    @Test
    fun snapshotBeam_Steve() = assertVariantSnapshot(AvatarVariant.BEAM, "Steve")

    // ──────────────────────────────────────────
    // MARBLE
    // ──────────────────────────────────────────

    @Test
    fun snapshotMarble_Felipe() = assertVariantSnapshot(AvatarVariant.MARBLE, "Felipe")

    @Test
    fun snapshotMarble_Maria() = assertVariantSnapshot(AvatarVariant.MARBLE, "Maria")

    @Test
    fun snapshotMarble_Steve() = assertVariantSnapshot(AvatarVariant.MARBLE, "Steve")

    // ──────────────────────────────────────────
    // SUNSET
    // ──────────────────────────────────────────

    @Test
    fun snapshotSunset_Felipe() = assertVariantSnapshot(AvatarVariant.SUNSET, "Felipe")

    @Test
    fun snapshotSunset_Maria() = assertVariantSnapshot(AvatarVariant.SUNSET, "Maria")

    @Test
    fun snapshotSunset_Steve() = assertVariantSnapshot(AvatarVariant.SUNSET, "Steve")

    // ──────────────────────────────────────────
    // BAUHAUS
    // ──────────────────────────────────────────

    @Test
    fun snapshotBauhaus_Felipe() = assertVariantSnapshot(AvatarVariant.BAUHAUS, "Felipe")

    @Test
    fun snapshotBauhaus_Maria() = assertVariantSnapshot(AvatarVariant.BAUHAUS, "Maria")

    @Test
    fun snapshotBauhaus_Steve() = assertVariantSnapshot(AvatarVariant.BAUHAUS, "Steve")

    // ──────────────────────────────────────────
    // RING
    // ──────────────────────────────────────────

    @Test
    fun snapshotRing_Felipe() = assertVariantSnapshot(AvatarVariant.RING, "Felipe")

    @Test
    fun snapshotRing_Maria() = assertVariantSnapshot(AvatarVariant.RING, "Maria")

    @Test
    fun snapshotRing_Steve() = assertVariantSnapshot(AvatarVariant.RING, "Steve")

    // ──────────────────────────────────────────
    // PIXEL
    // ──────────────────────────────────────────

    @Test
    fun snapshotPixel_Felipe() = assertVariantSnapshot(AvatarVariant.PIXEL, "Felipe")

    @Test
    fun snapshotPixel_Maria() = assertVariantSnapshot(AvatarVariant.PIXEL, "Maria")

    @Test
    fun snapshotPixel_Steve() = assertVariantSnapshot(AvatarVariant.PIXEL, "Steve")

    // ──────────────────────────────────────────
    // EMOJI
    // ──────────────────────────────────────────

    @Test
    fun snapshotEmoji_Felipe() = assertVariantSnapshot(AvatarVariant.EMOJI, "Felipe")

    @Test
    fun snapshotEmoji_Maria() = assertVariantSnapshot(AvatarVariant.EMOJI, "Maria")

    @Test
    fun snapshotEmoji_Steve() = assertVariantSnapshot(AvatarVariant.EMOJI, "Steve")

    // ──────────────────────────────────────────
    // WACKY
    // ──────────────────────────────────────────

    @Test
    fun snapshotWacky_Felipe() = assertVariantSnapshot(AvatarVariant.WACKY, "Felipe")

    @Test
    fun snapshotWacky_Maria() = assertVariantSnapshot(AvatarVariant.WACKY, "Maria")

    @Test
    fun snapshotWacky_Steve() = assertVariantSnapshot(AvatarVariant.WACKY, "Steve")

    // ──────────────────────────────────────────
    // PIXEL_NEUTRAL
    // ──────────────────────────────────────────

    @Test
    fun snapshotPixelNeutral_Felipe() = assertVariantSnapshot(AvatarVariant.PIXEL_NEUTRAL, "Felipe")

    @Test
    fun snapshotPixelNeutral_Maria() = assertVariantSnapshot(AvatarVariant.PIXEL_NEUTRAL, "Maria")

    @Test
    fun snapshotPixelNeutral_Steve() = assertVariantSnapshot(AvatarVariant.PIXEL_NEUTRAL, "Steve")

    // ──────────────────────────────────────────
    // PIXEL_ART
    // ──────────────────────────────────────────

    @Test
    fun snapshotPixelArt_Felipe() = assertVariantSnapshot(AvatarVariant.PIXEL_ART, "Felipe")

    @Test
    fun snapshotPixelArt_Maria() = assertVariantSnapshot(AvatarVariant.PIXEL_ART, "Maria")

    @Test
    fun snapshotPixelArt_Steve() = assertVariantSnapshot(AvatarVariant.PIXEL_ART, "Steve")

    // ──────────────────────────────────────────
    // PIXEL_ANIMALS
    // ──────────────────────────────────────────

    @Test
    fun snapshotPixelAnimals_Felipe() = assertVariantSnapshot(AvatarVariant.PIXEL_ANIMALS, "Felipe")

    @Test
    fun snapshotPixelAnimals_Maria() = assertVariantSnapshot(AvatarVariant.PIXEL_ANIMALS, "Maria")

    @Test
    fun snapshotPixelAnimals_Steve() = assertVariantSnapshot(AvatarVariant.PIXEL_ANIMALS, "Steve")

    // ──────────────────────────────────────────
    // SHAPE VARIANTS (verify different shapes render correctly)
    // ──────────────────────────────────────────

    @Test
    fun snapshotBeam_RoundedCorner() = runComposeUiTest {
        setContent {
            Avatar(
                name = "Felipe",
                colors = DEFAULT_COLORS,
                variant = AvatarVariant.BEAM,
                size = TEST_SIZE,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.size(TEST_SIZE)
            )
        }
        val image = onRoot().captureToImage()
        SnapshotTestUtil.assertSnapshot("beam_rounded_Felipe", image)
    }

    // ──────────────────────────────────────────
    // DETERMINISM TEST (same name = same image)
    // ──────────────────────────────────────────

    @Test
    fun snapshotDeterminism_SameNameProducesSameImage() = runComposeUiTest {
        setContent {
            Avatar(
                name = "DeterminismTest",
                colors = DEFAULT_COLORS,
                variant = AvatarVariant.BEAM,
                size = TEST_SIZE,
                shape = TEST_SHAPE,
                modifier = Modifier.size(TEST_SIZE)
            )
        }
        val image1 = onRoot().captureToImage()
        SnapshotTestUtil.assertSnapshot("determinism_beam", image1)
    }

    // ──────────────────────────────────────────
    // Helper
    // ──────────────────────────────────────────

    private fun assertVariantSnapshot(variant: AvatarVariant, name: String) = runComposeUiTest {
        setContent {
            Avatar(
                name = name,
                colors = DEFAULT_COLORS,
                variant = variant,
                size = TEST_SIZE,
                shape = TEST_SHAPE,
                modifier = Modifier.size(TEST_SIZE)
            )
        }
        val image = onRoot().captureToImage()
        SnapshotTestUtil.assertSnapshot("${variant.name.lowercase()}_$name", image)
    }
}
