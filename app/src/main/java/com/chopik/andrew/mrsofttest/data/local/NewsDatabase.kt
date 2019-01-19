package com.chopik.andrew.mrsofttest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chopik.andrew.mrsofttest.data.local.entity.News

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao(): NewsDao
}