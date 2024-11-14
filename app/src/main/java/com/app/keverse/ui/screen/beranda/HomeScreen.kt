package com.app.keverse.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.keverse.model.Album
import com.app.keverse.model.SampleData
import com.app.keverse.ui.theme.KeverseTheme
import com.app.keverse.R
import com.app.keverse.ui.components.MyTopAppBar

data class TopAlbumData(
    val title: String,
    val artist: String,
    val streams: Float
)

val TopAlbums = listOf(
    TopAlbumData("BORN PINK", "BLACKPINK", 89.5f),
    TopAlbumData("Proof", "BTS", 85.2f),
    TopAlbumData("FML", "SEVENTEEN", 78.4f),
    TopAlbumData("UNFORGIVEN", "LE SSERAFIM", 72.1f),
    TopAlbumData("KILL THIS LOVE", "BLACKPINK", 68.7f)
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "KEVERSE",
                showBackButton = false,
                navController = navController
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Chart
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(220.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Top 5 Album Streams",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(TopAlbums) { album ->
                                Card(
                                    modifier = Modifier.width(120.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier.size(80.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(
                                                progress = album.streams / 100f,
                                                modifier = Modifier.size(80.dp),
                                                strokeWidth = 8.dp,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                            Text(
                                                text = "${album.streams}M",
                                                style = MaterialTheme.typography.labelLarge,
                                                color = MaterialTheme.colorScheme.primary,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = album.title,
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Text(
                                            text = album.artist,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Comebacks
            item {
                Text(
                    text = "New K-pop Album Comeback",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // Comebacks
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(SampleData.recentComebacks) { album ->
                        AlbumItem(album = album) {
                            navController.navigate("album_detail/${album.id}/recent")
                        }
                    }
                }
            }


            stickyHeader {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Upcoming K-pop Album Comeback",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }


            items(SampleData.upcomingComebacks) { album ->
                UpcomingAlbumItem(
                    album = album,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    navController.navigate("album_detail/${album.id}/upcoming")
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun AlbumItem(
    album: Album,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .height(240.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = album.imageResId),
                contentDescription = album.title,
                modifier = Modifier
                    .size(136.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = album.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = album.artist,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun UpcomingAlbumItem(
    album: Album,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = album.imageResId),
                contentDescription = album.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = album.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = album.artist,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Rilis: ${album.releaseDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    KeverseTheme {
        HomeScreen(navController = rememberNavController())
    }
}