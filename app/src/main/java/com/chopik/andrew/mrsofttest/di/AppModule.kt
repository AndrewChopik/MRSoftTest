package com.chopik.andrew.mrsofttest.di

import android.content.Context
import androidx.room.Room
import com.chopik.andrew.mrsofttest.DATABASE_NAME
import com.chopik.andrew.mrsofttest.URL_API
import com.chopik.andrew.mrsofttest.data.AppDataSource
import com.chopik.andrew.mrsofttest.data.Repository
import com.chopik.andrew.mrsofttest.data.local.LocalDataSource
import com.chopik.andrew.mrsofttest.data.local.LocalNewsData
import com.chopik.andrew.mrsofttest.data.local.NewsDatabase
import com.chopik.andrew.mrsofttest.data.remote.ApiKeyInterceptor
import com.chopik.andrew.mrsofttest.data.remote.NewsApi
import com.chopik.andrew.mrsofttest.data.remote.RemoteDataSource
import com.chopik.andrew.mrsofttest.data.remote.RemoteNewsData
import com.chopik.andrew.mrsofttest.screens.list.NewsListAdapter
import com.chopik.andrew.mrsofttest.screens.list.presenter.ListPresenter
import com.chopik.andrew.mrsofttest.screens.list.presenter.ListPresenterImpl
import com.chopik.andrew.mrsofttest.screens.list.view.DataListView
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideListPresenter(repository: AppDataSource): ListPresenter<DataListView> = ListPresenterImpl(repository)

    @Singleton
    @Provides
    fun provideAppDataSource(local: LocalDataSource, remote: RemoteDataSource): AppDataSource =
        Repository(local, remote)

    @Singleton
    @Provides
    fun provideLocalDataSource(database: NewsDatabase): LocalDataSource = LocalNewsData(database)

    @Singleton
    @Provides
    fun provideRemoteDataSource(newsApi: NewsApi): RemoteDataSource = RemoteNewsData(newsApi)

    @Provides
    fun provideNewsAdapter(context: Context): NewsListAdapter = NewsListAdapter(context)

    @Singleton
    @Provides
    fun provideNewsDatabase(application: Context): NewsDatabase =
        Room.databaseBuilder(application, NewsDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    internal fun provideNewsService(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(URL_API).build()
    }
}