package com.app.keverse.ui.screen.profil

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.keverse.R
import com.app.keverse.ui.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Profile",
                showBackButton = false,
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Profile
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Foto Profil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Name
            Text(
                text = "Dhani Dzulkarnain",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Info
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    InfoRow(
                        icon = Icons.Default.Email,
                        label = "Email",
                        value = "dhanidzulkarnain1@gmail.com"
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(0.9f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                    )

                    InfoRow(
                        icon = Icons.Default.Info,
                        label = "Perguruan Tinggi",
                        value = "Politeknik Negeri Batam"
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(0.9f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                    )

                    InfoRow(
                        icon = Icons.Default.Person,
                        label = "Jurusan",
                        value = "Teknologi Rekayasa Perangkat Lunak"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun InfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(navController = rememberNavController())
    }
}
