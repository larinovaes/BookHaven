package com.jetbrains.kmm.androidApp.presentations.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jetbrains.kmm.androidApp.domain.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository,
): ViewModel() {

    private val bookId: String = checkNotNull(savedStateHandle["bookId"])

    private val _uiState = MutableStateFlow(getUiState())
    val uiState = _uiState.asStateFlow()

    private fun getUiState(): UiState {
        val bookModel = bookRepository.getBook(bookId)
        return UiState(
            bookImage = bookModel.image,
            title = bookModel.title,
            description = bookModel.description,
            authorName = bookModel.authorName,
        )
    }

    data class UiState(
        @DrawableRes val bookImage: Int,
        val title: String = "",
        val description: String = "",
        val authorName: String = ""
    )
}