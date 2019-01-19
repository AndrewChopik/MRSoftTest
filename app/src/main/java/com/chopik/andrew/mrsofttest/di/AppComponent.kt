package com.chopik.andrew.mrsofttest.di

import android.content.Context
import com.chopik.andrew.mrsofttest.screens.list.view.DataListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(dataListActivity: DataListActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(context: Context): Builder
    }
}