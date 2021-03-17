package com.amberfleet.innoventesinter.di.component


import com.amberfleet.innoventesinter.application.InnoventApp
import com.amberfleet.innoventesinter.di.builder.ActivityBuilder
import com.amberfleet.innoventesinter.di.module.AppModule
import com.amberfleet.innoventesinter.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        //Dagger support
        AndroidInjectionModule::class,

        //Activity
        ActivityBuilder::class,

        //ViewModel
        ViewModelModule::class,

        //App
        AppModule::class,

    ]
)

@Singleton
public interface AppComponent : AndroidInjector<InnoventApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        abstract fun application(application: InnoventApp): Builder
        abstract fun appModule(appModule: AppModule): Builder

        abstract fun build(): AppComponent
    }

    override fun inject(instance: InnoventApp?)
}