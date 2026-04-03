![Kotlin Badge](https://img.shields.io/badge/kotlin-v2.3.20-%237F52FF?logo=kotlin)
![Compose Badge](https://img.shields.io/badge/compose_multiplatform-v1.10.3-%234285F4?logo=jetpackcompose)
![Platform Windows Badge](https://img.shields.io/badge/platform-windows-%230078D4?logo=windows)
![Platform Linux Badge](https://img.shields.io/badge/platform-Linux-%23FCC624?logo=linux)
![Platform MacOS Badge](https://img.shields.io/badge/platform-macOS-%23000000?logo=macos)
![Android Badge](https://img.shields.io/badge/platform-Android-%2334A853?logo=android)
![iOS badge](https://img.shields.io/badge/platform-iOS-23000000?logo=ios&color=%23000000)
![WebAssembly](https://img.shields.io/badge/platform-WebAssembly-23000000?logo=webassembly&color=%23654FF0)

# Avatar KT

A Kotlin Multiplatform (KMP) library that generates unique, deterministic avatars completely offline using Compose Multiplatform Canvas. Based on [Boring Avatars](https://github.com/boringdesigners/boring-avatars) and [DiceBear](https://github.com/dicebear/dicebear).

<img width="982" height="388" alt="Captura de tela de 2026-04-02 20-41-57" src="https://github.com/user-attachments/assets/983f92fd-eb33-4433-91de-f06333d69193" />


## Supported Platforms
- Android
- iOS
- Desktop (JVM)
- Web (Wasm & JS)

## Installation

Add the dependency to your `commonMain` source set:

```kotlin
// build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.feliperce:avatarkt:0.3.0")
        }
    }
}
```

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
- `:avatarkt`: The main library code (Canvas-based).
- `:composeApp`: Example application demonstrating all variants.

## License

[MIT](LICENSE)
