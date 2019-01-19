package com.chopik.andrew.mrsofttest.data.local

import com.chopik.andrew.mrsofttest.data.local.entity.News

class LocalNewsData(private val database: NewsDatabase) : LocalDataSource {

    override fun getReversedData() = database.newsDao().getReversedNews()

    override fun getData() = database.newsDao().getNews()

    override fun insertNews(news: List<News>) {
        database.newsDao().insertNews(news)
    }

    override fun findByQuery(query: String) = database.newsDao().findByQuery(query)
}