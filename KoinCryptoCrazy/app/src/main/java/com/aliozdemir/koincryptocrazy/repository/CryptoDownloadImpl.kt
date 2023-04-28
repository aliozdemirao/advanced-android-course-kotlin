package com.aliozdemir.koincryptocrazy.repository

import com.aliozdemir.koincryptocrazy.model.CryptoModel
import com.aliozdemir.koincryptocrazy.service.CryptoAPI
import com.aliozdemir.koincryptocrazy.util.Resource

class CryptoDownloadImpl(private val api: CryptoAPI) : CryptoDownload {

    override suspend fun downloadCryptos(): Resource<List<CryptoModel>> {
        return try {
            val response = api.getData()

            if(response.isSuccessful){

                response.body()?.let{
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)

            } else {
                Resource.error("Error", null)
            }

        } catch (e: Exception){
            Resource.error("No data!", null)
        }



    }

}