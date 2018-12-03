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


class editMovie : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_movie_details)


        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        val movieTitle = intent.getStringExtra("movieTitle")
        val movieDescription = intent.getStringExtra("movieDescription")
        val releaseDate = intent.getStringExtra("releaseDate")
        val movieLanguage = intent.getStringExtra("movieLanguage")
        val notSuitable = intent.getStringExtra("notSuitable")
        val movieViolence = intent.getStringExtra("movieViolence")
        val movieLaugUser = intent.getStringExtra("movieLaugUser")

        txtname.setText(movieTitle)
        txtdesc.setText((movieDescription))
        txtdaterelease.setText((releaseDate))

        if (movieLanguage == "English")
        {
            rbtneng.isChecked=true
        }
        else if (movieLanguage == "Chinese")
        {
            rbtnchi.isChecked=true
        }
        else if (movieLanguage == "Malay")
        {
            rbtnmalay.isChecked=true
        }
        else if (movieLanguage == "Tamil")
        {
            rbtntamil.isChecked=true
        }

        if (notSuitable == "No"){
            chbaudi.isChecked = true

                if(chbaudi.isChecked == true)
                {
                    chklinear.visibility = View.VISIBLE
                }
            if (movieViolence == "(Violence)"){
                chbvio.isChecked=true
            }
            if (movieLaugUser == "(Langauge)"){
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

        val intent = Intent(this, LandingPage::class.java)
        startActivity(intent)
        finish()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.miSave) {

        }
        else if (item?.itemId == R.id.miCancel) {
            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }


}




