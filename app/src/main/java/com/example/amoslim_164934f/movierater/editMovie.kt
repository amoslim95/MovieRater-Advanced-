package com.example.amoslim_164934f.movierater


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.content.Intent




class editMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_movie_details)

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

        val intent = Intent(this, LandingPage::class.java)
        startActivity(intent)
        finish()

    }


    }

