package com.aliozdemir.cleanmovieapp.data.repository

import com.aliozdemir.cleanmovieapp.data.remote.MovieAPI
import com.aliozdemir.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.aliozdemir.cleanmovieapp.data.remote.dto.MoviesDto
import com.aliozdemir.cleanmovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieAPI) : MovieRepository {

    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId = imdbId)
    }

}