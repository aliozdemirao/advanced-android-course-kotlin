package com.aliozdemir.cryptocrazyapp.viewmodel

import androidx.lifecycle.ViewModel
import com.aliozdemir.cryptocrazyapp.model.Crypto
import com.aliozdemir.cryptocrazyapp.repository.CryptoRepository
import com.aliozdemir.cryptocrazyapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(private val repository: CryptoRepository) : ViewModel() {

    suspend fun getCrypto() : Resource<Crypto> {
        return repository.getCrypto()
    }

}