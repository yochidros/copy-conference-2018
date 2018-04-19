package com.yochio.copy_conference2018.di

import android.app.Application
import android.content.Context
import com.yochio.copy_conference2018.data.SessionDatabase
import com.yochio.copy_conference2018.data.api.DroidKaigApi
import com.yochio.copy_conference2018.data.repository.SessionDataRepository
import com.yochio.copy_conference2018.data.repository.SessionRepository
import com.yochio.copy_conference2018.util.rx.AppSchedulerProvider
import com.yochio.copy_conference2018.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yochio on 2018/03/07.
 */

@Module internal object AppModule {
    @Singleton @Provides @JvmStatic
    fun provideContext(application: Application): Context = application

    @Singleton @Provides @JvmStatic
    fun provideSessionRepository(
            api: DroidKaigApi,
            sessionDatabase: SessionDatabase,
            schedulerProvider: SchedulerProvider
    ): SessionRepository = SessionDataRepository(api, sessionDatabase, schedulerProvider)

    @Singleton @Provides @JvmStatic
    fun provideScheduleProvider(): SchedulerProvider = AppSchedulerProvider()
}