package com.carles.hyrule.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.carles.common.ui.MutableResourceLiveData
import com.carles.common.ui.ResourceLiveData
import com.carles.common.ui.extensions.addTo
import com.carles.common.ui.setError
import com.carles.common.ui.setLoading
import com.carles.common.ui.setSuccess
import com.carles.hyrule.Monster
import com.carles.hyrule.domain.RefreshMonsters
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MonstersViewModel @Inject constructor(
    private val refreshMonsters: RefreshMonsters
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _monsters = MutableResourceLiveData<List<Monster>>()
    val monsters: ResourceLiveData<List<Monster>> = _monsters

    init {
        refreshMonsters()
    }

    private fun refreshMonsters() {
        refreshMonsters.execute()
            .doOnSubscribe {
                _monsters.setLoading()
            }.subscribe({ monsters ->
                _monsters.setSuccess(monsters)
            }, { error ->
                Log.w("MonstersViewModel", error)
                _monsters.setError(error.message)
            }).addTo(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun retry() {
        refreshMonsters()
    }
}