package com.adamgumm.puppypal

data class Puppy(
    val id: Int,
    val name: String,
    val breed: String,
    val age: Int,
    val lat: Double,
    val lon: Double,
    val distance: Float? = null
)
