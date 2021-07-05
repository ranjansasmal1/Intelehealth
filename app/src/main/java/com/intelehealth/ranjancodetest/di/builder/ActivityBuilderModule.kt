package com.intelehealth.ranjancodetest.di.builder

import com.intelehealth.ranjancodetest.view.activity.MainActivity
import com.intelehealth.ranjancodetest.di.scope.ActivityScope
import com.intelehealth.ranjancodetest.view.activity.DetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeSplashScreen(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeDetailsActivity(): DetailsActivity
}