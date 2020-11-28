package com.tsivileva.nata.preferencesdatastoreexample

import androidx.datastore.preferences.core.preferencesKey

const val MOVEMENT_DURATION_NORMAL = 250
const val MOVEMENT_DURATION_FAST = 125
const val DEFAULT_SOUNDS_VOLUME = 80
const val DEFAULT_MUSIC_VOLUME = 30
const val MAX_SOUND_VOLUME = 100

data class GamePrefs(
    var soundVolume: Int = DEFAULT_SOUNDS_VOLUME,
    var musicVolume: Int = DEFAULT_MUSIC_VOLUME,
    var movementDuration: Int = MOVEMENT_DURATION_NORMAL,
    var isUserAuthorize: Boolean = false
)

object PreferencesKeys {
    val SOUND_VOLUME = preferencesKey<Int>("soundVolume")
    val MUSIC_VOLUME = preferencesKey<Int>("musicVolume")
    val MOVEMENT_DURATION = preferencesKey<Int>("movementDuration")
    val IS_USER_AUTHORIZE = preferencesKey<Boolean>("isUserAuthorize")
}