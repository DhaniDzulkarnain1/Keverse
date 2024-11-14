package com.app.keverse.model

data class KpopGroup(
    val id: Int,
    val name: String,
    val generation: Int,
    val debutYear: String,
    val company: String,
    val members: Int,
    val description: String,
    val imageResId: Int
)