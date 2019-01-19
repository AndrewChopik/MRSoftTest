package com.chopik.andrew.mrsofttest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chopik.andrew.mrsofttest.data.local.entity.News
import io.reactivex.Single

@Dao
interface NewsDao {

    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    fun getNews(): Single<List<News>>

    @Query("SELECT * FROM news ORDER BY publishedAt ASC")
    fun getReversedNews(): Single<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<News>)

    @Query("SELECT * FROM news WHERE title LIKE '%' || :query || '%'")
    fun findByQuery(query: String): Single<List<News>>
}