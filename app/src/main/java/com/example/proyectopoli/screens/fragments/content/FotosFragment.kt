package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.proyectopoli.R

@Composable
fun FotosFragment() {
    val fotos = listOf(
        R.drawable.cv_demo_image,
        R.drawable.poli_1,
        R.drawable.poli_2
    )

    var imagenSeleccionada by remember { mutableStateOf<Int?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
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
                    .height(150.dp)
                    .clickable {
                        imagenSeleccionada = foto
                    },
                contentScale = ContentScale.Crop
            )
        }
    }

    // Diálogo con imagen ampliada y botón cerrar
    if (imagenSeleccionada != null) {
        Dialog(onDismissRequest = { imagenSeleccionada = null }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imagenSeleccionada!!),
                    contentDescription = "Imagen ampliada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { imagenSeleccionada = null }) {
                    Text(text = "Cerrar")
                }
            }
            }
        }
}