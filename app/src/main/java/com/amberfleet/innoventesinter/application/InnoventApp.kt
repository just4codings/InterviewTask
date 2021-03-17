package com.amberfleet.innoventesinter.application

import com.amberfleet.innoventesinter.di.component.DaggerAppComponent
import com.amberfleet.innoventesinter.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class InnoventApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
        component.inject(this)
        return component
    }
}