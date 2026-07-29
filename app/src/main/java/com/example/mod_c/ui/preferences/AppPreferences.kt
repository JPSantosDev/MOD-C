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
        val darkMode = booleanPreferencesKey("dark_mode")
        val highContrast = booleanPreferencesKey("high_contrast")
        val notificationsActive = booleanPreferencesKey("notifications_active")
    }

    val state: Flow<AppState> = context.dataStore.data
        .map{ preferences ->
            AppState(
                splashScreenSeen = preferences[Keys.splashScreenSeen] ?: false,
                darkMode = preferences[Keys.darkMode] ?: false,
                notificationsActive = preferences[Keys.notificationsActive] ?: false,
                highContrast = preferences[Keys.highContrast] ?: false


            )
        }
    suspend fun finishOnboarding(){
        context.dataStore.edit { preferences ->
            preferences[Keys.splashScreenSeen] = true
        }
    }
    suspend fun alterDarkMode(){
        context.dataStore.edit { preferences ->
            val currentState = preferences[Keys.darkMode] ?: false
            preferences[Keys.darkMode] = !currentState
        }
    }
    suspend fun alterNotifications(){
        context.dataStore.edit { preferences ->
            val currentState = preferences[Keys.notificationsActive] ?: false
            preferences[Keys.notificationsActive] = !currentState
        }
    }
    suspend fun alterContrast(){
        context.dataStore.edit { preferences ->
            val currentState = preferences[Keys.highContrast] ?: false
            preferences[Keys.highContrast] = !currentState
        }
    }

}