package com.jetbrains.kmm.androidApp.data.database

import com.jetbrains.kmm.androidApp.data.BookMockDataHolder
import com.jetbrains.kmm.androidApp.data.dto.BookDto

internal class BookDao {

    fun getBook(bookId: String): BookDto {
        return BookMockDataHolder.getBookings().first { it.id == bookId }
    }
}