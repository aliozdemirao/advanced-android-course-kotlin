package com.aliozdemir.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.aliozdemir.artbooktesting.model.ImageResponse
import com.aliozdemir.artbooktesting.roomdb.Art
import com.aliozdemir.artbooktesting.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>

}