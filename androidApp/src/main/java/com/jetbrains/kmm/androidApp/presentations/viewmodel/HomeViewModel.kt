package com.jetbrains.kmm.androidApp.presentations.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.androidApp.R
import com.jetbrains.kmm.androidApp.domain.usecase.GetWelcomeTextUseCase
import com.jetbrains.kmm.androidApp.domain.model.BookModel
import com.jetbrains.kmm.androidApp.domain.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWelcomeTextUseCase: GetWelcomeTextUseCase,
    private val bookRepository: BookRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(getInitialUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchBooks()
    }

    private fun fetchBooks() = viewModelScope.launch {
        _uiState.update { it.copy(isAvailableBooksLoading = true) }
        val bookModeList = bookRepository.getBooks()
        _uiState.update { it.copy(
            isAvailableBooksLoading = false,
            bookModelList = bookModeList,
        ) }
    }

    private fun getInitialUiState(): UiState {
        return UiState(
            welcomeText = getWelcomeTextUseCase.execute()
        )
    }

    data class UiState(
        @DrawableRes val profileImage: Int = R.drawable.heedo,
        val welcomeText: String = "",
        val isAvailableBooksLoading: Boolean = true,
        val bookModelList: List<BookModel> = emptyList(),
    )
}
