package com.yildirimomer.aichatbot.di

import com.yildirimomer.aichatbot.data.MainRepositoryImpl
import com.yildirimomer.aichatbot.domain.MainRepository
import com.yildirimomer.aichatbot.domain.MainUseCase
import com.yildirimomer.aichatbot.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        HomeViewModel(get())
    }
    single<MainRepository> {  MainRepositoryImpl() }
    single { MainUseCase(get()) }
}