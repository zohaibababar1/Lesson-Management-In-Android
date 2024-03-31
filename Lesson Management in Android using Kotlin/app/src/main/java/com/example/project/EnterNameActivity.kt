package com.example.project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.project.databinding.ActivityEnterNameBinding
import com.google.android.material.snackbar.Snackbar

class EnterNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnterNameBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val continueButton = binding.continueButton
        val enterNameEditText = binding.enterNameEditText

        continueButton.setOnClickListener {
            val enteredName = enterNameEditText.text.toString()
            if (enteredName.isNullOrEmpty()) {
                val snackbar = Snackbar.make(binding.root, "Name Field must be filled", Snackbar.LENGTH_LONG)
            }
            else{
                saveName(enteredName)
                startActivity(Intent(this, LessonListActivity::class.java))
                finish()
                }
        }
    }

    private fun saveName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("user_name", name)
        editor.putBoolean("first_time", false)
        editor.apply()
    }
}