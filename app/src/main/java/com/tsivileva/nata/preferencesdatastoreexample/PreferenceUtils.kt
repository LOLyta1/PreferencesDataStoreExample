package com.tsivileva.nata.preferencesdatastoreexample

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class GamePreferences(
    context: Context
) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "gamePrefs")

    suspend fun editPrefs(prefs: GamePrefs) {
        dataStore.edit {
            it[PreferencesKeys.SOUND_VOLUME] = prefs.soundVolume
            it[PreferencesKeys.MUSIC_VOLUME] = prefs.musicVolume
            it[PreferencesKeys.MOVEMENT_DURATION] = prefs.movementDuration
            it[PreferencesKeys.IS_USER_AUTHORIZE] = prefs.isUserAuthorize
        }
    }
    suspend fun editSoundVolume(volume: Int) {
        dataStore.edit {
            it[PreferencesKeys.SOUND_VOLUME] = volume
        }
    }

    suspend fun editMusicVolume(volume: Int) {
        dataStore.edit {
            it[PreferencesKeys.MUSIC_VOLUME] = volume
        }
    }

    suspend fun editMovementDuration(duration: Int) {
        dataStore.edit {
            it[PreferencesKeys.MOVEMENT_DURATION] = duration
        }
    }

    suspend fun editIsUserAuthorize(isAuthorize: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.IS_USER_AUTHORIZE] = isAuthorize
        }
    }



    suspend fun getPreferences(): Flow<GamePrefs> {
        return dataStore.data
            .catch {
                if (this is IOException) {
                    emit(emptyPreferences())
                } else
                    if (this is Exception) {
                        throw this
                    }
            }
            .map {
                val soundVolume = it[PreferencesKeys.SOUND_VOLUME] ?: DEFAULT_SOUNDS_VOLUME
                val musicVolume = it[PreferencesKeys.MUSIC_VOLUME] ?: DEFAULT_MUSIC_VOLUME
                val movementDuration =
                    it[PreferencesKeys.MOVEMENT_DURATION] ?: MOVEMENT_DURATION_NORMAL
                val isUserAuthorize = it[PreferencesKeys.IS_USER_AUTHORIZE] ?: false
                GamePrefs(
                    soundVolume = soundVolume,
                    musicVolume = musicVolume,
                    movementDuration = movementDuration,
                    isUserAuthorize = isUserAuthorize
                )
            }
    }
}