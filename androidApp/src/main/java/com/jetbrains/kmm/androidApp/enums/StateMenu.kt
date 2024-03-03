package com.jetbrains.kmm.androidApp.enums

import androidx.annotation.DrawableRes
import com.jetbrains.androidApp.R

enum class StateMenu(@DrawableRes val icon: Int, val title: String) {
    Download(R.drawable.ic_download, title = "Download"),
    MY_BOOKS(R.drawable.ic_books_logo, title = "My books"),
    STORE(R.drawable.ic_store, title = "Store"),
    BookShare(R.drawable.ic_pall, title = "BookShare")
}