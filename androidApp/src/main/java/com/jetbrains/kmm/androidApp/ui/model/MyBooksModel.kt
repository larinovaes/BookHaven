package com.jetbrains.kmm.androidApp.ui.model

import androidx.annotation.DrawableRes

data class MyBooksModel(
    @DrawableRes val image: Int,
    val title: String,
    val subTitle: String
)