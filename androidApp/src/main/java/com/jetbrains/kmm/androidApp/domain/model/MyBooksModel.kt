package com.jetbrains.kmm.androidapp.presentations.model

import androidx.annotation.DrawableRes

data class MyBooksModel(
    @DrawableRes val image: Int,
    val title: String,
    val subTitle: String
)