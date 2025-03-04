# android-puppypal
Special note: While this is a fully functioning Android App, built in Kotlin. To protect IP, code sampling & Android skill-demoing purposes, I've updated the theme of the app to fetch "Therapy Puppies" using a 3rd party API call, parse the data, then use the user's precise location to sort the list by distance.

PuppyPal
# TL;DR
PuppyPal is an Android app that lets users browse adoptable puppies with a sleek, searchable list, sorted by distance from Columbus, Ohio. Built with Kotlin, it features Jetpack Navigation, Retrofit API integration, and View Binding—highlighting modern Android practices.

# General Overview
PuppyPal is a fun, user-friendly app designed to help puppy lovers explore a list of adorable adoptable dogs. From the welcoming home screen, you can jump into browsing a collection of puppies or tweak your preferences in settings. The main attraction is the browse screen, where you can scroll through puppies—complete with their names, breeds, ages, and how far they are from Columbus, Ohio—while filtering by breed with a handy search bar. Whether you’re dreaming of a Golden Retriever or a Pug, PuppyPal makes it easy to find your next furry friend. It’s a simple yet polished app that shows off a clean design and smooth navigation, perfect for anyone who loves dogs or wants to see Android development in action.

# Highlights:
Welcoming Start: A friendly home screen with quick access to browsing or settings.
Puppy Browsing: A scrollable list of puppies (pulled from a web service) from Northern Ohio cities, sorted by distance.
Search Made Simple: Type a breed to instantly narrow down the list.
Puppy-Themed Design: Warm, playful colors inspired by our four-legged friends.
Technical Outline
PuppyPal is a Kotlin-based Android application leveraging modern tools and architectures to deliver a robust demo experience. It integrates RESTful API calls, reactive UI updates, and navigation, showcasing key Android development skills.

# Functionality and Highlights:
Architecture:
MVVM Pattern: Uses PuppyViewModel to manage puppy data and business logic, observed via LiveData in fragments.
Jetpack Navigation: Implements a single-activity architecture with MainActivity hosting a NavHostFragment, navigating between HomeFragment, BrowseFragment, and SettingsFragment via nav_graph.xml.
View Binding: Employs type-safe binding (e.g., FragmentHomeBinding, FragmentBrowseBinding) across all fragments for efficient view management.
Core Features:
API Integration: Fetches puppy data (name, breed, age, lat/lon) from https://adamgumm.com/puppies.php using Retrofit (ApiService.kt), parsed with Gson into a Puppy data class.
Puppy List Display: BrowseFragment renders a RecyclerView with PuppyAdapter, showing 18 puppies sorted by distance from Columbus, Ohio (39.9612, -82.9988), calculated using Location.distanceBetween.
Real-Time Filtering: A TextWatcher in BrowseFragment filters the puppy list by breed as users type, updating the RecyclerView dynamically.
Settings Persistence: SettingsFragment saves preferred breeds to SharedPreferences, retrievable on relaunch.

# Technical Highlights:
Kotlin Coroutines: Powers async API calls in PuppyViewModel with viewModelScope.launch, ensuring smooth network operations.
Distance Calculation: Hardcodes Columbus as the reference point for demo reliability, with distances in miles displayed in PuppyAdapter.
Theming: Custom Theme.PuppyPal (AppCompat-based) in styles.xml applies puppy-inspired colors (browns, golds) across the app.
Logging: Extensive Log.d usage for debugging puppy fetch and distance calculations, visible in Logcat.

# Files:
HomeFragment.kt: Entry screen with navigation to browse/settings.
BrowseFragment.kt: Displays and filters the puppy list.
SettingsFragment.kt: Manages user preferences.
PuppyViewModel.kt: Handles API data and distance sorting.
ApiService.kt: Retrofit interface for puppy data.
PuppyAdapter.kt: Binds puppy data to RecyclerView.
Dependencies:
Jetpack: navigation-fragment-ktx, lifecycle-viewmodel-ktx, recyclerview
Retrofit: retrofit, converter-gson
Kotlin: kotlinx-coroutines-android
Material: com.google.android.material
Future Potential:

# Enable user location sorting with FusedLocationProviderClient (code commented out).
Add loading/error states and UI animations for polish.
Expand to paginated API results.
PuppyPal demonstrates proficiency in Kotlin, Android Jetpack, and RESTful integration.

