package com.yochio.copy_conference2018.di

import android.support.annotation.RestrictTo
import com.squareup.moshi.Moshi
import com.yochio.copy_conference2018.data.api.DroidKaigApi
import com.yochio.copy_conference2018.data.api.response.mapper.ApplicationJsonAdapterFactory
import com.yochio.copy_conference2018.data.api.response.mapper.LocalDateTimeAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by yochio on 2018/03/06.
 */

@Module open class NetworkModule {

    companion object {
        val instance = NetworkModule()
    }

    @Singleton @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://droidkaigi.jp/2018/")
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                        .add(ApplicationJsonAdapterFactory.INSTANCE)
                        .add(LocalDateTime::class.java, LocalDateTimeAdapter())
                        .build())
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @Singleton @Provides
    fun provideDroidKaigiApi(retrofit: Retrofit): DroidKaigApi {
        return retrofit.create(DroidKaigApi::class.java)
    }
}