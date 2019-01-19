package com.chopik.andrew.mrsofttest.data.remote

import com.chopik.andrew.mrsofttest.convertToNews
import com.chopik.andrew.mrsofttest.data.local.entity.News
import io.reactivex.Single

class RemoteNewsData(private val newsApi: NewsApi) : RemoteDataSource {

    override fun getNews(): Single<List<News>> = newsApi.requestNews().map {
        it.articles?.convertToNews()
    }
}