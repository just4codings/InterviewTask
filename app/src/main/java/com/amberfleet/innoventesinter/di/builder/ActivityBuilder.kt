package com.amberfleet.innoventesinter.di.builder


import com.amberfleet.innoventesinter.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


}