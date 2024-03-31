package com.example.project

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.project.models.Lesson

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var lesson: Lesson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        lesson = intent.getSerializableExtra("lesson") as Lesson
        webView = findViewById(R.id.webView)

        setupWebView()
        loadVideo()
    }

    private fun setupWebView() {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
    }

    private fun loadVideo() {
        val videoLink = lesson.videoLink ?: return // Return early if videoLink is null
        webView.loadUrl(videoLink)
    }
}