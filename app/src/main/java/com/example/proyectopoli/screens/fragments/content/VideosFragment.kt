package com.example.proyectopoli.screens.fragments.content

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.proyectopoli.R
import androidx.core.net.toUri

@Composable
fun VideosFragment() {
    val context = LocalContext.current
    AndroidView(factory = {
        val videoView = VideoView(it)
        val uri = "android.resource://${it.packageName}/${R.raw.poli_video}".toUri()
        videoView.setVideoURI(uri)

        val mediaController = MediaController(it)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()

        videoView
    })
}
