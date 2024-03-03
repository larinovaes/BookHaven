package com.jetbrains.kmm.androidApp.di

import com.jetbrains.kmm.androidApp.ui.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
}
