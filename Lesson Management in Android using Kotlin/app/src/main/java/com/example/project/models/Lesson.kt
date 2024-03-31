package com.example.project.models

import java.io.Serializable

data class Lesson(
    val number: Int,
    val name: String,
    val length: String,
    val description: String,
    val videoLink: String?,
    var completed: Boolean,
    val hasVideo: Boolean
) : Serializable