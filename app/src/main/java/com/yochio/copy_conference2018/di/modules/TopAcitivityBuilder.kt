package com.yochio.copy_conference2018.di.modules

import com.yochio.copy_conference2018.TopActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by yochio on 2018/03/05.
 */


@Module interface TopAcitivityBuilder {
    @ContributesAndroidInjector(modules = [TopActivityModule::class])
    fun contributeTopActivity(): TopActivity
}