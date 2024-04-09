package com.yildirimomer.aichatbot.di

import com.yildirimomer.aichatbot.data.ChatRepositoryImpl
import com.yildirimomer.aichatbot.domain.ChatRepository
import com.yildirimomer.aichatbot.domain.GenerateChatContentUseCase
import com.yildirimomer.aichatbot.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        HomeViewModel(get())
    }
    single<ChatRepository> {  ChatRepositoryImpl() }
    single { GenerateChatContentUseCase(get()) }
}