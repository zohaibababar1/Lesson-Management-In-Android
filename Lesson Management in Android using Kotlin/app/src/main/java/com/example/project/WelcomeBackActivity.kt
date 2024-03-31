package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityWelcomeBackBinding
import com.example.project.models.Lesson

class WelcomeBackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBackBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val userName = sharedPreferences.getString("user_name", "null")
        binding.welcomeTextView.text = "Welcome back, $userName!"

        sharedPreferences = getSharedPreferences("LessonPreferences", Context.MODE_PRIVATE)

        val totalLessons = getDummyLessons().size
        val completedLessons = getCompletedLessonsCount()

        val remainingLessons = totalLessons - completedLessons
        val percentage = (completedLessons.toDouble() / totalLessons.toDouble()) * 100
        val percentage1 = (remainingLessons.toDouble() / totalLessons.toDouble()) * 100
        binding.calculatepercentage.text = "You have completed ${percentage.toInt()}% of the lesson list"
        binding.calculatepercentage1.text = "You still need to complete ${percentage1.toInt()}% of the lesson list"
        binding.remainingLessonsTextView.text = "Remaining Lessons: $remainingLessons"
        binding.completedLessonsTextView.text = "Completed Lessons: $completedLessons"

        binding.proceedButton.setOnClickListener {
            startActivity(Intent(this, LessonListActivity::class.java))
            finish()
        }

        binding.resetButton.setOnClickListener {
            resetData()
            startActivity(Intent(this, EnterNameActivity::class.java))
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }
    }

    private fun getCompletedLessonsCount(): Int {
        var completedLessonsCount = 0
        for (i in 0 until getDummyLessons().size) {
            val completed = sharedPreferences.getBoolean("lesson_${i}_completed", false)
            if (completed) {
                completedLessonsCount++
            }
        }
        return completedLessonsCount
    }

    private fun resetData() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    private fun getDummyLessons(): List<Lesson> {
        val lessons = mutableListOf<Lesson>()

        lessons.add(
            Lesson(
                number = 1,
                name = "Introduction to Programming",
                length = "30 mins",
                description = "Introduction to Programming is an essential stepping stone for individuals entering the realm of computer science, software development, or any field requiring computational thinking. This course provides a comprehensive introduction to the basic principles and techniques used in programming.",
                completed = false,
                hasVideo = true,
                videoLink = "https://www.youtube.com/watch?v=ySJanhjGF4s"
            )
        )
        lessons.add(
            Lesson(
                number = 2,
                name = "Introduction to OOP",
                length = "30 mins",
                description = "Object-Oriented Programming (OOP) is a programming paradigm that revolves around the concept of \"objects\" and their interactions. It provides a way of structuring software programs to resemble real-world entities and models. Here's a breakdown of the key concepts in OOP:",
                completed = false,
                hasVideo = true,
                videoLink = "https://www.youtube.com/watch?v=PFVKjUUZMf8&t=9s&ab_channel=Simplilearn"
            )
        )
        lessons.add(
            Lesson(
                number = 3,
                name = "Introduction to Kotlin",
                length = "70 mins",
                description = "Kotlin, a statically-typed programming language, seamlessly integrates with Java, Android, and JavaScript. Renowned for conciseness and safety features, Kotlin offers null safety, extension functions, and functional programming support. Its versatility and expressiveness make it a top choice for modern development across various platforms, from server-side applications to Android mobile apps.",
                completed = false,
                hasVideo = true,
                videoLink = "https://www.youtube.com/watch?v=8YpftkkRPpQ&t=297s"
            )
        )
        lessons.add(
            Lesson(
                number = 4,
                name = "Introduction to Python",
                length = "30 mins",
                description = "Python is a versatile and beginner-friendly programming language renowned for its simplicity and readability. With its extensive libraries and frameworks, Python powers diverse applications from web development to data analysis and artificial intelligence. Its clear syntax and dynamic typing make it an ideal choice for both novices and seasoned developers.",
                completed = true,
                hasVideo = true,
                videoLink = "https://www.youtube.com/watch?v=w5xRN7EREEU"
            )
        )
        lessons.add(
            Lesson(
                number = 5,
                name = "Introduction to Java",
                length = "50 mins",
                description = "Java, a versatile and powerful programming language, is renowned for its platform independence, making it ideal for diverse applications from desktop to web development. Launched by Sun Microsystems in 1995, its robustness, portability, and rich ecosystem of libraries have cemented its status as a cornerstone in the world of software development.",
                completed = false,
                hasVideo = true,
                videoLink = "https://www.youtube.com/watch?v=sC7E1LbWf7Y"
            )
        )
        return lessons
    }
}