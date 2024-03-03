package com.jetbrains.kmm.androidApp.ui.model

import androidx.annotation.DrawableRes

data class BooksModel(
    @DrawableRes val image: Int,
    val title: String
)
