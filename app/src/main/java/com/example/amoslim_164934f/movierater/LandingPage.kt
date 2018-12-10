package com.example.amoslim_164934f.movierater

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import android.widget.AdapterView.AdapterContextMenuInfo



class LandingPage : AppCompatActivity() {

    lateinit var listView: ListView
    var selectedMovie: Movie = Movie("","","" ,"", "", "", "",0.0f,"")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)

        listView = this.findViewById(R.id.lvMovieList)
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
         //   intent.putExtra("movieRating",MovieListItem()[i].movieRating)
          //  intent.putExtra("RateMovieTxt", MovieListItem()[i].RateMovieTxt)

           /*Toast.makeText(applicationContext, "Title = ${ MovieListItem()[i].movieTitle}"
                   + "\n" + "star rate${MovieListItem()[i].movieRating}"
                   + "\n" + "rating text${MovieListItem()[i].RateMovieTxt}"

                   , Toast.LENGTH_LONG).show()
           setResult(Activity.RESULT_OK, intent)*/
            finish()
        }

    }


    fun MovieListItem():ArrayList<Movie> {



            val movieTitle = intent.getStringExtra("movieTitle")
            val movieDescription = intent.getStringExtra("movieDescription")
            val releaseDate = intent.getStringExtra("releaseDate")
            val movieLanguage = intent.getStringExtra("movieLanguage")
            val notSuitable = intent.getStringExtra("notSuitable")
            val movieViolence = intent.getStringExtra("movieViolence")
            val movieLaugUser = intent.getStringExtra("movieLaugUser")
            val movieRating = intent.getFloatExtra("movieRating",0.0f)
            val RateMovieTxt = intent.getStringExtra("RateMovieTxt")


            val result = ArrayList<Movie>()

            //val newMovie = Movie(movieTitle, movieDescription, releaseDate, movieLanguage, notSuitable, movieViolence, movieLaugUser)

            for (i in 0..0) {
                val newMovie = Movie(movieTitle, movieDescription, releaseDate, movieLanguage, notSuitable, movieViolence, movieLaugUser,movieRating,RateMovieTxt)

                result.add(newMovie)
            }
        /*var item1= Movie("Venom","When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego “Venom” to save his life.",
                "03-10-2018","English ","No","(Violence)","(Language)",0.0f,"Bad")

        var MovieListrArray = arrayListOf(item1)
        return MovieListrArray*/
        Toast.makeText(applicationContext, "Title = ${movieTitle}"
                + "\n" + "star rate${movieRating}"
                + "\n" + "rating text${RateMovieTxt}"
                + "\n\n\n" + "listview"

                , Toast.LENGTH_LONG).show()
        return result

        }


    class MovieListAdapter (private var activity: Activity, private var items: ArrayList<Movie>):BaseAdapter() {
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
            viewHolder.tvName?.text = SMovie.movieTitle
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
    //listView.setEmptyView(findViewById(R.id.lvMovieList));

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
//            intent.putExtra("movieRating", this.selectedMovie.movieRating)
  //          intent.putExtra("RateMovieTxt", this.selectedMovie.RateMovieTxt)
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