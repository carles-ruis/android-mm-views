package com.carles.hyrule.data

import com.carles.common.data.Cache
import com.carles.common.data.CacheKey
import com.carles.hyrule.data.local.HyruleLocalDatasourceAlt
import com.carles.hyrule.data.remote.HyruleRemoteDatasourceAlt
import javax.inject.Inject

class HyruleDatasourceFactoryAlt @Inject constructor(
    private val localDatasource: HyruleLocalDatasourceAlt,
    private val remoteDatasource: HyruleRemoteDatasourceAlt,
    private val cache: Cache
) {

    fun provideDatasource(key: CacheKey, refresh: Boolean = false): HyruleDatasource {
        return when {
            refresh -> remoteDatasource
            cache.isCached(key) -> localDatasource
            else -> remoteDatasource
        }
    }
}