[![official project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

# Kotlin Multiplatform Mobile Sample

This is a **Kotlin Multiplatform Mobile (KMM) Project**. It includes iOS and Android applications with a native UI and a module with code shared on iOS and Android.

## Features

This sample demonstrates basic KMM features:
* Using platform-specific APIs with the expect/actual mechanism (see `Platform.kt`)
* Tests for Shared Module (see `CalculatorTest.kt`, `iosTest.kt`, `androidTest.kt`)

## How to use

With the [KMM plugin for Android Studio](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) you can run, test, and debug shared code on both platforms without switching IDEs. Run and debug the application by selecting the corresponding configuration in the **Run configuration** menu. Run and debug shared module tests by pressing the gutter icon on a test class or method.

## Related links

* Visit [Kotlin Multiplatform Mobile Developer Portal](https://kotlinlang.org/lp/mobile/) to learn more about the technology
* Check out the [Networking and data storage with KMM hands-on](https://play.kotlinlang.org/hands-on/Networking%20and%20Data%20Storage%20with%20Kotlin%20Multiplatfrom%20Mobile/) lab to learn how to create a mobile application for Android and iOS with a shared codebase with Ktor and SQLDelight.

# BookHaven

**BookHaven** é um aplicativo dedicado ao download de livros. Oferecemos uma plataforma para conectar leitores que desejam compartilhar seus livros com aqueles que buscam novas leituras emocionantes.

## Funcionalidades Principais

- **Download de Livros:** Encontre e baixe uma ampla variedade de livros gratuitos disponíveis para todos os gostos.
- **Perfil do Usuário:** Crie seu perfil, adicione livros à sua biblioteca e acompanhe suas leituras.

## Iniciativa Futura: Doação de Livros

Estamos planejando introduzir a funcionalidade de doação de livros em futuras atualizações do **BookHaven**. Com essa iniciativa, os usuários poderão compartilhar seus livros usados ou não desejados com a comunidade. Fique atento às atualizações!

## Como Contribuir

Se você deseja contribuir para o desenvolvimento do **BookHaven**, siga estas etapas:

1. Faça um fork do repositório (clique no botão Fork no topo desta página).
2. Clone o fork em sua máquina local.
   ```bash
   git clone https://github.com/seunome/BookHaven.git
