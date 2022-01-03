package com.example.mywebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
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
        //webView.loadUrl("https://www.naver.com")
//        webView.loadUrl("file:///android_asset/delicious/index.html")
        webView.loadUrl("file:///android_asset/led/control_matrix.html")

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){ //만약 백버튼을 누르고 뒤로갈 페이지가 있다면
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}