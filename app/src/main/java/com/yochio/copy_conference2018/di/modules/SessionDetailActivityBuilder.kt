package com.yochio.copy_conference2018.di.modules

import com.yochio.copy_conference2018.presentation.detail.SessionDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by yochio on 2018/03/09.
 */

@Module
interface SessionDetailActivityBuilder {
    @ContributesAndroidInjector(modules = [SessionDetailActivityModule::class])
    fun contributeSessionDetailActivity(): SessionDetailActivity

}