package com.example.project

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.project.models.Lesson

class LessonDetailsActivity : AppCompatActivity() {

    private lateinit var lesson: Lesson
    private lateinit var lessons: MutableList<Lesson>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)

        lessons = getDummyLessons().toMutableList()

        lesson = intent.getSerializableExtra("lesson") as Lesson
        val lessonNumber: TextView = findViewById(R.id.lessonNumberTextView)
        var lessonName: TextView = findViewById(R.id.lessonNameTextView)
        var lessonDescription : TextView=findViewById(R.id.lessonDescriptionTextView)
        var lessonLength : TextView=findViewById(R.id.lessonLengthTextView)


        val position = lessons.indexOfFirst { it.number == lesson.number }
        for (i in 0 until position+1) {
            lessonNumber.text="LESSON NUMBER :${i+1}"
            lessonName.text= lesson.name
            lessonDescription.text= lesson.description
            lessonLength.text = lesson.length
        }



        val markCompleteButton: Button = findViewById(R.id.markCompleteButton)
        markCompleteButton.setOnClickListener {
            markLessonCompleted()
        }

        val watchLessonButton: Button = findViewById(R.id.watchLessonButton)
        watchLessonButton.setOnClickListener {
            watchLesson()
        }
    }

    private fun markLessonCompleted() {
        lesson.completed = true
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("lesson", lesson)
        })
        finish()
    }

    private fun watchLesson() {
        val videoUri = Uri.parse(lesson.videoLink)
        val intent = Intent(Intent.ACTION_VIEW, videoUri)
        startActivity(intent)
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