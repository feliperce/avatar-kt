# Avatar Kotlin Multiplatform (Boring Avatars KMP)

This project is a Kotlin Multiplatform (KMP) library based on the popular [Boring Avatars](https://github.com/boringdesigners/boring-avatars) library. It randomly generates unique avatars completely offline using Compose Multiplatform Canvas.

## Support
- Android
- iOS
- Desktop (JVM)
- Web (Wasm & JS)

## Available Variants
- `BEAM`: Friendly faces with geometric shapes.
- `MARBLE`: Organic and abstract patterns with gradients.
- `SUNSET`: Smooth sunset gradients.
- `BAUHAUS`: Minimalist geometric shapes.
- `RING`: Colorful concentric rings.
- `PIXEL`: 8x8 pixel art.

## How to use

Add the dependency to your Compose Multiplatform project:

```kotlin
// commonMain
implementation(project(":library"))
```

Usage example:

```kotlin
val colors = listOf(
    Color(0xFF92A1C6),
    Color(0xFF146A7C),
    Color(0xFFF0AB3D),
    Color(0xFFC271B4),
    Color(0xFFC20D90)
)

BoringAvatar(
    name = "Abacate",
    colors = colors,
    variant = AvatarVariant.BEAM,
    size = 80.dp
)
```

## Project Structure
- `:library`: The main library code (Canvas-based).
- `:composeApp`: Example application demonstrating all variants.

---