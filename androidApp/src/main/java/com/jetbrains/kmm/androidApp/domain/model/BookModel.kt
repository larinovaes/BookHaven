package com.jetbrains.kmm.androidApp.domain.model

import androidx.annotation.DrawableRes

data class BookModel(
    val id: String,
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
    val authorName: String,
)
