# Project Guidelines: Avatar Kotlin Multiplatform

This project is a Kotlin Multiplatform (KMP) library based on the popular [Boring Avatars](https://github.com/boringdesigners/boring-avatars) library. It generates unique, random avatars offline using Compose Multiplatform Canvas.

## Project Structure

- `:library`: The core library module containing the logic for generating avatars.
    - `src/commonMain`: Shared logic for all platforms (Android, iOS, Desktop, Web).
    - `src/commonMain/kotlin/io/github/feliperce/avatar/`: Contains the different avatar variants:
        - `AvatarBauhaus.kt`
        - `AvatarBeam.kt`
        - `AvatarMarble.kt`
        - `AvatarPixel.kt`
        - `AvatarRing.kt`
        - `AvatarSunset.kt`
        - `BoringAvatar.kt` (Main entry point)
- `:composeApp`: A sample application demonstrating how to use the library on different platforms.
    - Includes implementations for Android, iOS, JVM (Desktop), Web (Wasm & JS).

## Tech Stack

- **Kotlin Multiplatform (KMP)**
- **Compose Multiplatform** (Canvas-based rendering)
- **Gradle** for build and dependency management.

## Guidelines for Junie

### 1. Code Style and Design
- Follow the existing Kotlin style and naming conventions.
- Maintain consistency with the current implementation of avatar variants.
- Use `dp` for sizes where appropriate to ensure multiplatform compatibility.
- Ensure any new variants or modifications are implemented in `commonMain` to support all platforms.

### 2. Building the Project
- To build the library: `./gradlew :library:build`
- To build the sample app: `./gradlew :composeApp:assemble`
- Ensure all builds pass before submitting changes.

### 3. Testing
- Currently, the project does not have automated tests.
- When fixing bugs or adding new features, Junie **MUST** create reproduction tests or new test cases in the appropriate module (preferably in `commonMain`'s `test` source set).
- Use `./gradlew test` to run all tests.

### 4. Modifying the Project
- When making changes to the library logic, verify the changes using the `:composeApp` sample application if possible.
- Update `README.md` if any new features or variants are added.
- Always check `.junie/guidelines.md` for any updates to these instructions.

### 5. Multiplatform Considerations
- Be mindful of platform-specific code. Avoid using platform-specific APIs in `commonMain` unless necessary, and use `expect`/`actual` if needed.
- Supported targets include: Android, iOS (x64, Arm64, Simulator), JVM, JS (Browser), and WasmJS (Browser).
