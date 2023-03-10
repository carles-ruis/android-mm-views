package com.carles.hyrule.data.local

import com.carles.common.data.Cache
import com.carles.common.data.CacheItems
import com.carles.common.data.CacheKey
import com.carles.hyrule.Monster
import com.carles.hyrule.MonsterDetail
import com.carles.hyrule.data.HyruleDatasource
import com.carles.hyrule.data.mapper.MonsterDetailMapper
import com.carles.hyrule.data.mapper.MonstersMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class HyruleLocalDatasourceAlt @Inject constructor(
    private val dao: MonsterDao,
    private val monstersMapper: MonstersMapper,
    private val monsterDetailMapper: MonsterDetailMapper,
    private val cache: Cache
) : HyruleDatasource {

    override fun getMonsters(): Single<List<Monster>> {
        return dao.loadMonsters().map { entity ->
            monstersMapper.fromEntity(entity)
        }
    }

    override fun getMonsterDetail(id: Int): Single<MonsterDetail> {
        return dao.loadMonsterDetail(id).map { entity ->
            monsterDetailMapper.fromEntity(entity)
        }
    }

    fun persist(monsters: List<MonsterEntity>): Completable {
        return Completable.fromCallable {
            dao.deleteMonsters()
            dao.insertMonsters(monsters)
            cache.set(CacheKey(CacheItems.MONSTERS))
        }
    }

    fun persist(monster: MonsterDetailEntity): Completable {
        return Completable.fromAction {
            dao.insertMonsterDetail(monster)
            cache.set(CacheKey(CacheItems.MONSTER_DETAIL, monster.id))
        }
    }
}