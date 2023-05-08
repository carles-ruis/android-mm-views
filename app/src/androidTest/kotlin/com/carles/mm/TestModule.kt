package com.carles.mm

import android.content.Context
import androidx.room.Room
import com.carles.common.domain.AppSchedulers
import com.carles.hyrule.data.local.HyruleDatabase
import com.carles.hyrule.data.local.MonsterDao
import com.carles.hyrule.data.remote.HyruleApi
import com.carles.mm.api.FakeHyruleApi
import com.carles.common.di.CommonModule
import com.carles.hyrule.di.HyruleProvideModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CommonModule::class, HyruleProvideModule::class]
)
@Module
class TestModule {

    @Provides
    @Singleton
    fun provideAppSchedulers(): AppSchedulers {
        return AppSchedulers(
            io = AndroidSchedulers.mainThread(),
            ui = AndroidSchedulers.mainThread(),
            new = AndroidSchedulers.mainThread()
        )
    }

    @Provides
    @Singleton
    fun provideHyruleDatabase(@ApplicationContext context: Context): HyruleDatabase {
        return Room.inMemoryDatabaseBuilder(context, HyruleDatabase::class.java)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMonsterDao(database: HyruleDatabase): MonsterDao {
        return database.monsterDao()
    }

    @Provides
    @Singleton
    fun provideHyruleApi(): HyruleApi {
        return FakeHyruleApi()
    }
}