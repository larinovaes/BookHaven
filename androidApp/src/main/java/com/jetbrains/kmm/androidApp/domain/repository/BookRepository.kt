package com.jetbrains.kmm.androidApp.domain.repository

import com.jetbrains.kmm.androidApp.domain.model.BookModel

interface BookRepository {
    suspend fun getBooks(): List<BookModel>

    fun getBook(bookId: String): BookModel
}
