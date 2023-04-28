package com.aliozdemir.koincryptocrazy.di

import com.aliozdemir.koincryptocrazy.repository.CryptoDownload
import com.aliozdemir.koincryptocrazy.repository.CryptoDownloadImpl
import com.aliozdemir.koincryptocrazy.service.CryptoAPI
import com.aliozdemir.koincryptocrazy.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // singleton scope
    single {
        val BASE_URL = "https://raw.githubusercontent.com/"

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)
    }


    single<CryptoDownload> {
        CryptoDownloadImpl(get())
    }


    viewModel{
        CryptoViewModel(get())
    }


    // factory -> everytime we inject -> new instance
    factory{

    }

}