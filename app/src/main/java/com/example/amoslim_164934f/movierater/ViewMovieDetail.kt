package com.example.amoslim_164934f.movierater

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.view_movie_details.*


class ViewMovieDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_movie_details)

        val movieTitle = intent.getStringExtra("movieTitle")
        val movieDescription = intent.getStringExtra("movieDescription")
        val releaseDate = intent.getStringExtra("releaseDate")
        val movieLanguage = intent.getStringExtra("movieLanguage")
        val notSuitable = intent.getStringExtra("notSuitable")
        val movieViolence = intent.getStringExtra("movieViolence")
        val movieLaugUser = intent.getStringExtra("movieLaugUser")


        val newMovie = Movie(movieTitle, movieDescription, releaseDate, movieLanguage, notSuitable, movieViolence, movieLaugUser,movieRating= null,RateMovieTxt ="")

        NameText.setText(newMovie.movieTitle)
        ovtext.setText(newMovie.movieDescription)
        langtxt.setText(newMovie.movieLanguage)
        DateText.setText(newMovie.releaseDate)
        suitabletxt.setText(newMovie.notSuitable)
        viotxt.setText(newMovie.movieViolence)
        lantxt.setText(newMovie.movieLaugUser)

        registerForContextMenu(txtMovieReview)

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
       val intent = Intent(this, LandingPage::class.java)

        intent.putExtra("movieTitle",NameText.text.toString() )
        intent.putExtra("movieDescription",  ovtext.text.toString())
        intent.putExtra("releaseDate",DateText.text.toString() )
        intent.putExtra("movieLanguage", langtxt.text.toString())
        intent.putExtra("notSuitable",suitabletxt.text.toString())
        intent.putExtra("movieViolence",viotxt.text.toString())
        intent.putExtra("movieLaugUser",lantxt.text.toString())
        intent.putExtra("movieRating", ratestar.numStars.toFloat())
        intent.putExtra("RateMovieTxt",rate_movie_txt.text.toString())
        startActivityForResult(intent,1)



    }

    // - onCreateContextMenu
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.txtMovieReview)
            menu?.add(1, 1001, 1, "Add Review")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        //note Item ID is used to identify the menu item being selected by user
        if (item?.itemId == 1001) {

            val intent = Intent(this, rateMovie::class.java)

            intent.putExtra("movieTitle",NameText.text.toString() )
            intent.putExtra("movieDescription",  ovtext.text.toString())
            intent.putExtra("releaseDate",DateText.text.toString() )
            intent.putExtra("movieLanguage", langtxt.text.toString())
            intent.putExtra("notSuitable",suitabletxt.text.toString())
            intent.putExtra("movieViolence",viotxt.text.toString())
            intent.putExtra("movieLaugUser",lantxt.text.toString())
            startActivityForResult(intent, 2)

        }
        return super.onContextItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            txtMovieReview.visibility = View.GONE
            ratestar.visibility = View.VISIBLE
            rate_movie_txt.visibility = View.VISIBLE
            // disable user input
            ratestar.setIsIndicator(true);

            //get value from rateMovie
            val rateStar = data!!.getFloatExtra("movieStar", 0.0f)
            ratestar.setRating(rateStar.toFloat())

            val ratemovieText = data!!.getStringExtra ("movieRatintxt")
            rate_movie_txt.setText(ratemovieText)
        }

        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {

            txtMovieReview.visibility = View.GONE
            ratestar.visibility = View.VISIBLE
            rate_movie_txt.visibility = View.VISIBLE
            // disable user input
            ratestar.setIsIndicator(true);

            //get value from rateMovie
            val rateStar = data!!.getFloatExtra("movieStar", 0.0f)
            ratestar.setRating(rateStar.toFloat())

            val ratemovieText = data!!.getStringExtra ("movieRatintxt")
            rate_movie_txt.setText(ratemovieText)
        }
    }
}