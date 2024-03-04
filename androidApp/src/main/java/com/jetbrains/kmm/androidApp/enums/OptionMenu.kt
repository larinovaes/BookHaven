package com.jetbrains.kmm.androidApp.enums

import androidx.annotation.DrawableRes
import com.jetbrains.androidApp.R

enum class OptionMenu(@DrawableRes val icon: Int, val title: String) {
    DOWNLOAD(R.drawable.ic_download, title = "Download"),
    MY_BOOKS(R.drawable.ic_books_logo, title = "My books"),
    STORE(R.drawable.ic_store, title = "Store"),
    BOOK_SHARE(R.drawable.ic_pall, title = "BookShare")
}