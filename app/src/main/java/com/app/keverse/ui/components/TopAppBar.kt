package com.app.keverse.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    showBackButton: Boolean = false,
    navController: NavController? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            if (showBackButton && navController != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MyTopAppBarPreview() {
    val navController = rememberNavController()
    MyTopAppBar(
        title = "Preview Title",
        showBackButton = true,
        navController = navController
    )
}