<p align="center"><a><img src="https://github.com/chiragramani98/MVVM-WeatherApp/blob/master/media/App%20Screenshots.png" width="700"></a></p>

# Drop Weather :

An Android weather application implemented using the MVVM pattern, Retrofit2, Dagger Hilt, LiveData, ViewModel, Coroutines, Navigation Components, Data Binding and some other libraries from the [Android Jetpack](https://developer.android.com/jetpack) . Drop Weather fetches data from the [OpenWeatherMap API](https://openweathermap.org/api) to provide real time weather information. It also makes use of the [Google Places API] service which enables you search for weather conditions of various locations.

## Architecture
The architecture of this application relies and complies with the following points below:
* A single-activity architecture, using the [Navigation Components](https://developer.android.com/guide/navigation) to manage fragment operations.
* Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)(MVVM) which facilitates a separation of development of the graphical user interface.
* [Android architecture components](https://developer.android.com/topic/libraries/architecture/) which help to keep the application robust, testable, and maintainable.

<p align="center"><a><img src="https://github.com/chiragramani98/MVVM-WeatherApp/blob/master/media/final_architecture.png" width="700"></a></p>

## Technologies used:

* [Retrofit](https://square.github.io/retrofit/) a REST Client for Android which makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.
* [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to handle data in a lifecycle-aware fashion.
* [Navigation Component](https://developer.android.com/guide/navigation) to handle all navigations and also passing of data between destinations.
* [Google Places API](https://developers.google.com/maps/documentation/places/android-sdk/overview) to quickly and seamlessly implement search within the application.
* [Material Design](https://material.io/develop/android/docs/getting-started/) an adaptable system of guidelines, components, and tools that support the best practices of user interface design.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) Coroutines help in managing background threads and reduces the need for callbacks.
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) to declaratively bind UI components in layouts to data sources.
* [Paging Library](https://developer.android.com/topic/libraries/architecture/paging) helps you load and display small chunks of data at a time.
* [Android KTX](https://developer.android.com/kotlin/ktx) which helps to write more concise, idiomatic Kotlin code.
* [Preferences](https://developer.android.com/guide/topics/ui/settings) to create interactive settings screens.

## Installation
Drop Weather requires a minimum API level of 21. Clone the repository. You will need an API key i.e. `API_KEY` from [Open Weather](https://openweathermap.org/) to request data. If you don’t already have an account, you will need to create one in order to request an API Key. Also, you will need to enable Maps API and generate API Key from Google Developer Console

Each record follows this structure:
  
````

In your project's root directory, inside the `local.properties` file (create one if unavailable) include the following lines:

````properties
WEATHER_API_KEY = "YOUR_API_KEY"
MAPS_API_KEY = "YOUR_API_KEY"


````
## Contribution
All contributions are welcome. If you are interested in seeing a particular feature implemented in this app, please open a new issue after which you can make a PR!

