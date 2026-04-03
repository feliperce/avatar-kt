package io.github.feliperce.avatar

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.test.fail

/**
 * Utility object for snapshot-based UI testing.
 *
 * Provides functionality to save a rendered [ImageBitmap] as a PNG file,
 * load existing baseline images, and compare captured images pixel-by-pixel
 * against baselines to detect visual regressions.
 */
object SnapshotTestUtil {

    private val snapshotsDir = File("src/jvmTest/snapshots")
    private val failuresDir = File("src/jvmTest/snapshots/failures")

    /**
     * Saves the given [ImageBitmap] as a PNG file.
     *
     * @param image The captured image to save.
     * @param file The destination file.
     */
    fun saveImage(image: ImageBitmap, file: File) {
        file.parentFile?.mkdirs()
        val bufferedImage = image.toAwtImage()
        ImageIO.write(bufferedImage, "png", file)
    }

    /**
     * Loads a [BufferedImage] from the given file, or returns null if the file doesn't exist.
     *
     * @param file The image file to load.
     * @return The loaded [BufferedImage], or null if the file does not exist.
     */
    fun loadBaseline(file: File): BufferedImage? {
        if (!file.exists()) return null
        return ImageIO.read(file)
    }

    /**
     * Compares a captured [ImageBitmap] against a stored baseline PNG.
     *
     * If no baseline exists, the captured image is saved as the new baseline.
     * If a baseline exists, a pixel-by-pixel comparison is performed.
     * On mismatch, the actual output is saved to the failures directory and the test fails.
     *
     * @param testName A unique name identifying this snapshot (used as file name).
     * @param captured The [ImageBitmap] captured from the rendered Composable.
     * @param maxPixelDiffPercent The maximum allowed percentage of differing pixels (default 0.0 = exact match).
     */
    fun assertSnapshot(
        testName: String,
        captured: ImageBitmap,
        maxPixelDiffPercent: Double = 0.0
    ) {
        val baselineFile = File(snapshotsDir, "$testName.png")
        val capturedBuffered = captured.toAwtImage()

        val baseline = loadBaseline(baselineFile)
        if (baseline == null) {
            snapshotsDir.mkdirs()
            saveImage(captured, baselineFile)
            println("📸 Baseline created: ${baselineFile.absolutePath}")
            return
        }

        if (baseline.width != capturedBuffered.width || baseline.height != capturedBuffered.height) {
            failuresDir.mkdirs()
            val failureFile = File(failuresDir, "${testName}_actual.png")
            ImageIO.write(capturedBuffered, "png", failureFile)
            fail(
                "Snapshot size mismatch for '$testName': " +
                "baseline=${baseline.width}x${baseline.height}, " +
                "actual=${capturedBuffered.width}x${capturedBuffered.height}. " +
                "Actual saved to: ${failureFile.absolutePath}"
            )
        }

        val totalPixels = baseline.width * baseline.height
        var diffPixels = 0

        for (y in 0 until baseline.height) {
            for (x in 0 until baseline.width) {
                if (baseline.getRGB(x, y) != capturedBuffered.getRGB(x, y)) {
                    diffPixels++
                }
            }
        }

        val diffPercent = (diffPixels.toDouble() / totalPixels) * 100.0

        if (diffPercent > maxPixelDiffPercent) {
            failuresDir.mkdirs()
            val failureFile = File(failuresDir, "${testName}_actual.png")
            ImageIO.write(capturedBuffered, "png", failureFile)

            val diffImage = createDiffImage(baseline, capturedBuffered)
            val diffFile = File(failuresDir, "${testName}_diff.png")
            ImageIO.write(diffImage, "png", diffFile)

            fail(
                "Snapshot mismatch for '$testName': $diffPixels/$totalPixels pixels differ (${String.format("%.2f", diffPercent)}%). " +
                "Max allowed: ${String.format("%.2f", maxPixelDiffPercent)}%. " +
                "Actual saved to: ${failureFile.absolutePath}, " +
                "Diff saved to: ${diffFile.absolutePath}"
            )
        }
    }

    /**
     * Creates a visual diff image highlighting pixel differences in red.
     *
     * @param baseline The expected baseline image.
     * @param actual The captured actual image.
     * @return A [BufferedImage] where identical pixels are dimmed and differing pixels are bright red.
     */
    private fun createDiffImage(baseline: BufferedImage, actual: BufferedImage): BufferedImage {
        val diff = BufferedImage(baseline.width, baseline.height, BufferedImage.TYPE_INT_ARGB)
        for (y in 0 until baseline.height) {
            for (x in 0 until baseline.width) {
                if (baseline.getRGB(x, y) != actual.getRGB(x, y)) {
                    diff.setRGB(x, y, 0xFFFF0000.toInt())
                } else {
                    val gray = (baseline.getRGB(x, y) and 0xFF) / 3
                    diff.setRGB(x, y, (0xFF shl 24) or (gray shl 16) or (gray shl 8) or gray)
                }
            }
        }
        return diff
    }
}
