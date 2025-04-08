package com.example.proyectopoli.navigation

import androidx.compose.runtime.Composable
import com.example.proyectopoli.screens.fragments.content.BotonesFragment
import com.example.proyectopoli.screens.fragments.content.FotosFragment
import com.example.proyectopoli.screens.fragments.content.PerfilFragment
import com.example.proyectopoli.screens.fragments.content.WebFragment
import com.example.proyectopoli.screens.fragments.content.menu.VideosFragment // <- Esto está bien aunque sea un Composable

@Composable
fun ContentNavigation(selectedOption: String) {
    when (selectedOption) {
        "inicio" -> PerfilFragment()
        "galeria" -> FotosFragment()
        "archivos" -> BotonesFragment()
        "web" -> WebFragment()
        "videos" -> VideosFragment() // <- Lo usamos como Composable, no como Fragment
        else -> PerfilFragment()
    }
}
