package com.intelehealth.ranjancodetest

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.intelehealth.ranjancodetest.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class IntelehealthApplication: Application(), HasActivityInjector, HasSupportFragmentInjector {

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = mAndroidInjector

    @Inject
    lateinit var mAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder() // Building the app component
            .app(this)
            // .appModule(AppModule(this))
            .build().inject(this) // Injecting our android injector

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentInjector
}