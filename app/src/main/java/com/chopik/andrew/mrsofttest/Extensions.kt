package com.chopik.andrew.mrsofttest

import com.chopik.andrew.mrsofttest.data.local.entity.News
import com.chopik.andrew.mrsofttest.data.remote.models.ArticlesItem
import java.util.*

fun List<ArticlesItem>.convertToNews(): List<News> {
    val list = ArrayList<News>()
    this.forEach {
        list.add(News(
            it.publishedAt ?: "",
            it.author ?: "Unknown author",
            it.title ?: "",
            it.description ?: "",
            it.urlToImage ?: ""
        ))
    }
    return list
}