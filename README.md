# Avatar KT

A Kotlin Multiplatform (KMP) library that generates unique, deterministic avatars completely offline using Compose Multiplatform Canvas. Based on [Boring Avatars](https://github.com/boringdesigners/boring-avatars) and [DiceBear](https://github.com/dicebear/dicebear).

## Supported Platforms
- Android
- iOS
- Desktop (JVM)
- Web (Wasm & JS)

## Available Variants
- `BEAM`: Friendly faces with geometric shapes (Boring Avatars port).
- `MARBLE`: Organic abstract patterns with gradients (Boring Avatars port).
- `SUNSET`: Smooth sunset gradients (Boring Avatars port).
- `BAUHAUS`: Minimalist geometric shapes (Boring Avatars port).
- `RING`: Colorful concentric rings (Boring Avatars port).
- `PIXEL`: 8x8 pixel mosaic (Boring Avatars port).
- `EMOJI`: Random emoji on a colored background.
- `WACKY`: Playful characters based on DiceBear thumbs.
- `PIXEL_NEUTRAL`: Clean pixel-art faces from DiceBear.
- `PIXEL_ART`: Full pixel-art faces with hair, hats and accessories.
- `PIXEL_ANIMALS`: Pixel animal avatars.

## Usage

```kotlin
// Minimal - uses default colors and BEAM variant
Avatar(name = "Maria")

// Customized
Avatar(
    name = "Maria",
    colors = listOf(
        Color(0xFF92A1C6),
        Color(0xFF146A7C),
        Color(0xFFF0AB3D),
        Color(0xFFC271B4),
        Color(0xFFC20D90)
    ),
    variant = AvatarVariant.PIXEL_ART,
    size = 80.dp,
    shape = RoundedCornerShape(16.dp)
)
```

## Project Structure
- `:library`: The main library code (Canvas-based).
- `:composeApp`: Example application demonstrating all variants.
