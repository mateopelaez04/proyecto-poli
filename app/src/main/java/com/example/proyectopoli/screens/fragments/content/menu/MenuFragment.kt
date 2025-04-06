package com.example.proyectopoli.screens.fragments.content.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectopoli.model.MenuItem
import com.example.proyectopoli.ui.theme.components.DrawerItem

@Composable
fun MenuFragment(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val menuItems = listOf(
        MenuItem(id = "inicio", title = "Inicio", icon = Icons.Default.AccountCircle),
        MenuItem(id = "galeria", title = "Galeria", icon = Icons.Default.Image),
        MenuItem(id = "archivos", title = "Archivos", icon = Icons.Default.PictureAsPdf),
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "MiCurriculumYa!",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )

        Divider()

        LazyColumn {
            items(menuItems) { item ->
                DrawerItem(
                    item = item,
                    selected = selectedOption == item.id,
                    onItemClick = { onOptionSelected(item.id) }
                )
            }
        }
    }

}