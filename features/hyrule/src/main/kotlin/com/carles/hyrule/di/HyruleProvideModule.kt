package com.carles.hyrule.di

import android.content.Context
import androidx.room.Room
import com.carles.hyrule.data.local.HyruleDatabase
import com.carles.hyrule.data.local.MonsterDao
import com.carles.hyrule.data.remote.HyruleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HyruleProvideModule {

    @Provides
    @Singleton
    fun provideHyruleDatabase(
        @ApplicationContext
        context: Context
    ): HyruleDatabase {
        return Room.databaseBuilder(context, HyruleDatabase::class.java, "hyrule_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMonsterDao(database: HyruleDatabase): MonsterDao {
        return database.monsterDao()
    }

    @Provides
    @Singleton
    fun provideHyruleApi(retrofit: Retrofit): HyruleApi {
        return retrofit.create(HyruleApi::class.java)
    }
}
