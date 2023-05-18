package com.carles.hyrule.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carles.common.ui.Navigate
import com.carles.common.ui.extensions.addTo
import com.carles.hyrule.Monster
import com.carles.hyrule.domain.RefreshMonsters
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

sealed class MonstersState {
    object Loading : MonstersState()
    data class Success(val monsters: List<Monster>) : MonstersState()
    data class Error(val message: String?) : MonstersState()
}

@HiltViewModel
class MonstersViewModel @Inject constructor(
    private val refreshMonsters: RefreshMonsters,
    private val navigate: Navigate
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _uiState = MutableLiveData<MonstersState>()
    val uiState: LiveData<MonstersState> = _uiState

    init {
        refreshMonsters()
    }

    private fun refreshMonsters() {
        refreshMonsters.execute()
            .doOnSubscribe {
                _uiState.value = MonstersState.Loading
            }.subscribe({ monsters ->
                _uiState.value = MonstersState.Success(monsters)
            }, { error ->
                Log.w("MonstersViewModel", error)
                _uiState.value = MonstersState.Error(error.message)
            }).addTo(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun retry() {
        refreshMonsters()
    }

    fun onMonsterClicked(monster: Monster) {
        navigate.toMonsterDetail(monster.id)
    }

    fun onErrorDisplayed(message: String) {
        navigate.toErrorDialog(message, retry = true)
    }
}