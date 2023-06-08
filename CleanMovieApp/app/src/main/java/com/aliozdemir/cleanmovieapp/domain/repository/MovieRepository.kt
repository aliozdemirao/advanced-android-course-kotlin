package com.aliozdemir.cleanmovieapp.domain.repository

import com.aliozdemir.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.aliozdemir.cleanmovieapp.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(search : String) : MoviesDto
    suspend fun getMovieDetail(imdbId : String) : MovieDetailDto

}