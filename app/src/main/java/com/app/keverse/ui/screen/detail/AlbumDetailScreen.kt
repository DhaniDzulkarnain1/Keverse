package com.app.keverse.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.keverse.model.SampleData
import com.app.keverse.model.Album
import com.app.keverse.ui.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    albumId: Int,
    source: String,
    navController: NavController
) {
    val album = when (source) {
        "recent" -> SampleData.recentComebacks.firstOrNull { it.id == albumId }
        "upcoming" -> SampleData.upcomingComebacks.firstOrNull { it.id == albumId }
        else -> null
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = album?.title ?: "Detail Album",
                showBackButton = true,
                navController = navController
            )
        }
    ) { paddingValues ->
        album?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                // Image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = album.imageResId),
                        contentDescription = album.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                // Info
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    // Title
                    Text(
                        text = album.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Artist
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = album.artist,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    // Release Date & Genre
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Release Date",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = album.releaseDate,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Genre",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = album.genre,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    // Description
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Description",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = album.summary,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlbumDetailScreen() {
    AlbumDetailScreen(
        albumId = 1,
        source = "recent",
        navController = rememberNavController()
    )
}
