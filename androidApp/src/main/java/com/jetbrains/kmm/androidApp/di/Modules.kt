package com.jetbrains.kmm.androidApp.di

import com.jetbrains.kmm.androidApp.data.api.BookApi
import com.jetbrains.kmm.androidApp.data.database.BookDao
import com.jetbrains.kmm.androidApp.data.repository.BookRepositoryImpl
import com.jetbrains.kmm.androidApp.domain.repository.BookRepository
import com.jetbrains.kmm.androidApp.domain.usecase.GetWelcomeTextUseCase
import com.jetbrains.kmm.androidApp.presentations.viewmodel.BookDetailsViewModel
import com.jetbrains.kmm.androidApp.presentations.viewmodel.HomeViewModel
import com.jetbrains.kmm.androidApp.ui.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { BookDetailsViewModel(get(), get()) }
}

private val domainModule = module {
    factory { GetWelcomeTextUseCase() }
}

private val dataModule = module {
    factory { BookApi() }
    single { BookDao() }
    factory<BookRepository> { BookRepositoryImpl(get(), get()) }
}

val allModules = appModule + domainModule + dataModule
