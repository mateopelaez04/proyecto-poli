@file:OptIn(ExperimentalPagerApi::class)

package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.proyectopoli.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@Composable
fun FotosFragment() {
    val fotos = listOf(
        R.drawable.cv_demo_image,
        R.drawable.poli_1,
        R.drawable.poli_2,
        R.drawable.poli_3,
        R.drawable.poli_4
    )

    var imagenSeleccionada by remember { mutableStateOf<Int?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Galería de Fotos",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            fotos.forEachIndexed { index, foto ->
                Image(
                    painter = painterResource(id = foto),
                    contentDescription = "Miniatura",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { imagenSeleccionada = index },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    if (imagenSeleccionada != null) {
        Dialog(onDismissRequest = { imagenSeleccionada = null }) {
            val pagerState = rememberPagerState(initialPage = imagenSeleccionada!!)
            val scope = rememberCoroutineScope()

            LaunchedEffect(pagerState.currentPage) {
                val lastIndex = fotos.size - 1
                when (pagerState.currentPage) {
                    lastIndex + 1 -> scope.launch { pagerState.scrollToPage(0) }
                    -1 -> scope.launch { pagerState.scrollToPage(lastIndex) }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    count = fotos.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) { page ->
                    Image(
                        painter = painterResource(id = fotos[page]),
                        contentDescription = "Imagen $page",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                    )
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { imagenSeleccionada = null }) {
                    Text("Cerrar")
                }
            }
            }
        }
}