<h1 align="center">
  Google Maps Marker App
</h1>
<p align="center">
  <a href="http://developer.android.com/index.html"><img alt="Platform" src="https://img.shields.io/badge/platform-Android-green.svg"></a>
  <a href="http://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/kotlin-1.9.0-blue.svg"></a>
  <a href="https://developer.android.com/studio/releases/gradle-plugin"><img alt="Gradle" src="https://img.shields.io/badge/gradle-8.4.0-yellow.svg"></a>
</p>

## Introduction
<p>

This project uses [Jetpack Compose](https://developer.android.com/jetpack/compose) technology, in short jetpack Compose is Android’s recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.
To try out this sample app, use the latest stable version of [Android Studio](https://developer.android.com/studio). You can clone this repository or import the project from Android Studio following the steps [here](https://developer.android.com/jetpack/compose/setup#sample).
  


``` 
Note :  Live location only fetch in real-device
 ```
  
## Major Highlights

- **Jetpack Compose** for modern UI
- **Offline caching** with a **single source of truth**
- **MVVM architecture** for a clean and scalable codebase
- **[Use-Case](https://engineering.teknasyon.com/usecase-red-flags-and-best-practices-in-clean-architecture-76e2f6d921eb)** responsible for encapsulating the business logic for a single reusable task the system must perform
- **Kotlin** and **Kotlin DSL**
- **Dagger Hilt** for efficient dependency injection.
- **Room DB** for local storage of news articles
- **Coroutines** and **Flow** for asynchronous programming
- **StatFlow** for streamlined state management
- **Navigation** for smooth transitions between screens
- **Google Maps Compose** for save locations

## Feature implemented:
- **Google Maps Integration**: Incorporate Google Maps SDK to display an interactive map interface within the application.
- **Marker Management**: Implemented features to allow users to drop markers on the map, save them to the database with additional information (such as name, relation, age, and address with also the LatLongs), and retrieve saved markers for display on the Map itself.
- **Marker Deletion**: Enable users to delete saved markers from the map and database on tap of the saved marker, with a confirmation dialog to prevent accidental deletions.
  
# Preview App
| Home Screen  | Details Pop-up | Saved Location Screen |
| ------------- | ------------- | ------------- |
| <img src="assets/home_screen.png" height=500 width=250/>  | <img src="assets/details_popup.png" height=500 width=250/> | <img src="assets/saved_location_screen.png" height=500 width=250/> | 

| Delete Pop-up | Full Video |
| ------------- | ------------- | 
| <img src="assets/details_popup.png" height=500 width=250/>  | <video src="https://github.com/roshansharma824/Google-Maps-Marker-App/assets/85518522/52fdfa25-cc6a-4111-89a1-c2da1bb29571" height=500 width=250/> |


## 🚀 About Me
Hi there! My name is Roshan Sharma, I work as a Android Developer and like to expand my skill set in my spare time.

If you have any questions or want to connect, feel free to reach out to me on :

- [LinkedIn](https://www.linkedin.com/in/imrks/)
