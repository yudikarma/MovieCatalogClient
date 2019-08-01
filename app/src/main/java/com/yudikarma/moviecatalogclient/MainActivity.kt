package com.yudikarma.moviecatalogclient

import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.loader.app.LoaderManager;
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), MainRecycleAdapter.OnItemClickListener {
    override fun onItemDeleteClick(v: View, movieEntity: MovieEntity) {

    }

    private lateinit var adapter : MainRecycleAdapter
    private val LOADER_CHEESES = 1
    val AUTHORITY:String = "com.yudikarma.moviecatalogsubmision2.data.local.provider"
    val URI_CHEESE: Uri = Uri.parse("content://" + AUTHORITY + "/" + MovieEntity.TABLE_NAME)

    /** The match code for some items in the Cheese table.  */
    private val CODE_CHEESE_DIR = 1

    /** The match code for an item in the Cheese table.  */
    private val CODE_CHEESE_ITEM = 2

    var MATCHER: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        MATCHER.addURI(AUTHORITY,MovieEntity.TABLE_NAME, CODE_CHEESE_DIR )
        MATCHER.addURI(AUTHORITY,MovieEntity.TABLE_NAME+"/*", CODE_CHEESE_ITEM)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycleView()
    }
    private fun setupRecycleView() {
        adapter = MainRecycleAdapter(this,this)
        recycleview_listmovie.layoutManager = LinearLayoutManager(this)
        recycleview_listmovie.adapter = adapter

        supportLoaderManager.initLoader(LOADER_CHEESES,null,mLoaderCallbacks)



    }

    private val mLoaderCallbacks = object :
       LoaderManager.LoaderCallbacks<Cursor> {
        override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
             when(id){
                LOADER_CHEESES ->  return CursorLoader(applicationContext,URI_CHEESE,null,null,null,null)

                 else -> return  throw IllegalArgumentException("Unknow URI")
            }
        }

        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
            when(loader.id){
                LOADER_CHEESES ->   adapter.setData(data)

            }
            Log.e("data"," ..."+data)

        }

        override fun onLoaderReset(loader: Loader<Cursor>) {
            when(loader.id){
                LOADER_CHEESES -> return adapter.setData(null)
            }
        }

    }


}
