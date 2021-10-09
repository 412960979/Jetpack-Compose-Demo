package com.wn.compose.di

import com.wn.compose.viewmodel.MatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MatchViewModel(get()) }
}