package com.tsivileva.nata.preferencesdatastoreexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GameViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val datastore: GamePreferences = GamePreferences(application)
    val prefsLiveData = MutableLiveData<GamePrefs>()

    fun saveGamePrefs(gamePrefs: GamePrefs) {
        viewModelScope.launch {
            datastore.editPrefs(prefs = gamePrefs)
        }
    }

    fun getPrefs() {
        viewModelScope.launch {
            datastore.getPreferences().collect {
                prefsLiveData.postValue(it)
            }
        }
    }
}