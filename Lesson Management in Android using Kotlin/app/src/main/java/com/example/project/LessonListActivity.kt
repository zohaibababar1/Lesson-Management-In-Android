package com.example.project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.adapters.LessonAdapter
import com.example.project.models.Lesson

class LessonListActivity : AppCompatActivity(), LessonAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var lessonAdapter: LessonAdapter
    private lateinit var lessons: MutableList<Lesson>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)
        sharedPreferences = getSharedPreferences("LessonPreferences", Context.MODE_PRIVATE)
        recyclerView = findViewById(R.id.lessonRecyclerView)
        lessons = getDummyLessons().toMutableList()
        loadLessonStatuses()
        lessonAdapter = LessonAdapter(lessons, this)
        recyclerView.adapter = lessonAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadLessonStatuses() {
        for (i in 0 until lessons.size) {
            val completed = sharedPreferences.getBoolean("lesson_${i}_completed", false)
            lessons[i].completed = completed
        }
    }

    private fun saveLessonStatus(position: Int) {
        val lesson = lessons[position]
        val editor = sharedPreferences.edit()
        editor.putBoolean("lesson_${position}_completed", lesson.completed)
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

    override fun onItemClick(position: Int) {
        val lesson = lessons[position]
        val intent = Intent(this, LessonDetailsActivity::class.java)
        intent.putExtra("lesson", lesson)
        startActivityForResult(intent, LESSON_DETAILS_REQUEST)
    }

    override fun onMarkCompleteClick(position: Int) {
        val lesson = lessons[position]
        lesson.completed = true
        lessons[position] = lesson
        lessonAdapter.notifyItemChanged(position)
        saveLessonStatus(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LESSON_DETAILS_REQUEST && resultCode == Activity.RESULT_OK) {
            val lesson = data?.getSerializableExtra("lesson") as Lesson
            val position = lessons.indexOfFirst { it.number == lesson.number }
            if (position != -1) {
                lessons[position] = lesson
                lessonAdapter.notifyItemChanged(position)
                saveLessonStatus(position)
            }
        }
    }
    override fun onResume() {
        loadLessonStatuses()
        super.onResume()

    }
    companion object {
        const val LESSON_DETAILS_REQUEST = 1001
    }
}