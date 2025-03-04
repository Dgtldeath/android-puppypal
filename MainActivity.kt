package com.adamgumm.puppypal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.NavController
import com.adamgumm.puppypal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            val navController = findNavController(R.id.nav_host_fragment)

        } catch (e: Exception) {
            android.util.Log.e("MainActivity", "NavController failed: ${e.message}")
            e.printStackTrace()  // Log the error
        }
    }
}
