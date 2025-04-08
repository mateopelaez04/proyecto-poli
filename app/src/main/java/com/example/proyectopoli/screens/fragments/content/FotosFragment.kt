package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectopoli.R

@Composable
fun FotosFragment() {
    val fotos = listOf(
        R.drawable.cv_demo_image, // puedes agregar más imágenes aqui
        R.drawable.poli_1,
        R.drawable.poli_2
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columnas
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(fotos) { foto ->
            Image(
                painter = painterResource(id = foto),
                contentDescription = "Foto en galería",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
                )
           }
    }
}
