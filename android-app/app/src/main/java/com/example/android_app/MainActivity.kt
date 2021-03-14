package com.example.android_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(R.color.white)
        setNavigationBarColor(R.color.grey_300)
        BottomNavMediator(binding, supportFragmentManager)
    }
}