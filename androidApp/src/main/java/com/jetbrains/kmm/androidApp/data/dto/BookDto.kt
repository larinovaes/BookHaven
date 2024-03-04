package com.jetbrains.kmm.androidApp.data.dto

import androidx.annotation.DrawableRes

internal data class BookDto(
    val id: String,
    // todo: Quando retirar o mock, passar como imageUrl: String
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
    val authorName: String,
)
