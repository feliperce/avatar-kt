# Avatar Kotlin Multiplatform (Boring Avatars KMP)

Este projeto é uma biblioteca Kotlin Multiplatform (KMP) baseada na popular biblioteca [Boring Avatars](https://github.com/boringdesigners/boring-avatars). Ela gera avatares aleatórios e únicos de forma offline usando Compose Multiplatform Canvas.

## Suporte
- Android
- iOS
- Desktop (JVM)
- Web (Wasm & JS)

## Variantes Disponíveis
- `BEAM`: Rostos amigáveis com formas geométricas.
- `MARBLE`: Padrões orgânicos e abstratos com gradientes.
- `SUNSET`: Gradientes suaves de pôr do sol.
- `BAUHAUS`: Formas geométricas minimalistas.
- `RING`: Anéis concêntricos coloridos.
- `PIXEL`: Arte em pixel 8x8.

## Como usar

Adicione a dependência no seu projeto Compose Multiplatform:

```kotlin
// commonMain
implementation(project(":library"))
```

Exemplo de uso:

```kotlin
val colors = listOf(
    Color(0xFF92A1C6),
    Color(0xFF146A7C),
    Color(0xFFF0AB3D),
    Color(0xFFC271B4),
    Color(0xFFC20D90)
)

BoringAvatar(
    name = "Junie",
    colors = colors,
    variant = AvatarVariant.BEAM,
    size = 80.dp
)
```

## Estrutura do Projeto
- `:library`: O código principal da biblioteca (Canvas-based).
- `:composeApp`: Aplicativo de exemplo demonstrando todas as variantes.

---