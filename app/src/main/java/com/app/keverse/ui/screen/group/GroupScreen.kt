package com.app.keverse.ui.screen.group

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.keverse.model.GroupData
import com.app.keverse.model.KpopGroup
import com.app.keverse.ui.components.MyTopAppBar
import com.app.keverse.ui.theme.KeverseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreen(navController: NavController) {
    var selectedGeneration by remember { mutableStateOf<Int?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "K-Pop Groups",
                showBackButton = false,
                navController = navController
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
            ) {
                // Search
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    placeholder = { Text("Search for a group...") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Filter
                TabRow(
                    selectedTabIndex = selectedGeneration?.minus(1) ?: 0,
                    modifier = Modifier.padding(vertical = 8.dp),
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    listOf("All", "Gen 1", "Gen 2", "Gen 3", "Gen 4", "Gen 5").forEachIndexed { index, label ->
                        Tab(
                            selected = when(index) {
                                0 -> selectedGeneration == null
                                else -> selectedGeneration == index
                            },
                            onClick = {
                                selectedGeneration = if (index == 0) null else index
                            },
                            text = {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = if (
                                        when(index) {
                                            0 -> selectedGeneration == null
                                            else -> selectedGeneration == index
                                        }
                                    ) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        )
                    }
                }


                val filteredGroups = GroupData.groups.filter { group ->
                    val matchesGeneration = selectedGeneration == null || group.generation == selectedGeneration
                    val matchesSearch = searchQuery.isEmpty() || listOf(
                        group.name,
                        group.company,
                        group.debutYear.toString(),
                        "Generation ${group.generation}",
                        "Gen ${group.generation}"
                    ).any { it.contains(searchQuery, ignoreCase = true) }
                    matchesGeneration && matchesSearch
                }


                if (filteredGroups.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "No groups found",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Try adjusting your search or filters",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(filteredGroups) { group ->
                            GroupItem(
                                group = group,
                                onClick = {
                                    navController.navigate("group_detail/${group.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GroupItem(
    group: KpopGroup,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Image(
                    painter = painterResource(id = group.imageResId),
                    contentDescription = group.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Gen ${group.generation}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = group.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = group.company,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Debut: ${group.debutYear}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupScreen() {
    KeverseTheme {
        GroupScreen(rememberNavController())
    }
}
