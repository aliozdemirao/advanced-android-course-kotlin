package com.aliozdemir.cryptocrazyapp.service

import com.aliozdemir.cryptocrazyapp.model.Crypto
import com.aliozdemir.cryptocrazyapp.model.CryptoList
import retrofit2.http.GET

interface CryptoAPI {

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json")
    suspend fun getCryptoList() : CryptoList

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/crypto.json")
    suspend fun getCrypto() : Crypto

}