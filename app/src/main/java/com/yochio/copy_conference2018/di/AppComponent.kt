package com.yochio.copy_conference2018.di

import android.app.Application
import com.yochio.copy_conference2018.App
import com.yochio.copy_conference2018.di.modules.SessionDetailActivityBuilder
import com.yochio.copy_conference2018.di.modules.TopAcitivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by yochio on 2018/03/05.
 */

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    TopAcitivityBuilder::class,
    AppModule::class,
    ViewModelModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    SessionDetailActivityBuilder::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}