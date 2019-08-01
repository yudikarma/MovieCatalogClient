package com.yudikarma.moviecatalogclient

import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainRecycleAdapter(var context: Context, val listener: OnItemClickListener):
    RecyclerView.Adapter<MainRecycleAdapter.ViewHolder>() {

    private lateinit var popupMenu: PopupMenu
    private var mCursor: Cursor? = null

    fun setData(cursor: Cursor?){
        mCursor = cursor
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_movie,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        Log.d("count cursor",""+mCursor?.count)
      return if (mCursor == null) 0 else mCursor!!.getCount();
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mCursor!!.moveToPosition(position)){
            mCursor?.let {
                holder.nameMovie.text = it.getString(it.getColumnIndexOrThrow(MovieEntity.title))
                val urlImage = "https://image.tmdb.org/t/p"
                val path = it.getString(it.getColumnIndexOrThrow(MovieEntity.poster_path))
                Glide.with(context).load(urlImage+"/w500"+path).into(holder.posterMovie)
                holder.rilisMovie.text = it.getString(it.getColumnIndexOrThrow(MovieEntity.release_date))
                holder.sinopsisMovie.text = it.getString(it.getColumnIndexOrThrow(MovieEntity.overview))
                val valueRatingg = it.getDouble(it.getColumnIndexOrThrow(MovieEntity.vote_average))
                val rating = valueRatingg - 5.0
                holder.ratingMovie.rating = rating.toFloat()
                holder.icon_delete.visibility = View.VISIBLE

                Log.d("title :",""+it.getString(it.getColumnIndexOrThrow(MovieEntity.title)))
            }
            Log.d("title  luar :",""+mCursor?.getString(mCursor!!.getColumnIndexOrThrow(MovieEntity.title)))

            /*val item = datas[position]
            holder.bind(item)
            holder.icon_delete.setOnClickListener {
                showPopUpMenuDelete(it,item)
            }*/
        }

    }

    fun showPopUpMenuDelete(view: View,movieEntity: MovieEntity) {
        popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.delete_item)
        popupMenu.setOnMenuItemClickListener { item ->
            when(item?.itemId){
                R.id.action_delete -> listener.onItemDeleteClick(view,movieEntity)
            }
            true
        }
        popupMenu.show()
    }


    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var posterMovie: ImageView
        internal var nameMovie: TextView
        internal var rilisMovie: TextView
        internal var sinopsisMovie: TextView
        internal var ratingMovie: RatingBar
        internal var icon_delete: ImageView

        init {
            posterMovie = itemView.findViewById(R.id.poster_movie)
            nameMovie = itemView.findViewById(R.id.name_movie)
            rilisMovie = itemView.findViewById(R.id.rilis_movie)
            sinopsisMovie = itemView.findViewById(R.id.artist_movie)
            ratingMovie = itemView.findViewById(R.id.rating_movie)
            icon_delete = itemView.findViewById(R.id.icon_delete)


        }

        /*fun bind(item: MovieEntity) {
            nameMovie.text = "${item.title}"
            val urlImage = "https://image.tmdb.org/t/p"
            Glide.with(context).load(urlImage+"/w500"+item.poster_path).into(posterMovie)
            rilisMovie.text = "${item.release_date}"
            sinopsisMovie.text = "${item.overview}"
            val rating = item.vote_average - 5.0
            ratingMovie.rating = rating.toFloat()
            icon_delete.visibility = View.VISIBLE
        }*/
    }

    interface OnItemClickListener {
        fun onItemDeleteClick(v: View, movieEntity: MovieEntity)
    }
}