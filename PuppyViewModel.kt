package com.adamgumm.puppypal

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log

class PuppyViewModel : ViewModel() {
    private val _puppies = MutableLiveData<List<Puppy>>()
    val puppies: LiveData<List<Puppy>> get() = _puppies

    private val api = ApiService.create()

    fun fetchPuppies(context: android.content.Context) {
        viewModelScope.launch {
            try {
                val response = api.getPuppies()
                if (response.isSuccessful) {
                    val puppyList = response.body() ?: emptyList()
                    Log.d("PuppyViewModel", "Fetched ${puppyList.size} puppies: ${puppyList.map { it.name }}")

                    // Hardcode Columbus, Ohio (39.9612, -82.9988) TODO: UserLocation v2
                    val columbus = Location("Columbus").apply {
                        latitude = 39.9612
                        longitude = -82.9988
                    }
                    Log.d("PuppyViewModel", "Using hardcoded Columbus: ${columbus.latitude}, ${columbus.longitude}")

                    val sortedPuppies = puppyList.map { puppy ->
                        val results = FloatArray(1)
                        Location.distanceBetween(
                            columbus.latitude, columbus.longitude,
                            puppy.lat, puppy.lon,
                            results
                        )
                        val distance = results[0] / 1609.34f // Meters to miles
                        Log.d("PuppyViewModel", "Puppy ${puppy.name}: $distance miles from Columbus")
                        puppy.copy(distance = distance)
                    }.sortedBy { it.distance }

                    Log.d("PuppyViewModel", "Posting sorted puppies: ${sortedPuppies.map { "${it.name}: ${it.distance}" }}")
                    _puppies.postValue(sortedPuppies)
                } else {
                    Log.e("PuppyViewModel", "API failed: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("PuppyViewModel", "Network error: ${e.message}")
            }
        }
    }

    // Kept for future user location support—can be private or removed for now
    private fun sortPuppiesByDistance(userLocation: Location?) {
        val currentPuppies = _puppies.value ?: return
        Log.d("PuppyViewModel", "Sorting ${currentPuppies.size} puppies with passed location: $userLocation")

        val columbus = Location("Columbus").apply {
            latitude = 39.9612
            longitude = -82.9988
        }
        Log.d("PuppyViewModel", "Using hardcoded Columbus: ${columbus.latitude}, ${columbus.longitude}")

        val sortedPuppies = currentPuppies.map { puppy ->
            val results = FloatArray(1)
            Location.distanceBetween(
                columbus.latitude, columbus.longitude,
                puppy.lat, puppy.lon,
                results
            )
            val distance = results[0] / 1609.34f
            Log.d("PuppyViewModel", "Puppy ${puppy.name}: $distance miles from Columbus")
            puppy.copy(distance = distance)
        }.sortedBy { it.distance }

        _puppies.postValue(sortedPuppies)
    }
}
