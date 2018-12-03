package com.example.amoslim_164934f.movierater

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.landing_page.*
import android.widget.AdapterView.AdapterContextMenuInfo


class LandingPage : AppCompatActivity() {

    lateinit var listView: ListView
    var selectedMovie: Movie = Movie("","","" ,"", "", "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)

        listView = findViewById(R.id.lvMovieList)
        var adapter = MovieListAdapter(this, MovieListItem())
        listView.adapter=adapter

        adapter.notifyDataSetChanged()

        registerForContextMenu(listView)



        listView.setOnItemClickListener { adapterView, view,i, l->

            val intent = Intent(this@LandingPage, ViewMovieDetail::class.java)
            intent.putExtra("movieTitle", MovieListItem()[i].movieTitle)
            intent.putExtra("movieDescription", MovieListItem()[i].movieDescription)
            intent.putExtra("movieLanguage", MovieListItem()[i].movieLanguage)
            intent.putExtra("releaseDate", MovieListItem()[i].releaseDate)
            intent.putExtra("notSuitable", MovieListItem()[i].notSuitable)
            intent.putExtra("movieViolence", MovieListItem()[i].movieViolence)
            intent.putExtra("movieLaugUser", MovieListItem()[i].movieLaugUser)

            startActivity(intent)
            finish()


        }

    }
    fun MovieListItem():Array<Movie>{
        var item1= Movie("Avengers", "Movie about people hitting each other", "2010","English","Yes","","")
        var item2= Movie("Venom", "the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.", "2018","Chinese","No","(Violence)","(Langauge)")

        var MovieListArray = arrayOf(item1,item2)

        return MovieListArray
    }


    class MovieListAdapter (private var activity: Activity,private var items: Array<Movie>):BaseAdapter() {
        private class ViewHolder(row: View?) {
            var tvImage: ImageView? = null
            var tvName: TextView? = null

            init {
                this.tvImage = tvImage
                this.tvName = row?.findViewById(R.id.txtMovieName)
            }
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View?
            val viewHolder: ViewHolder
            if(convertView == null){
                val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(R.layout.movie_list_row, null)
                viewHolder = ViewHolder(view)
                view?.tag = viewHolder
            }else{
                view = convertView
                viewHolder = view.tag as ViewHolder
            }
            var SMovie = items[position]
            viewHolder.tvName?.text = SMovie.movieTitle.toString()
            return  view as View
        }


        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getItem(p0: Int): Any {
            return items[p0]
        }

        override fun getCount(): Int {
            return items.size
        }

    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.lvMovieList){
            val lv = v as ListView
            val acmi = menuInfo as AdapterContextMenuInfo
            val obj = lv.getItemAtPosition(acmi.position) as Movie
            this.selectedMovie = obj
            menu?.add(1, 1001, 1, "Edit")
        }



    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        //note Item ID is used to identify the menu item being selected by user
        if(item?.itemId ==1001){

            val intent = Intent(this,editMovie::class.java)
            intent.putExtra("movieTitle", this.selectedMovie.movieTitle)
            intent.putExtra("movieDescription", this.selectedMovie.movieDescription)
            intent.putExtra("releaseDate", this.selectedMovie.releaseDate)
            intent.putExtra("movieLanguage", this.selectedMovie.movieLanguage )
            intent.putExtra("notSuitable",this.selectedMovie.notSuitable)
            intent.putExtra("movieViolence",this.selectedMovie.movieViolence)
            intent.putExtra("movieLaugUser",this.selectedMovie.movieLaugUser)
            startActivity(intent)

        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Create new menu resource file, main.xml
        menuInflater.inflate(R.menu.landingpagemenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.miAdd) {
            val intent = Intent(this,AddMovie::class.java)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }





}