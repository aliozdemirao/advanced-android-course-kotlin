package com.aliozdemir.cleanmovieapp.presentation.movies

sealed class MoviesEvent {

    data class Search(val searchString : String) : MoviesEvent()

}