package com.example.mod_c.ui.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.mod_c.ui.models.AppState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore by preferencesDataStore("app_preferences")
class AppPreferences(val context: Context){
    object Keys{
        val splashScreenSeen = booleanPreferencesKey("splash_screen_seen")
    }

    val state: Flow<AppState> = context.dataStore.data
        .map{ preferences ->
            AppState(
                splashScreenSeen = preferences[Keys.splashScreenSeen] ?: false
            )

        }

    suspend fun finishOnboarding(){
        context.dataStore.edit { preferences ->
            preferences[Keys.splashScreenSeen] = true
        }
    }
}