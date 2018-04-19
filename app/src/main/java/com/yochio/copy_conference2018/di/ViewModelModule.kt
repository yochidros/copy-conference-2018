package com.yochio.copy_conference2018.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Created by yochio on 2018/03/06.
 */

@Module interface ViewModelModule {
    @Binds fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}