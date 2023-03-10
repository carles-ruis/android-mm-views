package com.carles.hyrule.data.remote

import com.carles.hyrule.Monster
import com.carles.hyrule.MonsterDetail
import com.carles.hyrule.data.HyruleDatasource
import com.carles.hyrule.data.local.HyruleLocalDatasourceAlt
import com.carles.hyrule.data.mapper.MonsterDetailMapper
import com.carles.hyrule.data.mapper.MonstersMapper
import io.reactivex.Single
import javax.inject.Inject

class HyruleRemoteDatasourceAlt @Inject constructor(
    private val api: HyruleApi,
    private val localDatasource: HyruleLocalDatasourceAlt,
    private val monstersMapper: MonstersMapper,
    private val monsterDetailMapper: MonsterDetailMapper
) : HyruleDatasource {

    override fun getMonsters(): Single<List<Monster>> {
        return api.getMonsters().map { dto ->
            monstersMapper.toEntity(dto)
        }.flatMap {
            localDatasource.persist(it)
            localDatasource.getMonsters()
        }
    }

    override fun getMonsterDetail(id: Int): Single<MonsterDetail> {
        return api.getMonsterDetail(id.toString()).map { dto ->
            monsterDetailMapper.toEntity(dto)
        }.flatMap {
            localDatasource.persist(it)
            localDatasource.getMonsterDetail(id)
        }
    }
}