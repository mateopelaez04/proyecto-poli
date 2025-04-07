package com.example.proyectopoli.screens.fragments.content

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebSettings

@Composable
fun WebFragment() {
    val url = remember { mutableStateOf("https://www.google.com") }
    val webView = remember { mutableStateOf<WebView?>(null) }
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = url.value,
            onValueChange = { url.value = it },
            label = { Text("Ingresa una URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    webView.value?.loadUrl(url.value)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cargar Página")
            }

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.value))
                    context.startActivity(intent)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Abrir en Navegador")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.cacheMode = WebSettings.LOAD_DEFAULT
                    clearCache(true)
                    webView.value = this
                    loadUrl(url.value)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
