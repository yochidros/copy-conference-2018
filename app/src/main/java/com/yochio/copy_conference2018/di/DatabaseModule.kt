package com.yochio.copy_conference2018.di

import android.app.Application
import android.arch.persistence.room.Room
import com.yochio.copy_conference2018.App
import com.yochio.copy_conference2018.data.AppDatabase
import com.yochio.copy_conference2018.data.SessionDatabase
import com.yochio.copy_conference2018.data.SessionRoomDatabase
import com.yochio.copy_conference2018.data.db.dao.SessionDao
import com.yochio.copy_conference2018.data.db.dao.SessionSpeakerJoinDao
import com.yochio.copy_conference2018.data.db.dao.SpeakerDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yochio on 2018/03/07.
 */

@Module
class DatabaseModule {

    companion object {
        val instance = DatabaseModule()
    }

    @Singleton @Provides
    fun provideSessionDatabase(
            appDatabase: AppDatabase,
            sessionDbDao: SessionDao,
            speakerDao: SpeakerDao,
            sessionSpeakerJoinDao: SessionSpeakerJoinDao
    ): SessionDatabase = SessionRoomDatabase(appDatabase, sessionDbDao, speakerDao, sessionSpeakerJoinDao)

    @Singleton @Provides open fun provideDb(app: Application): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, "droidKaigi.db")
                    .fallbackToDestructiveMigration()
                    .build()

    @Singleton @Provides fun provideSessionDao(db: AppDatabase): SessionDao = db.sessionDao()

    @Singleton @Provides fun provideSpeakerDao(db: AppDatabase): SpeakerDao = db.speakerDao()

    @Singleton @Provides fun provideSessionSpeakerJoinDao(db: AppDatabase): SessionSpeakerJoinDao = db.sessionSpeakerDao()
}