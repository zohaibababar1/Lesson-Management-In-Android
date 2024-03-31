package com.example.project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (isFirstTime()) {
            startActivity(Intent(this, EnterNameActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, WelcomeBackActivity::class.java))
            finish()
        }
    }

    private fun isFirstTime(): Boolean {
        return sharedPreferences.getBoolean("first_time", true)
    }
}




