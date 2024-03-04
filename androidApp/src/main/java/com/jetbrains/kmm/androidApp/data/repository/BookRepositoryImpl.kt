package com.jetbrains.kmm.androidApp.data.repository

import com.jetbrains.kmm.androidApp.data.api.BookApi
import com.jetbrains.kmm.androidApp.data.database.BookDao
import com.jetbrains.kmm.androidApp.data.mapper.toBookModel
import com.jetbrains.kmm.androidApp.domain.model.BookModel
import com.jetbrains.kmm.androidApp.domain.repository.BookRepository

internal class BookRepositoryImpl(
    private val bookApi: BookApi,
    private val bookDao: BookDao,
): BookRepository {

    override suspend fun getBooks(): List<BookModel> {
        return bookApi.getBooks().map { it.toBookModel() }
    }

    override fun getBook(bookId: String): BookModel {
        return bookDao.getBook(bookId).toBookModel()
    }
}
