package com.intelehealth.ranjancodetest.di.component

import android.app.Application
import com.intelehealth.ranjancodetest.IntelehealthApplication
import com.intelehealth.ranjancodetest.di.builder.ActivityBuilderModule
import com.intelehealth.ranjancodetest.di.module.AppModule
import com.intelehealth.ranjancodetest.di.module.NetworkModule
import com.intelehealth.ranjancodetest.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    ActivityBuilderModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent  : AndroidInjector<IntelehealthApplication> {

    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent
    }

}