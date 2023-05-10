package com.carles.mm.di

import com.carles.common.ui.Navigate
import com.carles.mm.NavigateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @ActivityScoped
    @Binds
    abstract fun provideNavigate(navigateImpl: NavigateImpl): Navigate
}