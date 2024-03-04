package com.jetbrains.kmm.androidApp.data.api

import com.jetbrains.kmm.androidApp.data.BookMockDataHolder
import com.jetbrains.kmm.androidApp.data.dto.BookDto
import kotlinx.coroutines.delay

internal class BookApi {

    suspend fun getBooks(): List<BookDto> {
        delay(1500L)
        return BookMockDataHolder.getBookings()
    }
}
