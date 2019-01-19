package com.chopik.andrew.mrsofttest.data

import com.chopik.andrew.mrsofttest.data.local.LocalDataSource
import com.chopik.andrew.mrsofttest.data.local.entity.News
import com.chopik.andrew.mrsofttest.data.remote.RemoteDataSource

class Repository(private val local: LocalDataSource, private val remote: RemoteDataSource) : AppDataSource {

    override fun getDataFromDB() = local.getData()

    override fun refreshData() = remote.getNews()

    override fun insertData(news: List<News>) {
        local.insertNews(news)
    }

    override fun getReversedFromDB() = local.getReversedData()

    override fun findByQuery(query: String) = local.findByQuery(query)
}