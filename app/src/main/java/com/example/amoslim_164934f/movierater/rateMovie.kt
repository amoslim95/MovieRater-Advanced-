package com.example.amoslim_164934f.movierater

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.rate_movie.*


class rateMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rate_movie)




        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    //create back button in action bar
    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Create new menu resource file, main.xml
        menuInflater.inflate(R.menu.movieratemenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.miSubmit)
        {


            val movieTitle = intent.getStringExtra("movieTitle")
            val movieDescription = intent.getStringExtra("movieDescription")
            val releaseDate = intent.getStringExtra("releaseDate")
            val movieLanguage = intent.getStringExtra("movieLanguage")
            val notSuitable = intent.getStringExtra("notSuitable")
            val movieViolence = intent.getStringExtra("movieViolence")
            val movieLaugUser = intent.getStringExtra("movieLaugUser")

            val newMovie = Movie(movieTitle, movieDescription, releaseDate, movieLanguage, notSuitable, movieViolence, movieLaugUser)

            val miMovietitle = newMovie.movieTitle
            val miDec = newMovie.movieDescription
            val miLang = newMovie.movieLanguage
            val miDaterelease = newMovie.releaseDate
            val miSuitable = newMovie.notSuitable
            val miSuitVio = newMovie.movieViolence
            val miSuitLang = newMovie.movieLaugUser

            val miRateStar = ratestar.getRating()
            val miRateMovie = txtdesc.text.toString()

            val intent = Intent(this@rateMovie,ViewMovieDetail::class.java)
            intent.putExtra("movieTitle", miMovietitle)
            intent.putExtra("movieDescription", miDec)
            intent.putExtra("releaseDate", miDaterelease)
            intent.putExtra("movieLanguage", miLang )
            intent.putExtra("notSuitable", miSuitable)
            intent.putExtra( "movieViolence",miSuitVio )
            intent.putExtra( "movieLaugUser",miSuitLang )


            intent.putExtra("movieStar",miRateStar)
            intent.putExtra("movieRatintxt", miRateMovie)
            setResult(Activity.RESULT_OK, intent)

            //intent.putExtra("movieRateTxt",RateMovieTxt )
            Toast.makeText(applicationContext, "Rate review= ${miRateMovie}"
                    + "\n" + "Star Rate =${miRateStar}"
                    , Toast.LENGTH_SHORT).show()
            finish()

            val actionbar = supportActionBar
            actionbar!!.setDisplayHomeAsUpEnabled(true)
        }
        return super.onOptionsItemSelected(item)
    }

}