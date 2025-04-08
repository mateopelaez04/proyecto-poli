package com.example.proyectopoli.screens.fragments.content.menu

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import com.example.proyectopoli.R

class VideosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_videos, container, false)
        return view
    }

    @Composable
    fun VideoPlayer() {
        // Usamos AndroidView para incluir el VideoView en Compose
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                // Usamos requireContext() para obtener el contexto correcto del Fragment
                val videoView = VideoView(context)
                val mediaController = MediaController(context)

                // Usamos MediaController para controlar el video
                mediaController.setAnchorView(videoView)

                // Usamos URI para apuntar al archivo en raw
                val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.cv_demo_video_15s}")
                videoView.setMediaController(mediaController)
                videoView.setVideoURI(videoUri)
                videoView.requestFocus()
                videoView.start()

                videoView // Retornamos el videoView para que se muestre
            }
        )
    }
}
