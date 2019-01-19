package com.chopik.andrew.mrsofttest.data.local

import com.chopik.andrew.mrsofttest.data.local.entity.News
import io.reactivex.Single

interface LocalDataSource {

    fun getData(): Single<List<News>>

    fun insertNews(news: List<News>)

    fun getReversedData(): Single<List<News>>

    fun findByQuery(query: String): Single<List<News>>
}