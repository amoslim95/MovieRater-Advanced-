package com.example.amoslim_164934f.movierater


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.widget.ListView
import android.view.View

import kotlinx.android.synthetic.main.edit_movie_details.*

import kotlinx.android.synthetic.main.movie_list_row.*
import org.intellij.lang.annotations.Language


class editMovie : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_movie_details)

        val movieTitle = intent.getStringExtra("movieTitle")
        val movieDescription = intent.getStringExtra("movieDescription")
        val releaseDate = intent.getStringExtra("releaseDate")
        val movieLanguage = intent.getStringExtra("movieLanguage")
        val notSuitable = intent.getStringExtra("notSuitable")
        val movieViolence = intent.getStringExtra("movieViolence")
        val movieLaugUser = intent.getStringExtra("movieLaugUser")

        val newMovie = Movie(movieTitle, movieDescription, releaseDate, movieLanguage, notSuitable, movieViolence, movieLaugUser,movieRating= null,RateMovieTxt ="")
        txtname.setText(newMovie.movieTitle)
        txtdesc.setText((newMovie.movieDescription))
        txtdaterelease.setText((newMovie.releaseDate))


        if (newMovie.movieLanguage == "English")
        {
            rbtneng.isChecked=true
        }
        else if (newMovie.movieLanguage == "Chinese")
        {
            rbtnchi.isChecked=true
        }
        else if (newMovie.movieLanguage == "Malay")
        {
            rbtnmalay.isChecked=true
        }
        else if (newMovie.movieLanguage == "Tamil")
        {
            rbtntamil.isChecked=true
        }




        if (newMovie.notSuitable == "No"){
            chbaudi.isChecked = true

            if(chbaudi.isChecked == true)
            {
                chklinear.visibility = View.VISIBLE
            }
            if (newMovie.movieViolence == "(Violence)"){
                chbvio.isChecked=true
            }
            if (newMovie.movieLaugUser == "(Langauge)"){
                chblang.isChecked=true
            }

        }


        chbaudi.setOnClickListener{
            if(chbaudi.isChecked)
            {
                chklinear.visibility = View.VISIBLE
            }
            else
            {
                chklinear.visibility = View.INVISIBLE
                chblang.isChecked= false
                chbvio.isChecked= false
            }
        }



        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Create new menu resource file, main.xml
        menuInflater.inflate(R.menu.editmoviemenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {

        val movieTitle = intent.getStringExtra("movieTitle")
        val movieDescription = intent.getStringExtra("movieDescription")
        val releaseDate = intent.getStringExtra("releaseDate")
        val movieLanguage = intent.getStringExtra("movieLanguage")
        val notSuitable = intent.getStringExtra("notSuitable")
        val movieViolence = intent.getStringExtra("movieViolence")
        val movieLaugUser = intent.getStringExtra("movieLaugUser")

        val newMovie = Movie(movieTitle, movieDescription, releaseDate, movieLanguage, notSuitable, movieViolence, movieLaugUser,movieRating= null,RateMovieTxt ="")
        txtname.setText(newMovie.movieTitle)
        txtdesc.setText((newMovie.movieDescription))
        txtdaterelease.setText((newMovie.releaseDate))

        var language =  movieLanguage.toString()
        var Suitable = notSuitable.toString()
        var violence = movieViolence.toString()
        var languageUse = movieLaugUser.toString()


        val intent = Intent(this, LandingPage::class.java)
        intent.putExtra("movieTitle",txtname.text.toString() )
        intent.putExtra("movieDescription",  txtdesc.text.toString())
        intent.putExtra("releaseDate",txtdaterelease.text.toString() )
        intent.putExtra("movieLanguage", language)
        intent.putExtra("notSuitable",Suitable)
        intent.putExtra("movieViolence",violence)
        intent.putExtra("movieLaugUser",languageUse)
        startActivity(intent)
        finish()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.miSave) {

            //delcare value
            var titlename = txtname.text.toString()
            var desc = txtdesc.text.toString()
            var resdate = txtdaterelease.text.toString()
            var laugauge = ""
            var audichecked = ""
            var reasonVio = ""
            var reasonLang = ""
            var NoReason = ""
            var notsuit = ""
            rbtneng.isChecked

            //vaildation if field/textbox is empty
            when {
                titlename.isEmpty() -> txtname.error = "Field empty, enter movie title"
                desc.isEmpty() -> txtdesc.error = "Field empty, Enter Description"
                resdate.isEmpty() -> txtdaterelease.error = "Field empty"
                else -> {


                    // for laugauge checkbox
                    //not suitable for audience radio button
                    when {
                        rbtneng.isChecked -> laugauge = "English"
                        rbtnchi.isChecked -> laugauge = "Chinese"
                        rbtnmalay.isChecked -> laugauge = "Malay"
                        rbtntamil.isChecked -> laugauge = "Tamil"
                    }
                    //not suitable for audience radio button
                    reasonLang = if (chblang.isChecked == true) {
                        "(Langauge)"
                    } else {
                        ""
                    }

                    reasonVio = if (chbvio.isChecked == true) {
                        "(Violence)"
                    } else {
                        ""
                    }

                    if (chbaudi.isChecked) {
                        audichecked = "true"
                    } else {
                        audichecked = "false"
                        NoReason = "null"

                    }
                    if(audichecked == true.toString())
                    {
                        notsuit = "No"
                    }
                    else
                        notsuit = "Yes"



                    val intent = Intent(this,ViewMovieDetail::class.java)
                    intent.putExtra("movieTitle",titlename )
                    intent.putExtra("movieDescription", desc)
                    intent.putExtra("releaseDate", resdate)
                    intent.putExtra("movieLanguage", laugauge )
                    intent.putExtra("notSuitable",notsuit)
                    intent.putExtra("movieViolence",reasonVio)
                    intent.putExtra("movieLaugUser",reasonLang)

                    startActivity(intent)

                }

            }

        }
        else if (item?.itemId == R.id.miCancel) {
            val movieTitle = intent.getStringExtra("movieTitle")
            val movieDescription = intent.getStringExtra("movieDescription")
            val releaseDate = intent.getStringExtra("releaseDate")
            val movieLanguage = intent.getStringExtra("movieLanguage")
            val notSuitable = intent.getStringExtra("notSuitable")
            val movieViolence = intent.getStringExtra("movieViolence")
            val movieLaugUser = intent.getStringExtra("movieLaugUser")

            val newMovie = Movie(movieTitle, movieDescription, releaseDate, movieLanguage, notSuitable, movieViolence, movieLaugUser,movieRating= null,RateMovieTxt ="")


            var movietitle = newMovie.movieTitle.toString()
            var movieDesc = newMovie.movieDescription.toString()
            var daterelease = newMovie.releaseDate.toString()
            var language =  newMovie.movieLanguage.toString()
            var Suitable = newMovie.notSuitable.toString()
            var violence = newMovie.movieViolence.toString()
            var languageUse = newMovie.movieLaugUser.toString()


            val intent = Intent(this, LandingPage::class.java)
            intent.putExtra("movieTitle",movietitle )
            intent.putExtra("movieDescription",  movieDesc)
            intent.putExtra("releaseDate",daterelease )
            intent.putExtra("movieLanguage", language)
            intent.putExtra("notSuitable",Suitable)
            intent.putExtra("movieViolence",violence)
            intent.putExtra("movieLaugUser",languageUse)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }


}




