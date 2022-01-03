package com.example.ledmetrix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webview)
        //webview Settings
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportMultipleWindows(true)
        }
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        val html = "<html><bode><h1>Welcome to WebView</h1></body></html>"
        //webView.loadData(html, "text/html", "utf-8")
//        webView.loadUrl("https://www.google.com")
        webView.loadUrl("file:///asset/control_matrix.html")
    }
}