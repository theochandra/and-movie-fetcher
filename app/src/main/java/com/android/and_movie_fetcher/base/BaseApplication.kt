package com.android.and_movie_fetcher.base

import android.app.Application
import com.android.and_movie_fetcher.di.Injector
import com.android.and_movie_fetcher.di.application.AppComponent
import com.android.and_movie_fetcher.di.application.AppModule
import com.android.and_movie_fetcher.di.application.DaggerAppComponent

class BaseApplication : Application(), Injector {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

}