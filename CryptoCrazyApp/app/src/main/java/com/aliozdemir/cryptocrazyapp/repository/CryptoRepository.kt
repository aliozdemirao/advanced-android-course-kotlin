package com.aliozdemir.cryptocrazyapp.repository

import com.aliozdemir.cryptocrazyapp.model.Crypto
import com.aliozdemir.cryptocrazyapp.model.CryptoList
import com.aliozdemir.cryptocrazyapp.service.CryptoAPI
import com.aliozdemir.cryptocrazyapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(private val api: CryptoAPI) {

    suspend fun getCryptoList() : Resource<CryptoList> {

        val response = try {
            api.getCryptoList()
        } catch (e: Exception){
            return Resource.Error("Error")
        }

        return Resource.Success(response)

    }

    suspend fun getCrypto() : Resource<Crypto> {

        val response = try {
            api.getCrypto()
        } catch (e: Exception){
            return Resource.Error("Error")
        }

        return Resource.Success(response)

    }



}