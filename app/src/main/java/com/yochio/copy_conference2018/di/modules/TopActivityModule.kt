package com.yochio.copy_conference2018.di.modules

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.yochio.copy_conference2018.TopActivity
import com.yochio.copy_conference2018.di.ViewModelKey
import com.yochio.copy_conference2018.presentation.favorite.FavoriteSessionFragment
import com.yochio.copy_conference2018.presentation.search.*
import com.yochio.copy_conference2018.presentation.sessions.*
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by yochio on 2018/03/05.
 */


@Module interface TopActivityModule {
    @Binds
    fun providesAppCompatActivity(topActivity: TopActivity): AppCompatActivity

    @ContributesAndroidInjector fun contributeFavoritesSessionFragment(): FavoriteSessionFragment

    @ContributesAndroidInjector fun contributeSessionFragment(): SessionsFragment

    @ContributesAndroidInjector fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector fun contributeRoomSessionsFragment(): RoomSessionsFragment

    @ContributesAndroidInjector fun contributeAllSessionsFragment(): AllSessionsFragment

    @ContributesAndroidInjector fun contributeSeachSessionsFragment(): SearchSessionsFragment

    @ContributesAndroidInjector fun contributeSearchTopicsFragment(): SearchTopicsFragment

    @Binds @IntoMap
    @ViewModelKey(SearchTopicsViewModel::class)
    fun bindSearchTopicsViewModel(
            sessionDetailViewModel: SearchTopicsViewModel
    ): ViewModel

    @Binds @IntoMap
    @ViewModelKey(SearchSessionsViewModel::class)
    fun bindSearchSesionsViewModel(
            sessionDetailViewModel: SearchSessionsViewModel
    ): ViewModel

    @Binds @IntoMap
    @ViewModelKey(SessionsViewModel::class)
    fun bindsSessionsViewModel(
            sessionsViewModel: SessionsViewModel
    ): ViewModel

    @Binds @IntoMap
    @ViewModelKey(RoomSessionsViewModel::class)
    fun bindsRoomSessionsViewModel(
            sessionsViewModel: RoomSessionsViewModel
    ): ViewModel

    @Binds @IntoMap
    @ViewModelKey(AllSessionsViewModel::class)
    fun bindsAllSessionsViewModel(
            sessionsViewModel: AllSessionsViewModel
    ): ViewModel
}
