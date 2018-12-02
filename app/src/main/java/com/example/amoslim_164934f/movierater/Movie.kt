package com.example.amoslim_164934f.movierater

/**
 *  Created by Amos Lim 164934F
 */

// delcare entity class to store object or values
class Movie (movieTitle : String, movieDescription: String,releaseDate: String, movieLanguage: String,notSuitable: String, movieViolence: String?, movieLaugUser: String?)
{

    var movieTitle : String
    var movieDescription :String
    var releaseDate: String
    var movieLanguage: String
    var notSuitable: String
    var movieViolence: String?
    var movieLaugUser: String?
    //var movieRating: Float
    //var RateMovieTxt:String?

    init {
        this.movieTitle = movieTitle
        this.movieDescription = movieDescription
        this.releaseDate = releaseDate
        this.movieLanguage = movieLanguage
        this.notSuitable = notSuitable
        this.movieViolence= movieViolence
        this.movieLaugUser = movieLaugUser
        //this.movieRating = movieRating
        //this.RateMovieTxt = RateMovieTxt
    }


}
