package com.chopik.andrew.mrsofttest.data

import com.chopik.andrew.mrsofttest.data.local.entity.News
import io.reactivex.Single

interface AppDataSource {

    fun getDataFromDB(): Single<List<News>>

    fun insertData(news: List<News>)

    fun refreshData(): Single<List<News>>

    fun getReversedFromDB(): Single<List<News>>

    fun findByQuery(query: String): Single<List<News>>
}