package com.carles.mm.di

import com.carles.common.ui.Navigate
import com.carles.mm.NavigateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun provideNavigate(navigateImpl: NavigateImpl): Navigate
}