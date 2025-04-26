package com.example.proyectopoli.screens.fragments.content.menu

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.proyectopoli.R

@Composable
fun VideosFragment() {
    val context = LocalContext.current

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val videoView = VideoView(context)
            val mediaController = MediaController(context)
            mediaController.setAnchorView(videoView)

            val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.cv_demo_video_15s}")
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(videoUri)
            videoView.requestFocus()
            videoView.start()

            videoView
        }
    )
}

