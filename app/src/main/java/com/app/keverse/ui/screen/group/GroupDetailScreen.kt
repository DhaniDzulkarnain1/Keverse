package com.app.keverse.ui.screen.group

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
import com.app.keverse.model.GroupData
import com.app.keverse.ui.components.MyTopAppBar
import com.app.keverse.ui.theme.KeverseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupDetailScreen(
    groupId: Int,
    navController: NavController
) {
    val group = GroupData.groups.find { it.id == groupId }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = group?.name ?: "Group Detail",
                showBackButton = true,
                navController = navController
            )
        }
    ) { paddingValues ->
        group?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                // Image
                Image(
                    painter = painterResource(id = group.imageResId),
                    contentDescription = group.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                    contentScale = ContentScale.Crop
                )

                // Info
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = group.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Info
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            InfoRow("Generation", "Generation ${group.generation}")
                            InfoRow("Debut Year", group.debutYear)
                            InfoRow("Company", group.company)
                            InfoRow("Members", "${group.members} members")
                        }
                    }

                    // Description
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "About",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = group.description,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: Any) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupDetailScreen() {
    KeverseTheme {
        GroupDetailScreen(
            groupId = 1,
            navController = rememberNavController()
        )
    }
}