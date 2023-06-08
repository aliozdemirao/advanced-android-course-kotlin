package com.aliozdemir.cleanmovieapp.data.remote

import com.aliozdemir.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.aliozdemir.cleanmovieapp.data.remote.dto.MoviesDto
import com.aliozdemir.cleanmovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //https://www.omdbapi.com/?apikey=5e528520&s=batman
    //https://www.omdbapi.com/?apikey=5e528520&i=tt3896198

    @GET(".")
    suspend fun getMovies(
        @Query("apikey") apiKey : String = API_KEY,
        @Query("s") searchString : String
    ) : MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("apikey") apiKey : String = API_KEY,
        @Query("i") imdbId : String
    ) : MovieDetailDto

}