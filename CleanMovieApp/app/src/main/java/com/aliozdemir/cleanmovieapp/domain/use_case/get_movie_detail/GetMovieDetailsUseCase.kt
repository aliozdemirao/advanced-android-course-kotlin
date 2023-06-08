package com.aliozdemir.cleanmovieapp.domain.use_case.get_movie_detail

import com.aliozdemir.cleanmovieapp.data.remote.dto.toMovieDetail
import com.aliozdemir.cleanmovieapp.data.remote.dto.toMovieList
import com.aliozdemir.cleanmovieapp.domain.model.Movie
import com.aliozdemir.cleanmovieapp.domain.model.MovieDetail
import com.aliozdemir.cleanmovieapp.domain.repository.MovieRepository
import com.aliozdemir.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository){

    fun executeGetMovieDetails(imdbId: String) : Flow<Resource<MovieDetail>> = flow {

        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId)

            emit(Resource.Success(movieDetail.toMovieDetail()))

        } catch (e: IOError) {
            emit(Resource.Error(message = "No internet connection"))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))

        }

    }

}