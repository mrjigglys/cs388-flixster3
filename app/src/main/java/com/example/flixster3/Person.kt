package com.example.flixster3

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


    @Keep
    @Serializable
    data class Results(
        @SerialName("results")
        val results: List<Person>?
    )
    @Keep
    @Serializable
    data class Person(
        @SerialName("name")
        var name: String?,
        @SerialName("profile_path")
        var profilePath: String?,
        @SerialName("known_for")
        val knownFor: List<Movie>?
    ) : java.io.Serializable {
        var personImageUrl = "https://image.tmdb.org/t/p/w500${profilePath}"
    }

    @Keep
    @Serializable
    data class Movie(
        @SerialName("name")
        var name: String? = "",
        @SerialName("title")
        var title: String? = "",
        @SerialName("poster_path")
        var posterPath: String?,
        @SerialName("overview")
        var movieDescription: String?
    ) : java.io.Serializable {
        var movieImageUrl = "https://image.tmdb.org/t/p/w500${posterPath}"
        var movieName = "${name}${title}"
    }