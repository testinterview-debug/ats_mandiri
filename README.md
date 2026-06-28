# Maulidani Mahmud

# MovieDB Explorer 🎬[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-Material_3-green.svg)](https://developer.android.com/jetpack/compose)
[![Architecture](https://img.shields.io/badge/Architecture-Clean_Architecture-orange.svg)](https://developer.android.com/topic/architecture)
[![License](https://img.shields.io/badge/License-MIT-purple.svg)](LICENSE)

**MovieDB Explorer** is a high-performance Android application built with the latest industry standards. It provides a seamless experience for discovering movies by genre, viewing detailed metadata, watching trailers, and reading community reviews via the [TMDB API](https://www.themoviedb.org/documentation/api).

---

## 🚀 Key Features

- **Dynamic Genre Discovery:** Browse movies organized by official TMDB categories (Action, Comedy, Horror, etc.).
- **Infinite Scrolling:** Powered by **Paging 3** for a smooth browsing experience that loads content dynamically.
- **Rich Media Integration:** High-resolution posters via **Coil** and embedded YouTube trailer support.
- **Community Insights:** Direct access to global user ratings and detailed movie reviews.
- **Modern UI/UX:** Built entirely with **Jetpack Compose** and **Material Design 3**.
- **Resilient Connectivity:** Graceful handling of network states, including "No Internet" UI and retry logic.

---

## 🏗️ Technical Architecture

The project is engineered using **Clean Architecture** and **MVVM (Model-View-ViewModel)**. This separation ensures the code is highly maintainable, testable, and scalable.

### Layers:
1. **Presentation:** Jetpack Compose screens that observe UI state from ViewModels.
2. **Domain:** The "Brain" containing business rules, entities, and specific Use Cases.
3. **Data:** The implementation layer handling Retrofit API services and Data Mappers.

---

## 🛠️ Technology Stack

| Category | Technology | Purpose |
| :--- | :--- | :--- |
| **Language** | Kotlin | Modern, safe language for Android. |
| **UI Framework** | Jetpack Compose | Declarative UI toolkit. |
| **Design System** | Material 3 | Google’s latest design guidelines. |
| **Dependency Injection** | Hilt | Standardized management of object lifecycles. |
| **Networking** | Retrofit / OkHttp | REST API communication. |
| **Image Loading** | Coil | Lightweight, Kotlin-first image loading. |
| **Asynchrony** | Coroutines / Flow | Modern reactive data streams. |

---

## 📂 Project Structure

app

├── core           # Base classes, common UI theme, and shared utilities

├── data           # API services, DTO Mappers, and Repository implementations

├── domain         # Business logic, Entity Models, and Use Cases

└── presentation   # UI components

      ├── genre    # Genre Discovery Screen
      
      ├── movie    # Movie Listings (Paginated)
      
      └── detail   # Movie Insights, Trailers, and Reviews
      

---

## ⚙️ Setup Instructions

### 1. Get an API Key
Sign up at [The Movie Database (TMDB)](https://www.themoviedb.org/) and generate your API Key (v3 auth).

### 2. Configure Environment
Add your API key to your `local.properties` file in the project root:

TMDB_API_KEY=your_api_key_here

BASE_URL=https://api.themoviedb.org/3/

### 3. Build & Run
1. Open the project in Android Studio (Ladybug or newer).
2. Sync Gradle and run the `app` module on an emulator or physical device.

---

## 📜 License

Copyright 2026
Developed by Maulidani Mahmud

---
**Developed with ❤️ for the Android Community.**
