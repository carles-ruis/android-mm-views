package com.carles.mm.di

import android.app.Application
import com.carles.common.domain.AppSchedulers
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    private const val BASE_URL = "https://botw-compendium.herokuapp.com"

    @Provides
    @Singleton
    fun provideRetrofit(app: Application): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor.Builder(app.applicationContext).build())
            .addNetworkInterceptor(StethoInterceptor())
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideAppSchedulers(): AppSchedulers {
        return AppSchedulers(
            io = Schedulers.io(),
            ui = AndroidSchedulers.mainThread(),
            new = Schedulers.newThread()
        )
    }
}