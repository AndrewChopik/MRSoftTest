package com.chopik.andrew.mrsofttest.data.remote

import com.chopik.andrew.mrsofttest.data.local.entity.News
import io.reactivex.Single

interface RemoteDataSource {

    fun getNews(): Single<List<News>>
}