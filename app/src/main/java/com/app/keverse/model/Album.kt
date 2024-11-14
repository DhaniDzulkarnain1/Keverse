package com.app.keverse.model

import androidx.annotation.DrawableRes

data class Album(
    val id: Int,
    val title: String,
    val artist: String,
    val releaseDate: String,
    val genre: String,
    val summary: String,
    @DrawableRes val imageResId: Int
)
