package com.example.proyectopoli.screens.fragments.content

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.proyectopoli.R

@Composable
fun PerfilFragment() {
    val scrollState = rememberScrollState()
    var searchQuery by remember { mutableStateOf("") }
    var searchUrl by remember { mutableStateOf("https://www.google.com") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de perfil
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Datos del usuario con campos editables
        ProfileField(title = "Nombre Completo")
        ProfileField(title = "Edad")
        ProfileField(title = "Sexo")
        ProfileField(title = "Dirección de residencia")
        ProfileField(title = "Acerca de mí", isLarge = true)

        Spacer(modifier = Modifier.height(16.dp))

        // Secciones
        ProfileField(title = "Experiencias laborales", isLarge = true)
        ProfileField(title = "Estudios", isLarge = true)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para realizar una búsqueda en Google
        Text(
            text = "Buscador de Google",
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
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                searchUrl = "https://www.google.com/search?q=${searchQuery.replace(" ", "+")}"
            }) {
                Text("Buscar")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // WebView para mostrar la búsqueda en Google
        WebViewContainer(url = searchUrl)
    }
}

@Composable
fun ProfileField(title: String, isLarge: Boolean = false) {
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isLarge) 100.dp else 40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun WebViewContainer(url: String) {
    var webView: WebView? = null

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                        return false
                    }
                }
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadsImagesAutomatically = true
                settings.useWideViewPort = true
                settings.allowContentAccess = true
                settings.allowFileAccess = true
                webView = this
                loadUrl(url) // se carga inicialmente
            }
        },
        update = { view ->
            view.loadUrl(url) // se recarga cuando cambia la URL
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
