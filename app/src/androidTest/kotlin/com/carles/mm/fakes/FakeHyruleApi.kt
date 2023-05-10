package com.carles.mm.fakes

import com.carles.hyrule.data.remote.HyruleApi
import com.carles.hyrule.data.remote.MonsterDetailResponseDto
import com.carles.hyrule.data.remote.MonstersResponseDto
import com.google.gson.Gson
import io.reactivex.Single

class FakeHyruleApi : HyruleApi {

    override fun getMonsters(): Single<MonstersResponseDto> {
        return Single.just(
            Gson().fromJson(
                MONSTERS_RESPONSE_DTO,
                MonstersResponseDto::class.java
            )
        )
    }

    override fun getMonsterDetail(id: String): Single<MonsterDetailResponseDto> {
        val response = when (id) {
            "133" -> MONSTERS_DETAIL_133_RESPONSE_DTO
            "151" -> MONSTERS_DETAIL_151_RESPONSE_DTO
            else -> throw IllegalArgumentException()
        }
        return Single.just(Gson().fromJson(response, MonsterDetailResponseDto::class.java))
    }
}