package com.example.proyectopoli.navigation

import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.fillMaxSize
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.proyectopoli.screens.fragments.content.BotonesFragment
import com.example.proyectopoli.screens.fragments.content.FotosFragment
import com.example.proyectopoli.screens.fragments.content.PerfilFragment
import com.example.proyectopoli.screens.fragments.content.menu.VideosFragment
import com.example.proyectopoli.screens.fragments.content.WebFragment

@Composable
fun ContentNavigation(selectedOption: String) {
    when (selectedOption) {
        "inicio" -> PerfilFragment()
        "galeria" -> FotosFragment()
        "archivos" -> BotonesFragment()
        "web" -> WebFragment()
        "videos" -> {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    // Usamos LocalContext.current para obtener el contexto adecuado
                    val activity = context as? AppCompatActivity

                    if (activity != null) {
                        val container = FrameLayout(context).apply { id = View.generateViewId() }

                        val fragment = VideosFragment()

                        // Aseguramos que el contexto es una instancia de AppCompatActivity
                        activity.supportFragmentManager.beginTransaction()
                            .replace(container.id, fragment)
                            .commit()

                        container
                    } else {
                        throw IllegalStateException("Unable to get valid Activity context")
                    }
                }
            )
        }






        else -> PerfilFragment()
    }
}
