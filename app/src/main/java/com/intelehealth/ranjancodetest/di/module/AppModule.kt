package com.intelehealth.ranjancodetest.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun provideContext(application: Application):Context{
        return application.applicationContext
    }
}