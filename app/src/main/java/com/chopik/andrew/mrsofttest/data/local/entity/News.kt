package com.chopik.andrew.mrsofttest.data.local.entity

import androidx.room.Entity

@Entity(tableName = "news", primaryKeys = ["publishedAt", "author"])
data class News(val publishedAt: String,
                val author: String,
                val title: String,
                val description: String,
                val  urlToImage: String) {
}