package com.yudikarma.moviecatalogclient

import android.content.ContentValues
import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = MovieEntity.TABLE_NAME)
data class MovieEntity(@ColumnInfo(name = "overview") var overview:String = "",
                       @ColumnInfo(name = "original_language") var original_language:String = "",
                       @ColumnInfo(name = "title") var title:String = "",
                       @ColumnInfo(name = "poster_path") var poster_path:String ="",
                       @ColumnInfo(name = "backdrop_path") var backdrop_path:String="",
                       @ColumnInfo(name = "release_date") var release_date:String="",
                       @ColumnInfo(name = "vote_average") var vote_average:Double=0.0,
                       @ColumnInfo(name = "popularity") var popularity:Double=0.0,
                       @ColumnInfo(name = "vote_count") var vote_count:Int = 0,
                       @ColumnInfo(name = "getCountFavorite")var isFavorite:Boolean = false,
                       @PrimaryKey @ColumnInfo(name = "id") var id:Long = 0):Parcelable {

    companion object {
        const val TABLE_NAME = "MOVIE"
        const val AUTH = "com.yudikarma.moviecatalogsubmision2"
        const val id = "id"
        const val isFavorite = "getCountFavorite"
        const val vote_count = "vote_count"
        const val popularity = "popularity"
        const val vote_average = "vote_average"
        const val release_date = "release_date"
        const val backdrop_path = "backdrop_path"
        const val poster_path = "poster_path"
        const val title = "title"
        const val original_language = "original_language"
        const val overview = "overview"

        val uri = Uri.Builder().scheme("content")
            .authority(AUTH)
            .appendPath("MOVIE")
            .build() as Uri

        /*fun getKolomString(cursor: Cursor,column_name:String):String{
            return cursor.getString(cursor.getColumnIndex(column_name))
        }

        fun getKolomInt(cursor: Cursor,column_name:String):Int{
            return  cursor.getInt(cursor.getColumnIndex(column_name))
        }*/

        fun fromContentValues(values: ContentValues): MovieEntity {
            var movieEntity:MovieEntity = MovieEntity()

            if (values.containsKey(id)) {
                movieEntity.id = values.getAsLong(id)
            }
            if (values.containsKey(isFavorite)) {
                movieEntity.isFavorite = values.getAsBoolean(isFavorite)
            }
            if (values.containsKey(vote_count)) {
                movieEntity.vote_count = values.getAsInteger(vote_count)
            }
            if (values.containsKey(popularity)) {
                movieEntity.popularity = values.getAsDouble(popularity)
            }
            if (values.containsKey(vote_average)) {
                movieEntity.vote_average = values.getAsDouble(vote_average)
            }
            if (values.containsKey(release_date)) {
                movieEntity.release_date = values.getAsString(release_date)
            }
            if (values.containsKey(backdrop_path)) {
                movieEntity.backdrop_path = values.getAsString(backdrop_path)
            }
            if (values.containsKey(poster_path)) {
                movieEntity.poster_path = values.getAsString(poster_path)
            }
            if (values.containsKey(title)) {
                movieEntity.title = values.getAsString(title)
            }
            if (values.containsKey(original_language)) {
                movieEntity.original_language = values.getAsString(original_language)
            }
            if (values.containsKey(overview)) {
                movieEntity.overview = values.getAsString(overview)
            }

            return movieEntity
        }
    }


}