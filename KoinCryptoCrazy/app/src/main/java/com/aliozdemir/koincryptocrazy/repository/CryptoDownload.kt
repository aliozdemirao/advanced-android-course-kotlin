package com.aliozdemir.koincryptocrazy.repository

import com.aliozdemir.koincryptocrazy.model.CryptoModel
import com.aliozdemir.koincryptocrazy.util.Resource

interface CryptoDownload {

    suspend fun downloadCryptos() : Resource<List<CryptoModel>>

}