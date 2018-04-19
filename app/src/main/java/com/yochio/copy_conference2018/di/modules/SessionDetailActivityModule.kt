package com.yochio.copy_conference2018.di.modules

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.yochio.copy_conference2018.di.ViewModelKey
import com.yochio.copy_conference2018.presentation.detail.SessionDetailActivity
import com.yochio.copy_conference2018.presentation.detail.SessionDetailFragment
import com.yochio.copy_conference2018.presentation.detail.SessionDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by yochio on 2018/03/09.
 */

@Module interface SessionDetailActivityModule {
    @Binds
    fun provideAppCompatActivity(activity: SessionDetailActivity): AppCompatActivity

    @ContributesAndroidInjector fun contributeDetailFragment(): SessionDetailFragment

    @Binds @IntoMap
    @ViewModelKey(SessionDetailViewModel::class)
    fun bindSessionDetailViewModel(
            sessionDetailViewModel: SessionDetailViewModel
    ): ViewModel
}