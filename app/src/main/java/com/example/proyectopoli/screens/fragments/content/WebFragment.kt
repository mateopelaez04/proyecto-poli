package com.example.proyectopoli.screens.fragments.content

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URL

@Composable
fun WebFragment() {
    var searchQuery by remember { mutableStateOf("") }
    var searchUrl by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.background(Color.White),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Ingresar URL",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    val finalUrl = if (searchQuery.startsWith("http://") || searchQuery.startsWith("https://")) {
                        searchQuery
                    } else {
                        "https://${searchQuery}"
                    }

                    if (isValidUrl(finalUrl)) {
                        searchUrl = finalUrl
                        showError = false
                    } else {
                        showError = true
                    }
                }) {
                    Text("Ir")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (showError) {
                LaunchedEffect(snackbarHostState) {
                    snackbarHostState.showSnackbar("La URL no es válida.")
                }
            }

            if (searchUrl.isNotEmpty()) {
                WebViewContainer(url = searchUrl)
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF0F0F0)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Introduce una URL para comenzar",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

fun isValidUrl(url: String): Boolean {
    return try {
        URL(url)
        true
    } catch (e: Exception) {
        false
    }
}

@Composable
fun WebViewContainer(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return false
                    }
                }
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                loadUrl(url)
            }
        },
        update = { view -> view.loadUrl(url) },
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    )
}
