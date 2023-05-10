package com.carles.mm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.carles.hyrule.data.local.HyruleDatabase
import com.carles.hyrule.data.local.MonsterDao
import com.carles.hyrule.data.local.MonsterDetailEntity
import com.carles.hyrule.data.local.MonsterEntity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MonsterDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: MonsterDao
    private lateinit var database: HyruleDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context = InstrumentationRegistry.getInstrumentation().context,
            klass = HyruleDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = database.monsterDao()
    }

    @After
    @kotlin.jvm.Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenLoadMonsters_whenCalled_thenLoadAllMonstersFromDatabase() {
        dao.insertMonsters(MONSTERS_ENTITY).test().assertComplete()
        dao.loadMonsters().test().assertValue(MONSTERS_ENTITY)
    }

    @Test
    fun givenInsertMonsters_whenMonsterIsNotInDatabase_thenInsertIt() {
        dao.insertMonsters(MONSTERS_ENTITY).test().assertValue(listOf(1L))
    }

    @Test
    fun givenInsertMonsters_whenMonsterIsAlreadyInDatabase_thenReplaceIt() {
        dao.insertMonsters(MONSTERS_ENTITY).test().assertComplete()
        dao.insertMonsters(MONSTERS_ENTITY).test().assertValue(listOf(1L))
    }

    @Test
    fun givenDeleteMonsters_whenCalled_thenDeleteAllMonstersFromDatabase() {
        dao.insertMonsters(MONSTERS_ENTITY).test().assertComplete()
        dao.deleteMonsters().test().assertValue(1)
    }

    @Test
    fun givenLoadMonsterDetail_whenCalled_thenGetProperMonster() {
        dao.insertMonsterDetail(MONSTER_DETAIL_ENTITY).test().assertComplete()
        dao.loadMonsterDetail(1).test().assertValue(MONSTER_DETAIL_ENTITY)
    }

    @Test
    fun givenInsertMonsterDetail_whenMonsterIsNotInDatabase_thenInsertIt() {
        dao.insertMonsterDetail(MONSTER_DETAIL_ENTITY).test().assertValue(1L)
    }

    @Test
    fun givenInsertMonsterDetail_whenMonsterIsAlreadyInDatabase_thenReplaceIt() {
        dao.insertMonsterDetail(MONSTER_DETAIL_ENTITY).test().assertComplete()
        dao.insertMonsterDetail(MONSTER_DETAIL_ENTITY).test().assertValue(1L)
    }

    companion object {
        private val MONSTERS_ENTITY = listOf(MonsterEntity(1, "Monster"))
        private val MONSTER_DETAIL_ENTITY = MonsterDetailEntity(1, "Monster", "Here", "Big monster", "https://url")
    }
}